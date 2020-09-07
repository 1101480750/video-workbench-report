package com.zxcl.report.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import com.zxcl.report.auth.error.AuthExceptionEntryPoint;
import javax.servlet.http.HttpServletResponse;

/**
 * 资源认证服务器
 *
 * @author zhouyaoming
 * @version 1.0.0
 * @create 2020-01-13 21:40
 **/

@Configuration
@EnableResourceServer
@Order(3)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//                .and()
//                .requestMatchers().antMatchers("/api/**")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/oauth/**").authenticated()
//                .and()
//                .httpBasic();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                //请求权限配置
                .authorizeRequests()
                //下边的路径放行,不需要经过认证
                .antMatchers("/oauth/*", "/auth/login").permitAll()
                //OPTIONS请求不需要鉴权
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                //用户的增删改接口只允许管理员访问
//                .antMatchers(HttpMethod.POST, "/auth/user").hasAnyAuthority(ROLE_ADMIN)
//                .antMatchers(HttpMethod.PUT, "/auth/user").hasAnyAuthority(ROLE_ADMIN)
//                .antMatchers(HttpMethod.DELETE, "/auth/user").hasAnyAuthority(ROLE_ADMIN)
//                //获取角色 权限列表接口只允许系统管理员及高级用户访问
//                .antMatchers(HttpMethod.GET, "/auth/role").hasAnyAuthority(ROLE_ADMIN)
                //其余接口没有角色限制，但需要经过认证，只要携带token就可以放行
                .anyRequest()
                .authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(new AuthExceptionEntryPoint());
    }
}
