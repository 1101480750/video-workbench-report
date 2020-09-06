package com.zxcl.report.cache;

import com.zxcl.report.form.LoginUserForm;
import com.zxcl.report.form.LoginUserForm;
import org.springframework.stereotype.Component;

@Component
public class VideoRedis extends BaseCache<LoginUserForm>{

	public VideoRedis() {
		super(RedisConstant.DEFAULT_INDEX);
	}

	@Override
	public String getKey(String key) {
		return RedisConstant.DEFAULT_KEY + key;
	}

}
