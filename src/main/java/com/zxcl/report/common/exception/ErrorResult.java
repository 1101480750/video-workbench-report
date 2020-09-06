package com.zxcl.report.common.exception;

import com.zxcl.report.common.response.RestResponse;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;

/**
 * 错误信息
 *
 * @author YAZUO)WU-TONG
 */
@Data
public class ErrorResult implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -2818937208930967841L;

    /** httpstatus */
    private HttpStatus httpStatus;

    /** 错误码 */
    private String code;

    /** 异常信息 */
    private String message;

    /** 二级错误码 */
    private String subCode;

    /** 二级异常信息 */
    private String subMessage;

    /** 异常时间 */
    private Date date;

    /** 异常类名 */
    private String type;

    /** 详细异常堆栈信息 */
    private String stackTrace;

    /** 子异常 */
    private RestResponse<String> child;

    /** 异常调用栈信息 */
    private StackTraceElement[] stackTraceElements;

    /**
     * setter
     *
     * @param date
     */
    public void setDate(Date date) {
        if (date != null) {
            this.date = (Date) date.clone();
        } else {
            this.date = null;
        }
    }

    /**
     * setter
     *
     * @param stackTraceElements
     */
    public void setStackTraceElements(StackTraceElement[] stackTraceElements) {
        if (stackTraceElements != null) {
            this.stackTraceElements = stackTraceElements.clone();
        } else {
            this.stackTraceElements = null;
        }
    }

    /**
     * getter
     *
     * @return
     */
    public Date getDate() {
        if (date == null) {
            return null;
        }
        return (Date) date.clone();
    }

    /**
     * getter
     *
     * @return
     */
    public StackTraceElement[] getStackTraceElements() {
        if (stackTraceElements == null) {
            return null;
        }
        return stackTraceElements.clone();
    }
}
