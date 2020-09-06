package com.zxcl.report.form;

import lombok.Data;

/**
 * 用户登录
 *
 * @author zhouyaoming
 * @version 1.0.0
 * @date 2020/9/4 17:31
 */
@Data
public class LoginUserForm {

    /**
     * 用户名
     */
    private String account;

    /**
     * 用户密码
     */
    private String password;
}
