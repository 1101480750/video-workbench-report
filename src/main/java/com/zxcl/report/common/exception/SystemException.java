package com.zxcl.report.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统异常
 *
 * @author YAZUO)WU-TONG
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SystemException extends GenericException {

    private static final long serialVersionUID = 1009266812138718174L;

    @SuppressWarnings(value = "all")
    public SystemException(String code, String message) {
        super(code, message);
    }

    public SystemException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
