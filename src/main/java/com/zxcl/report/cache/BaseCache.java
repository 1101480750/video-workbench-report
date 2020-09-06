package com.zxcl.report.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

/**
 * 缓存操作类
 * @author Lee SZ
 * @date 2016年5月11日
 */
public abstract class BaseCache<T> implements Cache<T>{

	/**
	 * REDIS数据库索引号
	 */
	protected int index;

	@Resource
	protected RedisTemplate<String, Serializable> redisTemplate;

	public BaseCache(int index){
		this.index = index;
	}

	@Override
	public void setCache(String key, T value){
		setCache(key, value, RedisConstant.DEFAULT_EXPIRES);
	}

	@Override
	public void setCache(final String key, final T value, final long expires) {
		final String cacheKey = getKey(key);
		redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(index);
				byte[] kByte = redisTemplate.getStringSerializer().serialize(cacheKey);
				byte[] vByte = redisTemplate.getStringSerializer().serialize(JSON.toJSONString(value));
				connection.setEx(kByte, expires, vByte);
				return true;
			}
		});
	}

	public void setStrCache(final String key, final String value){
	    setStrCache(key, value, RedisConstant.DEFAULT_EXPIRES);
	}

	public void setStrCache(final String key, final String value, final long expires) {
		final String cacheKey = getKey(key);
		redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(index);
				byte[] kByte = redisTemplate.getStringSerializer().serialize(cacheKey);
				byte[] vByte = redisTemplate.getStringSerializer().serialize(value);
				connection.setEx(kByte, expires, vByte);
				return true;
			}
		});
	}

	@Override
	public void removeCache(String key) {
		final String cacheKey = getKey(key);
		redisTemplate.execute(new RedisCallback<Boolean>() {
	        @Override
	        public Boolean doInRedis(RedisConnection connection)  throws DataAccessException {
	        	connection.select(index);
	        	RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] k = serializer.serialize(cacheKey);
                connection.del(k);
                return true;
	        }
	    });
	}

	@Override
	public T getCache(final String key, final Class<T> c) {
		final String cacheKey = getKey(key);
		T result = redisTemplate.execute(new RedisCallback<T>() {
			public T doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(index);
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] k = serializer.serialize(cacheKey);
				byte[] val = connection.get(k);
				if (val == null) {
					return null;
				}
				String jsonStr = serializer.deserialize(val);
				return  JSON.parseObject(jsonStr, c);
			}
		});
		return result;
	}

	public List<T> getCacheList(final String key, final Class<T> c) {
		final String cacheKey = getKey(key);
		List<T> result = redisTemplate.execute(new RedisCallback<List<T>>() {
			public List<T> doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(index);
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] k = serializer.serialize(cacheKey);
				List<byte[]> val = connection.lRange(k, 0, -1);
				if (val == null || val.size() <= 0) {
					return null;
				}
				List<String> jsonList = new ArrayList<>();
				for(byte[] v : val) {
					String jsonStr = serializer.deserialize(v);
					jsonList.add(jsonStr);
				}
				return  JSONArray.parseArray(jsonList.toString(), c);
			}
		});
		return result;
	}

	@Override
	public void flush() {
		redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(index);
				connection.flushAll();
				return true;
			}
		});
	}

	@Override
	public void expire(final String key, final long seconds){
		final String cacheKey = getKey(key);
		redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(index);
				byte[] kByte = redisTemplate.getStringSerializer().serialize(cacheKey);
				connection.expire(kByte, seconds);
				return true;
			}
		});
	}


	@Override
	public List<T> getCache(final Class<T> c, String... keys) {
		if(c ==null || keys == null || keys.length <=0){
			return null;
		}
		final String[] cacheKeys = new String[keys.length];
		for(int i=0; i< cacheKeys.length; i++){
			cacheKeys[i] = getKey(keys[i]);
		}
		List<T> result = redisTemplate.execute(new RedisCallback<List<T>>() {
			public List<T> doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(index);
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();

			   byte[][] bKeys = new byte[cacheKeys.length][];
			   for(int i=0; i<cacheKeys.length; i++){
				   bKeys[i] = serializer.serialize(cacheKeys[i]);
			   }
				List<byte[]> bValList = connection.mGet(bKeys);
				if(bValList == null || bValList.isEmpty()){
					return null;
				}
				List<T> l = new ArrayList<T>();
				for(int i=0; i<bValList.size(); i++){
					byte[] bval = bValList.get(i);
					if(bval == null || bval.length == 0){
						continue;
					}
					String jsonStr = serializer.deserialize(bval);
					l.add(JSON.parseObject(jsonStr, c));
				}
				return  l;
			}
		});
		return result;
	}

	/**
	 * 获取字符串的value
	 * @param key
	 * @return
	 * @author Lee SZ
	 * @date 2016年5月11日
	 */
	public String getStrCache(String key){
		if( key == null || "".equals(key.trim())){
			return null;
		}

		final String cacheKey = getKey(key);
		String result = redisTemplate.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(index);
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] k = serializer.serialize(cacheKey);
				byte[] val = connection.get(k);
				if (val == null) {
					return null;
				}
				String jsonStr = serializer.deserialize(val);
				return  jsonStr;
			}
		});
		return result;
	}

	@Override
	public T getMapValue(String key, final String field, final Class<T> c) {
		if( key == null || "".equals(key.trim()) || field == null || "".equals(field.trim())){
			return null;
		}

		final String cacheKey = getKey(key);
		T result = redisTemplate.execute(new RedisCallback<T>() {
			public T doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(index);
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] k = serializer.serialize(cacheKey);
				byte[] f = serializer.serialize(field);
				byte[] v = connection.hGet(k, f);
				if (v == null) {
					return null;
				}
				String jsonStr = serializer.deserialize(v);
				return  JSON.parseObject(jsonStr, c);
			}
		});
		return result;
	}

	@Override
	public Map<String, T> getMap(String key, final Class<T> c) {
		if( key == null || "".equals(key.trim())){
			return null;
		}

		final String cacheKey = getKey(key);
		Map<String, T> result = redisTemplate.execute(new RedisCallback<Map<String, T>>() {
			public Map<String, T> doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(index);
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] k = serializer.serialize(cacheKey);
				Map<byte[], byte[]> vMap = connection.hGetAll(k);
				if (vMap == null) {
					return null;
				}
				Map<String, T> map = new HashMap<String, T>();
				Set<Entry<byte[], byte[]>> entrySet = vMap.entrySet();
				for(Iterator<Entry<byte[], byte[]>> it = entrySet.iterator(); it.hasNext();){
					Entry<byte[], byte[]> entry = it.next();
					String mapKey = serializer.deserialize(entry.getKey());
					T mapValue = JSON.parseObject(serializer.deserialize(entry.getValue()), c);
					map.put(mapKey, mapValue);
				}
				return  map;
			}
		});
		return result;
	}

	@Override
	public boolean setMapValue(final String key, final String field, final T value) {
		if( key == null || "".equals(key.trim()) || field == null || "".equals(field.trim()) || value == null){
			return false;
		}
		final String cacheKey = getKey(key);
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(index);
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] kByte = serializer.serialize(cacheKey);
				byte[] fByte = serializer.serialize(field);
				byte[] vByte = serializer.serialize(JSON.toJSONString(value));

				boolean result = connection.hSet(kByte, fByte, vByte);
				return result;
			}
		});
	}

	@Override
	public void setMap(final String key, final Map<String, T> map) {
		if( key == null || "".equals(key.trim()) || map == null || map.isEmpty()){
			return;
		}
		final String cacheKey = getKey(key);
		redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(index);
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] kByte = serializer.serialize(cacheKey);
				Map<byte[], byte[]> hashes = new HashMap<byte[], byte[]>();
				Set<Entry<String, T>> entrySet = map.entrySet();
				for(Iterator<Entry<String, T>> it = entrySet.iterator(); it.hasNext();){
					Entry<String, T> entry = it.next();
					byte[] keyMap = serializer.serialize(entry.getKey());
					byte[] valueMap = serializer.serialize(JSON.toJSONString(entry.getValue()));
					hashes.put(keyMap, valueMap);
				}
				connection.hMSet(kByte, hashes);
				return true;
			}
		});
	}


	public long setListValue(String key, final List<T> values){
		final String cacheKey = getKey(key);
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(index);
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] bKey = serializer.serialize(cacheKey);
				byte[][] bVal = new byte[values.size()][];
				for(int i=0; i < values.size(); i++){
					bVal[i] = serializer.serialize(JSON.toJSONString(values.get(i)));
				}
				return connection.rPush(bKey, bVal);
			}
		});
	}

	public T getListValue(String key, final Class<T> c){
		final String cacheKey = getKey(key);
		return redisTemplate.execute(new RedisCallback<T>() {
			@Override
			public T doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(index);
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] bKey = serializer.serialize(cacheKey);
				byte[] bVal = connection.rPop(bKey);
				if(bVal != null && bVal.length > 0){
					return JSON.parseObject(serializer.deserialize(bVal), c);
				}
				return null;
			}
		});
	}

	public T brpoplpush(String key, String tempKey, final int timeout, final Class<T> c){
		final String cacheKey = getKey(key);
		final String temp = tempKey;
		return redisTemplate.execute(new RedisCallback<T>() {
			@Override
			public T doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(index);
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] bKey = serializer.serialize(cacheKey);
				byte[] tempKey = serializer.serialize(temp);
				byte[] bVal = connection.bRPopLPush(timeout, bKey, tempKey);
				if(bVal != null && bVal.length > 0){
					return JSON.parseObject(serializer.deserialize(bVal), c);
				}
				return null;
			}
		});
	}

	public boolean hExist(final String key, final String field){
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(index);
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] bKey = serializer.serialize(key);
				byte[] bField = serializer.serialize(field);
				return connection.hExists(bKey, bField);
			}
		});
	}

	public int hDel(final String key, final String field){
		final String cacheKey = getKey(key);
		Long result = redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(index);
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] bKey = serializer.serialize(cacheKey);
				byte[] bField = serializer.serialize(field);
				return connection.hDel(bKey, bField);
			}
		});

		return result.intValue();
	}

	public List<String> keys(final String pattarn){
		List<String> list = redisTemplate.execute(new RedisCallback<List<String>>() {
			@Override
			public List<String> doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(index);
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] k = serializer.serialize(pattarn);
				Set<byte[]> valuesByte = connection.keys(k);
				if(valuesByte == null || valuesByte.isEmpty()){
					return null;
				}
				List<String> list = new ArrayList<String>();
				for(Iterator<byte[]> it = valuesByte.iterator(); it.hasNext();){
					String key = serializer.deserialize(it.next());
					list.add(key);
				}
				return  list;
			}
		});
		return list;
	}

	public long ttl(final String key){
		long ttl = redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(index);
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] k = serializer.serialize(key);
				long ttl = connection.ttl(k);

				return  ttl;
			}
		});
		return ttl;
	}

    public Boolean setListMap(String key, final List<Map<Object, Object>> list){
		final String cacheKey = getKey(key);
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(index);
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] bKey = serializer.serialize(cacheKey);
				connection.set(bKey, SerializationUtils.serialize(list));
				return true;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<?> getListMap(String key){
		final String cacheKey = getKey(key);
		return (List<Map<Object, Object>>) redisTemplate.execute(new RedisCallback<T>() {
			@Override
			public T doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(index);
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] bKey = serializer.serialize(cacheKey);
				byte[] data = connection.get(bKey);
			    return (T) SerializationUtils.deserialize(data);
			}
		});
	}

	public Boolean setListQueue(String key, final Object obj){
    	final String cacheKey = getKey(key);
    	return redisTemplate.execute(new RedisCallback<Boolean>() {
    		@Override
    		public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
    			connection.select(index);
    			RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
    			byte[] bKey = serializer.serialize(cacheKey);
    			byte[] bValue = serializer.serialize(JSONObject.toJSONString(obj));
//    			long len = connection.lLen(bKey); // 队列长度
    			connection.lPush(bKey, bValue);
    			return true;
    		}
    	});
    }

	public Object getListQueue(String key){
		final String cacheKey = getKey(key);
		return redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(index);
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] bKey = serializer.serialize(cacheKey);
				byte[] bValue = connection.rPop(bKey);
				String str = serializer.deserialize(bValue);
				return JSON.parseObject(str, Object.class);
			}
		});
	}

	/**
	 *
	 * @param key
	 * @return
	 * @date 2019年5月17日
	 */
	public Map<String, T> getHMap(String key) {
		if( key == null || "".equals(key.trim())){
			return null;
		}
		final String cacheKey = getKey(key);
		Map<String, T> result = redisTemplate.execute(new RedisCallback<Map<String, T>>() {
			@SuppressWarnings("unchecked")
			public Map<String, T> doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(index);
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] k = serializer.serialize(cacheKey);
				Map<byte[], byte[]> vMap = connection.hGetAll(k);
				if (vMap == null) {
					return null;
				}
				Map<String, T> map = new HashMap<String, T>();
				Set<Entry<byte[], byte[]>> entrySet = vMap.entrySet();
				for(Iterator<Entry<byte[], byte[]>> it = entrySet.iterator(); it.hasNext();){
					Entry<byte[], byte[]> entry = it.next();
					String mapKey = serializer.deserialize(entry.getKey());
					T mapValue = (T) serializer.deserialize(entry.getValue());
					map.put(mapKey, mapValue);
				}
				return  map;
			}
		});
		return result;
	}

	/**
	 * 获取队列大小
	 * @param key
	 * @return
	 * @date 2019年5月17日
	 */
	public Object getListQueueSize(String key){
		final String cacheKey = getKey(key);
		return redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(index);
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] bKey = serializer.serialize(cacheKey);
				long len = connection.lLen(bKey); // 队列长度
				return len;
			}
		});
	}
}
