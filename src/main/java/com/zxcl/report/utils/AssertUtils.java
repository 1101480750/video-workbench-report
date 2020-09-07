package com.zxcl.report.utils;

public class AssertUtils {
    /**
     * @description 获取请求头的token
     * @param authToken
     * @return
     */
    public static String extracteToken(String authToken){
        String authTokenPrefix="bearer";
        if(authToken.indexOf(authTokenPrefix)!=-1){
            return authToken.substring(7);
        }else {
            return authToken;
        }
    }
}
