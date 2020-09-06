package com.zxcl.report.form;

import lombok.Data;

import java.util.List;

/**
 * @author Zhifeng.Zeng
 * @description oauth2客户端token参数
 * @date 2019/3/8
 */
@Data
public class TokenForm {

    /**
     * 过期时间
     */
    private String expiration;
    /**
     * 是否过期
     */
    private boolean expired;
    /**
     * 过期时限
     */
    private int expiresIn;
    /**
     * refreshToken对象
     */
    private RefreshTokenForm refreshToken;

    /**
     * token类型
     */
    private String tokenType;

    /**
     * access_token值
     */
    private String value;

    /**
     * 使用范围
     */
    private List<String> scope;

}
