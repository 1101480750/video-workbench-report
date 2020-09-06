package com.zxcl.report.utils.string;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author fanyuwen
 */
public class StringUtils {
    private StringUtils() {
        throw new RuntimeException("can't create this object " + StringUtils.class.getSimpleName() + ".");
    }

    /**
     * 判断是整数正则
     */
    private static final Pattern INTEGERPATTERN = Pattern.compile("^\\d+$");
    /**
     * 判断浮点数正则
     */
    private static final Pattern FLOATPATTERN = Pattern.compile("^\\d*\\.\\d+$");
    /**
     * IP地址正则
     */
    private static final Pattern BASE_IPPATTERN = Pattern.compile("^(\\d{1,3}\\.){3}\\d{1,3}$");

    /**
     * 定义正则表达式(用于匹配域名)
     */
    private static final String RE_TOP = "[\\w-]+\\.(com.cn|net.cn|gov.cn|org\\.nz|org.cn|com|net|org|gov|cc|biz|info|cn|co)\\b()*";

    /**
     * 域名匹配模式
     */
    private static final Pattern DOMAINPATTERN = Pattern.compile(RE_TOP, Pattern.CASE_INSENSITIVE);

    /**
     * 字符串是否为空
     * @param input 输入字符串
     * @return true:空,false:非空
     */
    public static boolean isEmpty(String input) {
        return input == null || "".equals(input.trim());
    }

    /**
     *
     * @param input 输入字符串
     * @return true:非空,false:空
     */
    public static boolean isNotEmpty(String input) {
        return !isEmpty(input);
    }

    /**
     * 是否是整数
     *
     * @param input 输入字符串
     * @return true:是整数,otherwise:不是
     */
    public static boolean isInteger(String input) {
        return INTEGERPATTERN.matcher(input).matches();
    }

    /**
     * 是否是小数
     *
     * @param input 输入字符串
     * @return true:是小数,otherwise:不是
     */
    public static boolean isFloat(String input) {
        return FLOATPATTERN.matcher(input).matches();
    }

    /**
     * 是否是ip地址
     *
     * @param input 输入字符串
     * @return true:是ip地址,otherwise:不是
     */
    public static boolean isIP(String input) {
        return BASE_IPPATTERN.matcher(input).matches();
    }

    /**
     * 本地域名
     */
    private static final String LOCALHOST = "localhost";

    /**
     * 地址最小长度
     */
    private static final int ADDR_LENGTH_MIN = 7;
    /**
     * 地址最大长度
     */
    private static final int ADDR_LENGTH_MAX = 15;

    /**
     * IP判断正则表达式
     */
    private static final String IP_REXP = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";

    /**
     * IP匹配模式
     */
    private static final Pattern IPPATTERN = Pattern.compile(IP_REXP);

    /**
     * 获取url顶级域名
     * @param url url地址
     * @return 顶级域名字符串
     */
    public static String getTopDomain(String url){
        if (isEmpty(url)) {
            return null;
        }
        String result = url.toLowerCase();
        try {
            URL currUrl = new URL(result);
            result = currUrl.getHost();
            if (url.toLowerCase().contains(LOCALHOST)) {
                // 如果是localhost
            } else if (isIp(result)) {
                // 如果是IP地址
            } else {
                // 是域名
                Matcher matcher = DOMAINPATTERN.matcher(url.toLowerCase());
                matcher.find();
                result = matcher.group();
            }
        } catch (Exception e) {e.printStackTrace();}
        return result;
    }

    /**
     * 判断IP格式和范围
     *
     * @param addr 输入的id地址
     * @return boolean
     */
    private static boolean isIp(String addr) {
        if (addr.length() < ADDR_LENGTH_MIN || addr.length() > ADDR_LENGTH_MAX) {
            return false;
        }
        Matcher mat = IPPATTERN.matcher(addr);

        return mat.find();
    }
}
