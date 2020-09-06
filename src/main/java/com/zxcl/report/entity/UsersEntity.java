package com.zxcl.report.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * UsersEntity
 *
 * @author zhouyaoming
 * @version 1.0.0
 * @date 2020/1/2 10:16
 */
@Data
public class UsersEntity implements Serializable {

    private Long id;

    /**
     * ç”¨æˆ·å��
     */
    private String username;

    /**
     * å¯†ç �ï¼ŒåŠ å¯†å­˜å‚¨
     */
    private String password;

    /**
     * æ³¨å†Œæ‰‹æœºå�·
     */
    private String phone;

    /**
     * æ³¨å†Œé‚®ç®±
     */
    private String email;

    private Date created;

    private Date updated;

    private static final long serialVersionUID = 1L;
}
