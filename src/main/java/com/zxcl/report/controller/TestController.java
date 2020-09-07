package com.zxcl.report.controller;

import com.zxcl.report.common.request.RestRequest;
import com.zxcl.report.common.response.RestResponse;
import com.zxcl.report.form.LoginUserForm;
import com.zxcl.report.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试controller
 *
 * @author zhouyaoming
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    /**
     * 获取名称
     *
     * @param request
     * @return
     * @author wentao
     * @time 2018/03/21
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse listAclSysVersions(@Validated @RequestBody RestRequest<LoginUserForm> request) {
        LoginUserForm loginUserForm = request.getBody();
        testService.getName(loginUserForm);
        return RestResponse.success("25");
    }
}
