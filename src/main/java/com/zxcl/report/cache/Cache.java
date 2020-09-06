package com.zxcl.report.cache;

import java.util.List;
import java.util.Map;

/**
 * 缓存操作接口
 * @author Lee SZ
 * @date 2016年5月11日
 */
public interface Cache<T>{

	/**
	 * 设置缓存，默认永远不过期
	 * @param key
	 * @param value
	 * @author Lee SZ
	 * @date 2016年5月11日
	 */
	public void setCache(final String key, final T value);

	/**
	 * 设置缓存
	 * @param dbIndex		db索引
	 * @param key				缓存key
	 * @param value			缓存的value
	 * @param expires		过期时间
	 * @author Lee SZ
	 * @date 2016年5月11日
	 */
	public void setCache(String key, T value, long expires);

	/**
	 * 设置字符串的缓存
	 * @param key
	 * @param value
	 * @author Lee SZ
	 * @date 2016年7月6日
	 */
	public void setStrCache(final String key, final String value);

	/**
	 * 设置字符串的值，指定默认时间
	 * @param key
	 * @param value
	 * @param expires
	 * @author Lee SZ
	 * @date 2016年7月6日
	 */
	public void setStrCache(final String key, final String value, final long expires);

	/**
	 * 根据key删除缓存
	 * @param dbIndex		db索引
	 * @param key				要删除的缓存的key
	 */
	public void removeCache(String key);

	/**
	 * 根据key获取缓存的值
	 * @param dbIndex		db索引
	 * @param key				要获取的缓存的key
	 * @author Lee SZ
	 * @date 2016年5月11日
	 */
	public T getCache(String key, Class<T> c);

	/**
	 * 根据多个key获取value列表
	 * @param c
	 * @param keys
	 * @return
	 * @author Lee SZ
	 * @date 2016年5月11日
	 */
	public List<T> getCache(Class<T> c, String... keys);

	/**
	 * 清空缓存
	 * @author Lee SZ
	 * @date 2016年5月11日
	 */
	public void flush();

	/**
	 * 设置KEY的过期时间
	 * @param key
	 * @author Lee SZ
	 * @date 2016年5月11日
	 */
	public void expire(String key, long seconds);

	/**
	 * 获取缓存数据的key
	 * @param key
	 * @return
	 * @author Lee SZ
	 * @date 2016年5月11日
	 */
	public String getKey(String key);

	/**
	 * 获取缓存中以HASH（哈希表）结构存放的某个key的值
	 * @param key	REDIS中对应HASH的key
	 * @param field	HASH结构中的key
	 * @return	HASH结构中的value
	 * @author Lee SZ
	 * @date 2017年3月14日
	 */
	public T getMapValue(String key, String field, Class<T> c);

	/**
	 * 获取缓存中以HASH（哈希表）结构存放的数据
	 * @param key  REDIS中对应HASH的key
	 * @return  HASH（哈希表）中的数据
	 * @author Lee SZ
	 * @date 2017年3月14日
	 */
	public Map<String, T> getMap(String key, Class<T> c);

	/**
	 * 将数据存放到HASH中
	 * @param key	REDIS中对应HASH的key
	 * @param field	 HASH中的key
	 * @param value  HASH中对应的value
	 * @author Lee SZ
	 * @date 2017年3月14日
	 */
	public boolean setMapValue(String key, String field, T value);

	/**
	 * 将MAP存放到HASH中
	 * @param key
	 * @param map
	 * @author Lee SZ
	 * @date 2017年3月14日
	 */
	public void setMap(String key, Map<String, T> map);

	/**
	 * 设置列表的值
	 * @param key
	 * @param values
	 * @return
	 * @author Lee SZ
	 * @date 2017年6月3日
	 */
	public long setListValue(String key, final List<T> values);

	/**
	 * 获取列表末尾的值
	 * @param key
	 * @param c
	 * @return
	 * @author Lee SZ
	 * @date 2017年6月3日
	 */
	public T getListValue(String key, final Class<T> c);

	/**
	 * 阻塞式获取队列中的值
	 * @param key	队列的key
	 * @param tempKey	取值存入的临时队列key，用于出错之后的回滚
	 * @param timeout	超时时间
	 * @param c
	 * @return
	 */
	public T brpoplpush(String key, String tempKey, final int timeout, final Class<T> c) ;

	/**
	 * 判断HASH中是否存在该field
	 * @param key	HASH的key
	 * @param field	域的KEY
	 * @return true:exist;false:not exist
	 * @author Lee SZ
	 * @date 2017年6月16日
	 */
	public boolean hExist(String key, String field);

	/**
	 * 删除HASH中的某个域
	 * @param key	HASH的key
	 * @param field 域的KEY
	 * @return 被成功移除的域的数量
	 * @author Lee SZ
	 * @date 2017年7月12日
	 */
	public int hDel(String key, String field);

	/**
	 * 获取符合给定模式 pattern 的 key
	 * @param pattarn
	 * @return
	 * @author Lee SZ
	 * @date 2017年7月12日
	 */
	public List<String> keys(String pattarn);

	/**
	 * 返回给定 key的剩余生存时间(单位：秒)
	 * @param key
	 * @return
	 * @author Lee SZ
	 * @date 2017年7月12日
	 */
	public long ttl(String key);
}
