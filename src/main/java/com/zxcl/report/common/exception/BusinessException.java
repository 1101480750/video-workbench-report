package com.zxcl.report.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务异常
 *
 * @author YAZUO)WU-TONG
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends GenericException {

    private static final long serialVersionUID = -4435979898433185113L;

    @SuppressWarnings(value = "all")
    public BusinessException(String code, String message) {
        super(code, message);
    }

    @SuppressWarnings(value = "all")
    public BusinessException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
