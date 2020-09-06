package com.zxcl.report.common.exception;

import lombok.Data;

/**
 * 用于参数校验的字段错误信息
 *
 * @author YAZUO)DU-YI
 * @version 0.1 Date: 2017/12/12
 */
@Data
public class FieldError {
    private String name;
    private String message;
}
