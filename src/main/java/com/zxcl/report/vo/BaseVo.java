package com.zxcl.report.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础信息
 *
 * @author zhouyaoming
 * @create 2019-05-24 7:43
 **/
@Data
public class BaseVo implements Serializable {

    private static final long serialVersionUID = -5028412161951874201L;
    protected Long guid;

    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private LocalDateTime addTime;

    private String addUser;

    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private LocalDateTime modTime;

    private String modUser;

    private Long addUserId;

    private Long modUserId;

    private Boolean deleteFlag ;
}
