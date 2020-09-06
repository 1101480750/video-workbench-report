package com.zxcl.report.cache;

import com.zxcl.report.form.LoginUserForm;
import com.zxcl.report.info.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class WebRedis extends BaseCache<UserInfo>{

	public WebRedis() {
		super(RedisConstant.DEFAULT_INDEX);
	}

	@Override
	public String getKey(String key) {
		return RedisConstant.DEFAULT_KEY + key;
	}

}
