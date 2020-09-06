package com.zxcl.report.cache;

import org.springframework.stereotype.Component;

@Component
public class MobileRedis extends BaseCache<Object>{

	public MobileRedis() {
		super(RedisConstant.DEFAULT_INDEX);
	}

	@Override
	public String getKey(String key) {
		return RedisConstant.MOBILE_DEFAULT_KEY + key;
	}

}
