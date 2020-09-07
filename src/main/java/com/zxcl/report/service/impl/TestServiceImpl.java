package com.zxcl.report.service.impl;

import com.zxcl.report.common.components.MessageSourceService;
import com.zxcl.report.common.constant.MsgCdConstant;
import com.zxcl.report.common.exception.BusinessException;
import com.zxcl.report.form.LoginUserForm;
import com.zxcl.report.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private MessageSourceService mss;

    @Override
    public void getName(LoginUserForm userForm) {
        if (null == userForm) {
            throw new BusinessException(MsgCdConstant.ALIPAY_AUTHENTICATION_FAIL, mss.getOrigMessage(MsgCdConstant.ALIPAY_AUTHENTICATION_FAIL));
        }
    }
}
