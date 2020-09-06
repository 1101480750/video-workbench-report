package com.zxcl.report.common.handler;

import com.zxcl.report.common.components.MessageSourceService;
import com.zxcl.report.common.exception.BusinessException;
import com.zxcl.report.common.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 *
 * @author zhouyaoming
 * @version 1.0.0
 * @create 2019-03-17 11:45
 **/
@ControllerAdvice
@Slf4j
public class ExceptionHandle {


    @Autowired
    private MessageSourceService mss;

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RestResponse handle(Exception e) {
        log.info("Exception:",e);
        if (e instanceof BusinessException) {
            log.info("mss.getOrigMessage(((BusinessException) e).getCode()):" + mss.getOrigMessage(((BusinessException) e).getCode()));
            log.info("((BusinessException) e).getCode():" + ((BusinessException) e).getMessage());
            BusinessException businessException = (BusinessException) e;
            log.info("businessException:" + e);
            return RestResponse.buildWithCode(businessException.getCode(), businessException.getMessage());
        }else {
            log.error("【系统异常】{}", e);
            return RestResponse.buildWithCode("1101480750","未知错误");
        }
    }
}
