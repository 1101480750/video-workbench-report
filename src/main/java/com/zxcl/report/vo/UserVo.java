package com.zxcl.report.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @author zhouyaoming
 * @create 2019-05-24 7:41
 **/
@Data
public class UserVo extends BaseVo implements Serializable {

    private static final long serialVersionUID = -5028412161951874201L;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户电话号码
     */
    private String telephone;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 角色类型：1管理员 2普通员工
     */
    private Integer role;

    /**
     * 启用停用：1启用 0停用
     */
    private Boolean enableFlag;

    /**
     * 初始化flag 0：密码未修改  1：密码已修改
     */
    private Boolean initFlag;
}
