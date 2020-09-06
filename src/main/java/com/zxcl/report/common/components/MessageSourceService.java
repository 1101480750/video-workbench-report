package com.zxcl.report.common.components;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * @author wutong 2017/11/13 11:24 Created
 */
@Component
public class MessageSourceService {

    @Resource
    private MessageSource messageSource;

    /**
     * 根据code获取message
     *
     * @param code code
     * @return message
     */
    public String getMessage(String code) {
        return getMessage(code, null);
    }

    /**
     * @param code ：对应messages配置的key.
     * @param args : 数组参数.
     * @return
     */
    public String getMessage(String code, Object[] args) {
        return getMessage(code, args, "");
    }

    /**
     *
     * @param code 对应messages配置的key.
     * @param args 数组参数.
     * @param defaultMessage 没有设置key的时候的默认值.
     * @return message
     */
    public String getMessage(String code, Object[] args, String defaultMessage) {
        //这里使用比较方便的方法，不依赖request.
        Locale locale = LocaleContextHolder.getLocale();
        final String msg = "[" + code + "]" + messageSource.getMessage(code, args, defaultMessage, locale);
        return msg;
    }

    /**
     * 根据code获取原始message(message前不拼接[code])
     *
     * @param code code
     * @return message
     */
    public String getOrigMessage(String code) {
        return getOrigMessage(code, null);
    }

    /**
     * @param code ：对应messages配置的key.
     * @param args : 数组参数.
     * @return
     */
    public String getOrigMessage(String code, Object[] args) {
        return getOrigMessage(code, args, "");
    }

    /**
     *
     * @param code 对应messages配置的key.
     * @param args 数组参数.
     * @param defaultMessage 没有设置key的时候的默认值.
     * @return message
     */
    public String getOrigMessage(String code, Object[] args, String defaultMessage) {
        //这里使用比较方便的方法，不依赖request.
        final Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }
}
