package com.zxcl.report.cache;

/**
 * Redis常量类
 *
 * @author Lee SZ
 * @date 2016年5月11日
 */
public class RedisConstant {

	/**
	 * 默认过期时间
	 */
	public static final long DEFAULT_EXPIRES = Integer.MAX_VALUE;

	/**
	 * 默认index
	 */
	public static final int DEFAULT_INDEX = 0;

	/**
	 * 默认redis的key前缀
	 */
	public static final String DEFAULT_KEY = "MOBILE_REDIS_KEY_";

	/******************** 移动端	 ************************/
    // 定义redis用户信息的后缀
    public static final String USER_INFO = ":userInfo";
    public static final String USER_TOKEN = ":userToken";

	/**
	 * 移动端默认redis的key前缀
	 */
	public static final String MOBILE_DEFAULT_KEY = "VIDEO_MOBILE:";

}
