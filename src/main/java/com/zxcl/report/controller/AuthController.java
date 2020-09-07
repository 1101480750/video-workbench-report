package com.zxcl.report.controller;

import com.zxcl.report.common.request.RestRequest;
import com.zxcl.report.common.response.RestResponse;
import com.zxcl.report.form.LoginUserForm;
import com.zxcl.report.service.UserService;
import com.zxcl.report.utils.AssertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 登录
 *
 * @author zhouyaoming
 * @version 1.0.0
 * @date 2020/9/4 15:57
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthController {


    @Autowired
    private UserService userService;

    /**
     * 用户登录.
     *
     * @return the rest
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse loginSuccess(@Validated @RequestBody RestRequest<LoginUserForm> request) {
        LoginUserForm userForm = request.getBody();
        return RestResponse.success(userService.login(userForm));
    }


    /**
     * @description 用户注销
     * @param authorization
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse logout(@RequestHeader("Authorization") String authorization){
        userService.logout(AssertUtils.extracteToken(authorization));
        return RestResponse.success();
    }
}
