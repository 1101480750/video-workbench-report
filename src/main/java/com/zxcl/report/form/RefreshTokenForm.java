package com.zxcl.report.form;

import lombok.Data;

@Data
public class RefreshTokenForm {

    /**
     * 过期时间
     */
    private String expiration;
    /**
     * token值
     */
    private String value;

}
