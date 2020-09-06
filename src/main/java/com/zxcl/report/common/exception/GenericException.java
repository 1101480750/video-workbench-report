package com.zxcl.report.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author YAZUO)WU-TONG
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GenericException extends RuntimeException {

    /** serialVersionUID */
    private static final long serialVersionUID = -2269536687119318933L;

    /** code */
    private String code;

    @SuppressWarnings(value = "all")
    public GenericException() {
        super();
    }

    @SuppressWarnings(value = "all")
    public GenericException(String code, String message) {
        super(message);
        this.code = code;
    }

    @SuppressWarnings(value = "all")
    public GenericException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
