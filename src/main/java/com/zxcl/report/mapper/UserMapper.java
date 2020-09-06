package com.zxcl.report.mapper;

import com.zxcl.report.info.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 后台账号管理
 * @author zhouyaoming
 */
@Repository
public interface UserMapper {

    /**
     * 根据用户名查询用户
     *
     * @param loginName
     * @return
     */
    UserInfo getUserByLoginName(@Param("loginName") String loginName);

}