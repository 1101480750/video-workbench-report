package com.zxcl.report.utils.string;

import org.slf4j.helpers.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * 字符串工具类
 *
 * @author YAZUO)DU-YI
 * @version 0.1 Date: 2017/11/13
 */
public final class YazuoStringUtils {
    /** 占位符：开始 */
    private static final char DELIM_START = '{';
    /** 占位符：结束 */
    @SuppressWarnings("unused")
    private static final char DELIM_STOP = '}';
    /** 占位符 */
    private static final String DELIM_STR = "{}";
    /** 转义字符 */
    private static final char ESCAPE_CHAR = '\\';
    /** Double类型转义字符位置 */
    private static final int DOUBLE_ESCAPED_INDEX = 2;
    /** 默认集合初始化 */
    private static final int DEFAULT_COLLECTION_CAPACITY = 10;

    /**
     * unicode转中文
     *
     * @param str
     *            String (含)unicode字符
     * @return String 中文字符串
     */
    public static String unicodeToString(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        Pattern pattern = compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }

    /**
     * 转义含有`{}`占位符的字符串
     *
     * @param messagePattern
     *            含有`{}`占位符的字符串
     * @param argArray
     *            替换参数
     * @return 转义后的字符串
     */
    public static String formatMessage(String messagePattern, Object[] argArray) {
        if (messagePattern == null) {
            return null;
        }

        if (argArray == null) {
            return messagePattern;
        }

        int i = 0;
        int j;
        // 多核环境使用StringBuilder性能较好
        StringBuilder sbuf = new StringBuilder(messagePattern.length() + 50);

        int inx;
        for (inx = 0; inx < argArray.length; inx++) {

            j = messagePattern.indexOf(DELIM_STR, i);

            if (j == -1) {
                if (i == 0) {
                    // 只是单纯字符串
                    return messagePattern;
                } else {
                    // 已没有可替换的变量，结尾追加剩余的字符串
                    sbuf.append(messagePattern, i, messagePattern.length());
                    return sbuf.toString();
                }
            } else {
                if (isEscapedDelimeter(messagePattern, j)) {
                    if (!isDoubleEscaped(messagePattern, j)) {
                        inx--;
                        sbuf.append(messagePattern, i, j - 1);
                        sbuf.append(DELIM_START);
                        i = j + 1;
                    } else {
                        sbuf.append(messagePattern, i, j - 1);
                        deeplyAppendParameter(sbuf, argArray[inx],
                                new HashMap<Object[], Object>(DEFAULT_COLLECTION_CAPACITY));
                        i = j + 2;
                    }
                } else {
                    sbuf.append(messagePattern, i, j);
                    deeplyAppendParameter(sbuf, argArray[inx],
                            new HashMap<Object[], Object>(DEFAULT_COLLECTION_CAPACITY));
                    i = j + 2;
                }
            }
        }
        sbuf.append(messagePattern, i, messagePattern.length());
        return sbuf.toString();
    }

    /**
     * 是否含有转义字符
     *
     * @param messagePattern
     *            匹配字符串
     * @param delimeterStartIndex
     *            位置
     * @return true:含，false:不含
     */
    final static boolean isEscapedDelimeter(String messagePattern, int delimeterStartIndex) {

        if (delimeterStartIndex == 0) {
            return false;
        }
        char potentialEscape = messagePattern.charAt(delimeterStartIndex - 1);
        if (potentialEscape == ESCAPE_CHAR) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否含有Double类型转义字符
     *
     * @param messagePattern
     *            匹配字符串
     * @param delimeterStartIndex
     *            位置
     * @return true:含，false:不含
     */
    final static boolean isDoubleEscaped(String messagePattern, int delimeterStartIndex) {
        if (delimeterStartIndex >= DOUBLE_ESCAPED_INDEX
                && messagePattern.charAt(delimeterStartIndex - DOUBLE_ESCAPED_INDEX) == ESCAPE_CHAR) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 拼接字符串
     *
     * @param sbuf
     *            StringBuilder
     * @param o
     *            对象
     * @param seenMap
     *            Map
     */
    private static void deeplyAppendParameter(StringBuilder sbuf, Object o, Map<Object[], Object> seenMap) {
        if (o == null) {
            sbuf.append("null");
            return;
        }
        if (!o.getClass().isArray()) {
            safeObjectAppend(sbuf, o);
        } else {
            if (o instanceof boolean[]) {
                booleanArrayAppend(sbuf, (boolean[]) o);
            } else if (o instanceof byte[]) {
                byteArrayAppend(sbuf, (byte[]) o);
            } else if (o instanceof char[]) {
                charArrayAppend(sbuf, (char[]) o);
            } else if (o instanceof short[]) {
                shortArrayAppend(sbuf, (short[]) o);
            } else if (o instanceof int[]) {
                intArrayAppend(sbuf, (int[]) o);
            } else if (o instanceof long[]) {
                longArrayAppend(sbuf, (long[]) o);
            } else if (o instanceof float[]) {
                floatArrayAppend(sbuf, (float[]) o);
            } else if (o instanceof double[]) {
                doubleArrayAppend(sbuf, (double[]) o);
            } else {
                objectArrayAppend(sbuf, (Object[]) o, seenMap);
            }
        }
    }

    /**
     * 普通对象字符串拼接
     *
     * @param sbuf
     *            StringBuilder
     * @param o
     *            拼接对象
     */
    private static void safeObjectAppend(StringBuilder sbuf, Object o) {
        try {
            String oAsString = o.toString();
            sbuf.append(oAsString);
        } catch (Throwable t) {
            Util.report("SLF4J: Failed toString() invocation on an object of type [" + o.getClass().getName() + "]", t);
            sbuf.append("[FAILED toString()]");
        }

    }

    /**
     * 普通对象字符串拼接
     *
     * @param sbuf
     *            StringBuilder
     * @param a
     *            拼接对象
     * @param seenMap
     *            参数
     */
    private static void objectArrayAppend(StringBuilder sbuf, Object[] a, Map<Object[], Object> seenMap) {
        sbuf.append('[');
        if (!seenMap.containsKey(a)) {
            seenMap.put(a, null);
            final int len = a.length;
            for (int i = 0; i < len; i++) {
                deeplyAppendParameter(sbuf, a[i], seenMap);
                if (i != len - 1) {
                    sbuf.append(", ");
                }
            }
            // allow repeats in siblings
            seenMap.remove(a);
        } else {
            sbuf.append("...");
        }
        sbuf.append(']');
    }

    /**
     * Boolean类型字符串拼接
     *
     * @param sbuf
     *            StringBuilder
     * @param a
     *            参数
     */
    private static void booleanArrayAppend(StringBuilder sbuf, boolean[] a) {
        sbuf.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
        }
        sbuf.append(']');
    }

    /**
     * byte类型字符串拼接
     *
     * @param sbuf
     *            StringBuilder
     * @param a
     *            参数
     */
    private static void byteArrayAppend(StringBuilder sbuf, byte[] a) {
        sbuf.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
        }
        sbuf.append(']');
    }

    /**
     * char类型字符串拼接
     *
     * @param sbuf
     *            StringBuilder
     * @param a
     *            参数
     */
    private static void charArrayAppend(StringBuilder sbuf, char[] a) {
        sbuf.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
        }
        sbuf.append(']');
    }

    /**
     * short类型字符串拼接
     *
     * @param sbuf
     *            StringBuilder
     * @param a
     *            参数
     */
    private static void shortArrayAppend(StringBuilder sbuf, short[] a) {
        sbuf.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
        }
        sbuf.append(']');
    }

    /**
     * int类型字符串拼接
     *
     * @param sbuf
     *            StringBuilder
     * @param a
     *            参数
     */
    private static void intArrayAppend(StringBuilder sbuf, int[] a) {
        sbuf.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
        }
        sbuf.append(']');
    }

    /**
     * long类型字符串拼接
     *
     * @param sbuf
     *            StringBuilder
     * @param a
     *            参数
     */
    private static void longArrayAppend(StringBuilder sbuf, long[] a) {
        sbuf.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
        }
        sbuf.append(']');
    }

    /**
     * float类型字符串拼接
     *
     * @param sbuf
     *            StringBuilder
     * @param a
     *            参数
     */
    private static void floatArrayAppend(StringBuilder sbuf, float[] a) {
        sbuf.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
        }
        sbuf.append(']');
    }

    /**
     * double类型字符串拼接
     *
     * @param sbuf
     *            StringBuilder
     * @param a
     *            参数
     */
    private static void doubleArrayAppend(StringBuilder sbuf, double[] a) {
        sbuf.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
        }
        sbuf.append(']');
    }
}
