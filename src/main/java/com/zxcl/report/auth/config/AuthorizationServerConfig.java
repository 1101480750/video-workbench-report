package com.zxcl.report.auth.config;

import com.zxcl.report.auth.error.MssWebResponseExceptionTranslator;
import com.zxcl.report.auth.utils.AuthConstant;
import com.zxcl.report.form.LoginUserForm;
import com.zxcl.report.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * 认证服务器
 *
 * @author zhouyaoming
 * @version 1.0.0
 * @create 2020-01-13 21:29
 **/

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LettuceConnectionFactory lettuceConnectionFactory;

    @Autowired
    private UserDetailsServiceImpl userDetailService;

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(lettuceConnectionFactory);
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //允许表单认证
//        security.allowFormAuthenticationForClients().tokenKeyAccess("isAuthenticated()")
//                .checkTokenAccess("permitAll()");
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    /**
     * 配置 oauth_client_details【client_id和client_secret等】信息的认证【检查ClientDetails的合法性】服务
     * 设置 认证信息的来源：数据库 (可选项：数据库和内存,使用内存一般用来作测试)
     * 自动注入：ClientDetailsService的实现类 JdbcClientDetailsService (检查 ClientDetails 对象)
     * 这个方法主要是用于校验注册的第三方客户端的信息，可以存储在数据库中，默认方式是存储在内存中，如下所示，注释掉的代码即为内存中存储的方式
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // clients.withClientDetails(clientDetails());
        clients.inMemory()
                .withClient(AuthConstant.CLIENT_ID)
                //密码模式及refresh_token模式
                .authorizedGrantTypes(AuthConstant.GRANT_TYPE[0], AuthConstant.GRANT_TYPE[1])
                .scopes("all")
                .secret(AuthConstant.CLIENT_ID);
    }

    @Bean
    public WebResponseExceptionTranslator webResponseExceptionTranslator() {
        return new MssWebResponseExceptionTranslator();
    }


    /**
     * 定义jwt的生成方式
     *
     * @return JwtAccessTokenConverter
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter() {
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                final Map<String, Object> additionalInformation = new HashMap<>();
                User userModel = (User) authentication.getUserAuthentication().getPrincipal();
                // 查詢用戶
                LoginUserForm usersForm = new LoginUserForm();
                usersForm.setAccount("zhouyaoming");
//                Member member = memberDao.findByMemberName(userModel.getUsername());
                //把用户的主键uin放进去
                additionalInformation.put("userName", usersForm.getAccount());
//                additionalInformation.put("mobile", usersInfo.getTelephone());
//                additionalInformation.put("email", usersInfo.getEmail());
//                additionalInformation.put("id", usersInfo.getGuid());
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
                return super.enhance(accessToken, authentication);
            }
        };
        //非对称加密，但jwt长度过长
//        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("kevin_key.jks"), "123456".toCharArray())
//                .getKeyPair("kevin_key");
//        converter.setKeyPair(keyPair);
//        return converter;
        //对称加密
        converter.setSigningKey("123");
        return converter;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .userDetailsService(userDetailService)
                .authenticationManager(authenticationManager)
                // 配置JwtAccessToken转换器
                .accessTokenConverter(accessTokenConverter());
        endpoints.tokenServices(defaultTokenServices());

        //认证异常翻译
        endpoints.exceptionTranslator(webResponseExceptionTranslator());
    }


    /**
     * <p>注意，自定义TokenServices的时候，需要设置@Primary，否则报错，</p>
     * 自定义的token
     * 认证的token是存到redis里的
     *
     * @return
     */
    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
//        tokenServices.setClientDetailsService(clientDetails());
        // token有效期自定义设置，默认12小时
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 12);
        // refresh_token默认30天
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);
        return tokenServices;
    }
}
