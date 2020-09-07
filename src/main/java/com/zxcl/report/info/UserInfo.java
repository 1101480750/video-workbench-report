package com.zxcl.report.info;

import lombok.Data;

import java.util.List;

/**
 * @author Generator
 * @comment 管理后台账号表
 * @date 2019-12-03
 */
@Data
public class UserInfo implements java.io.Serializable {
    /**
     * 账号主键，U + 日期 + 序列号
     */
    private String userId;
    /**
     * 账号所属合作伙伴公司id
     */
    private String compId;
    /**
     * 账号所属分公司id
     */
    private String deptId;
    /**
     * 账号所属分公司id
     */
    private List<String> deptIdList;
    /**
     * 账号角色id
     */
    private String roleId;
    /**
     * 账号角色id
     */
    private List<String> roleIdList;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private java.util.Date createTime;
    /**
     * 修改人
     */
    private String updateBy;
    /**
     * 修改时间
     */
    private java.util.Date updateTime;

    private String updateDateStr;
    /**
     * 合作伙伴公司系统工号
     */
    private String compUserCode;
    /**
     * 登录账号
     */
    private String loginName;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private String mobilePhone;
    /**
     * 真实姓名
     */
    private String userName;
    /**
     * 状态，1正常，2禁用
     */
    private String status;
    /**
     * 通知声音，1开启，0关闭
     */
    private String voiceFlag;
    /**
     * 开启通知：1开启，0关闭
     */
    private String msgFlag;
    /**
     * 是否可以视频，1可以，0不可以
     */
    private String videoFlag;
    /**
     * 可用标识，1可用，0不可用
     */
    private String enableFlag;


    /**
     * 角色名
     */
    private String roleName;
    /**
     * 公司名
     */
    private String compName;
    /**
     * 机构名
     */
    private String deptName;
    /**
     * 状态
     */
    private String statusName;

    /**
     * 险种权限List
     */
    private List<String> insTypeList;

    /**
     * token
     */
    private String token;


}
