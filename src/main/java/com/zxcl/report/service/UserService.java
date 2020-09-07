package com.zxcl.report.service;

import com.zxcl.report.common.response.RestResponse;
import com.zxcl.report.form.LoginUserForm;
import com.zxcl.report.info.UserInfo;

/**
 * 用户
 *
 * @author zhouyaoming
 * @version 1.0.0
 * @date 2020/9/4 17:30
 */
public interface UserService {


    /**
     * 用户登录
     *
     * @param loginUserForm
     * @return
     */
    UserInfo login(LoginUserForm loginUserForm);

    /**
     * 用户登出
     * @param token
     */
    void logout(String token);
}
