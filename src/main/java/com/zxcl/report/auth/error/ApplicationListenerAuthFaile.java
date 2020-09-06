package com.zxcl.report.auth.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureDisabledEvent;
import org.springframework.stereotype.Component;

/**
 * 登录失败回调
 *
 * @author zhouyaoming
 * @version 1.0.0
 * @date 2020/1/14 11:43
 */
@Slf4j
@Component
public class ApplicationListenerAuthFaile  implements ApplicationListener<AuthenticationFailureDisabledEvent> {

    @Override
    public void onApplicationEvent(AuthenticationFailureDisabledEvent authenticationFailureDisabledEvent) {
        log.info("登录失败");
    }
}
