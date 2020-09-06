package com.zxcl.report.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qcloud.cos.utils.Md5Utils;
import com.zxcl.report.auth.utils.AuthConstant;
import com.zxcl.report.cache.WebRedis;
import com.zxcl.report.common.components.MessageSourceService;
import com.zxcl.report.common.constant.MsgCdConstant;
import com.zxcl.report.common.exception.BusinessException;
import com.zxcl.report.common.response.RestResponse;
import com.zxcl.report.enums.UrlEnum;
import com.zxcl.report.form.LoginUserForm;
import com.zxcl.report.form.TokenForm;
import com.zxcl.report.info.LoginUserInfo;
import com.zxcl.report.info.UserInfo;
import com.zxcl.report.mapper.UserMapper;
import com.zxcl.report.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Optional;
/**
 * 用户
 *
 * @author zhouyaoming
 * @version 1.0.0
 * @date 2020/9/4 17:33
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MessageSourceService mss;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private WebRedis webRedis;

    @Autowired
    private UserMapper userMapper;

    /**
     * 用戶登录
     * @param loginUserForm
     * @return
     */
    @Override
    public UserInfo login(LoginUserForm loginUserForm) {
        TokenForm token = null;
        UserInfo userInfo = null;
        try {
            // 校验账号是否为空
            if (StringUtils.isEmpty(loginUserForm.getAccount())){
                throw new BusinessException(MsgCdConstant.LOGIN_USER_ACCOUNT_EMPTY, mss.getOrigMessage(MsgCdConstant.LOGIN_USER_ACCOUNT_EMPTY));
            }
            // 校验密码是否为空
            if (StringUtils.isEmpty(loginUserForm.getPassword())){
                throw new BusinessException(MsgCdConstant.LOGIN_USER_PASSWORD_EMPTY, mss.getOrigMessage(MsgCdConstant.LOGIN_USER_PASSWORD_EMPTY));
            }
            userInfo = userMapper.getUserByLoginName(loginUserForm.getAccount());
            // 校验用户信息是否为空
            if (null == loginUserForm) {
                throw new BusinessException(MsgCdConstant.LOGIN_USER_INFO_EMPTY, mss.getOrigMessage(MsgCdConstant.LOGIN_USER_INFO_EMPTY));
            }
            // 将前端密码进行加密
            String password = Md5Utils.md5Hex(loginUserForm.getPassword().getBytes());
            // 校验密码是否重复
            if (!password.equals(userInfo.getPassword())) {
                throw new BusinessException(MsgCdConstant.LOGIN_USER_PASSWORD_ERROR, mss.getOrigMessage(MsgCdConstant.LOGIN_USER_PASSWORD_ERROR));
            }
            MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
            paramMap.add("client_id", AuthConstant.CLIENT_ID);
            paramMap.add("client_secret", AuthConstant.CLIENT_SECRET);
            paramMap.add("grant_type", AuthConstant.GRANT_TYPE[0]);
            paramMap.add("username", loginUserForm.getAccount());
            paramMap.add("password", password);
            /**
             * 因为oauth2本身自带的登录接口是"/oauth/token"，并且返回的数据类型不能按我们想要的去返回
             * 但是业务需求:登录接口是"auth/login"，由于我没研究过要怎么去修改oauth2内部的endpoint配置
             * 所以这里我用restTemplate(HTTP客户端)进行一次转发到oauth2内部的登录接口
             */
            String url = "http://localhost:40251" + UrlEnum.LOGIN_URL.getUrl();
            // 发送请求获取token
            JSONObject loginObject  = restTemplate.postForObject(url, paramMap, JSONObject.class);
            // 取出token
            String accessToken = loginObject.getString("access_token");
            UserInfo loginInfo = webRedis.getCache(accessToken, UserInfo.class);
            // 判断是否已经登录
            if (null != loginInfo) {
                webRedis.hDel(accessToken, "");
            }
            webRedis.setCache(accessToken, userInfo);
        } catch (RestClientException e) {
            try {
                e.printStackTrace();
                //此处应该用自定义异常去返回，在这里我就不去具体实现了
                //throw new Exception("username or password error");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        //这里我拿到了登录成功后返回的token信息之后，我再进行一层封装，最后返回给前端的其实是LoginUserVO
        UserInfo loginUser = new UserInfo();
        loginUser.setUserId(userInfo.getUserId());
        loginUser.setUserName(userInfo.getUserName());
        return loginUser;
    }
}
