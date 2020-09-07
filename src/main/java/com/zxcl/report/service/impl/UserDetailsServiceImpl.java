package com.zxcl.report.service.impl;

import com.zxcl.report.info.UserInfo;
import com.zxcl.report.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息认证实现
 *
 * @author zhouyaoming
 * @version 1.0.0
 * @date 2020/1/2 10:14
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户信息获取
     *
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("username:".concat(userName));
        // 获取用户信息
        UserInfo userInfo = userMapper.getUserByLoginName(userName);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        // 判断用户信息是否为空
        if (userInfo != null) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("/SystemContent");
            grantedAuthorities.add(grantedAuthority);
        }
        return new org.springframework.security.core.userdetails.User(userInfo.getLoginName(), userInfo.getPassword(), grantedAuthorities);

    }
}
