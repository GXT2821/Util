package com.guotao.string;

/**
 * String工具类，复用org.apache.commons.lang.StringUtils类的一些实现
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断字符串去除左右空格之后是否为空
     * @param str
     * @return
     */
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    /**
     * 返回去除字符串左右空格后的结果值
     * @param str
     * @return
     */
    public static String clean(String str) {
        return str == null ? "" : str.trim();
    }

    /**
     * 判断字符创是否相等
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }

    /**
     * 判断字符串是否相等，忽略大小写
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
    }

}
