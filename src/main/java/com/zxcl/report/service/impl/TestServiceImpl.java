package com.zxcl.report.service.impl;

import com.zxcl.report.common.components.MessageSourceService;
import com.zxcl.report.common.constant.MsgCdConstant;
import com.zxcl.report.common.exception.BusinessException;
import com.zxcl.report.service.TestService;
import com.zxcl.report.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private MessageSourceService mss;
    @Override
    public void getName(UserVo userVo) {
        if (null == userVo.getUsername()) {
            throw new BusinessException(MsgCdConstant.ALIPAY_AUTHENTICATION_FAIL, mss.getOrigMessage(MsgCdConstant.ALIPAY_AUTHENTICATION_FAIL));
        }
    }
}
