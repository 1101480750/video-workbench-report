package com.zxcl.report.auth.utils;

/**
 * 授权常量
 *
 * @author zhouyaoming
 * @version 1.0.0
 * @create 2020-09-06 21:29
 **/
public class AuthConstant {


    public static final String ROLE_ADMIN = "ADMIN";
    /**
     * 访问客户端密钥
     */
    public static final String CLIENT_SECRET = "video-workbench-report";
    /**
     * 访问客户端ID
     */
    public static final String CLIENT_ID = "video-workbench-report";
    /**
     * 鉴权模式
     */
    public static final String GRANT_TYPE[] = {"password", "refresh_token"};
}
