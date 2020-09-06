package com.zxcl.report.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.zxcl.report.entity.UsersEntity;
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

//    @Autowired
//    private UsersService userService;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("username:".concat(userName));
        UsersEntity user = null;

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if (user != null){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("/SystemContent");
            grantedAuthorities.add(grantedAuthority);
        }
        return new org.springframework.security.core.userdetails.User("zhouyaoming", "123456",grantedAuthorities);

    }
}
