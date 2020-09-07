package com.zxcl.report.auth.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxcl.report.common.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 授权异常信息处理
 *
 * @author zhouyaoming
 * @version 1.0.0
 * @date 2020/1/14 11:28
 */
@Slf4j
@Component
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

    /**
     * 授权异常
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Map map = new HashMap();
        map.put("message", authException.getMessage());
        map.put("path", request.getServletPath());
        map.put("timestamp", String.valueOf(LocalDateTime.now()));
        RestResponse restResponse = RestResponse.buildWithCode("401", "暂时无权限");
        restResponse.setBody(map);
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), restResponse);
        } catch (Exception e) {
            throw new ServletException();
        }
    }
}
