package com.zxcl.report.auth.security;

import com.zxcl.report.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义无加密密码验证
 *
 * @author zhouyaoming
 * @version 1.0.0
 * @create 2020-01-13 21:42
 **/
@Slf4j
public class NoEncryptPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return (String) charSequence;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public boolean matches(CharSequence charSequence, String s) {
        //密码对比 密码对 true 反之 false
        //CharSequence 数据库中的密码
        //s 前台传入的密码
        log.info("s：" + s);
        log.info("s：" + passwordEncoder().encode(s));
        log.info("charSequence++" + (String) charSequence);
        log.info("s++" + passwordEncoder().encode((String) charSequence));
        String sequence = String.valueOf((String) charSequence);
        if (!s.equals(sequence)) {
            throw new BusinessException("125", "密码信息错误");
        }
        return true;
    }
}
