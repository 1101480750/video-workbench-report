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
	public static final String DEFAULT_KEY = "VIDEO_REDIS_KEY_";

	/**
	 * 后台登录验证码前缀
	 */
	public static final String USER_LOGIN_CODE_KEY = "USER_LOGIN_CODE_";

	/**
	 * 后台登录用户信息缓存前缀
	 */
	public static final String USER_LOGIN_INFO_KEY = "USER_LOGIN_INFO_";

	/**
	 * 	未接通视频的会话列表
	 */
	public static final String VIDEO_WAIT_LIST_KEY = "VIDEO_LIST_";
	/**
	 * 	接通中视频的会话列表
	 */
	public static final String VIDEO_CONNECT_LIST_KEY = "VIDEO_CONNECT_LIST_";
	/**
	 * 	接通中图片的会话列表
	 */
	public static final String VIDEO_IMG_LIST_KEY = "VIDEO_IMG_LIST_";
	/**
	 * 	国任历史资源
	 */
	public static final String GUOREN_HISTROY_KEY = "GUOREN_HISTROY_LIST_ENDTIME_";
	/**
	 * 	公司logo
	 */
	public static final String COMP_LOGO_KEY = "COMP_LOGO_";


	/******************** 移动端	 ************************/
    // 定义redis用户信息的后缀
    public static final String USER_INFO = ":userInfo";
    public static final String USER_TOKEN = ":userToken";

	/**
	 * 移动端默认redis的key前缀
	 */
	public static final String MOBILE_DEFAULT_KEY = "VIDEO_MOBILE:";

}
