package com.zxcl.report.auth.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * 登录成功回调
 *
 * @author zhouyaoming
 * @version 1.0.0
 * @date 2020/1/14 11:41
 */
@Slf4j
@Component
public class ApplicationListenerAuthencationSuccess implements ApplicationListener<AuthenticationSuccessEvent> {

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent authenticationSuccessEvent) {
        log.info("登录成功");
    }
}
