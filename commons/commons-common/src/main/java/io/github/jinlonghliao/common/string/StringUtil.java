package io.github.jinlonghliao.common.string;


import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author liaojinlong
 * @since 2020/9/29 10:24
 */
public class StringUtil {

    public static StringBuilder newStringBuilder() {
        return new StringBuilder();
    }

    public static StringBuffer newStringBuffer() {
        return new StringBuffer();
    }

    public static boolean isEmpty(String str) {
        str = String.valueOf(str);
        if (str.equals("null") || str.trim().equals("")) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(String... strs) {
        if (strs.length == 0) {
            return true;
        }
        for (String str : strs) {
            if (isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * ?*@描述?如果存在转换接口 使用转化器进行转换并返回 String 值 不存在时 使用 String自带的 转换字符串方法
     * ?*@参数? object 要转换的参数 converter 转换接口 defName 为空的默认显示
     * ?*@返回值? 转换为字符串的结果
     * ?*@创建人? liaojl
     * ?*@创建时间? 2018/11/9 18:47
     * ?*@修改人和其它信息
     * ?
     */
    public static String ValueOf(Object object, IConverter converter, String defName) {
        String returnStr;
        if (converter == null) {
            returnStr = String.valueOf(object);
        } else {
            returnStr = String.valueOf(converter.toConvert(object));
        }
        if (StringUtil.isEmpty(returnStr) && StringUtil.isNotEmpty(defName)) {
            returnStr = defName;
        }
        return returnStr;
    }

    public static String ValueOf(Object object, String defName) {
        return ValueOf(object, null, defName);
    }

    /**
     * 32位 UUID 字符串
     *
     * @return
     */
    public static String uuid32() {
        return uuid64().replace("-", "");
    }

    /**
     * 64 位 UUID 字符串
     *
     * @return
     */
    public static String uuid64() {
        return UUID.randomUUID().toString();
    }

    /**
     * 字符串拼接工具
     *
     * @param charSequences
     * @return
     */
    public synchronized static String appendStr(@NotNull CharSequence... charSequences) {
        StringBuilder builder = new StringBuilder();
        for (CharSequence charSequence : charSequences) {
            builder.append(charSequence);
        }
        return builder.toString();
    }

    /**
     * 判断字符串是不是 Base64 字符串
     *
     * @param str 判断字符串
     * @return
     */
    public static synchronized boolean isBase64(String str) {
        if (StringUtil.isEmpty(str)) {
            return false;
        } else {
            if (str.length() % 4 != 0) {
                return false;
            }

            char[] strChars = str.toCharArray();
            for (char c : strChars) {
                if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '+' || c == '/' || c == '=') {
                    continue;
                } else {
                    return false;
                }
            }
            return true;
        }
    }

    public static List<String> split(String className, String dot) {
        return Arrays.asList(className.split(dot));
    }



    /**
     * String 转换接口
     *
     * @author liaojinlong
     * @since 2020/9/29 10:32
     */
    public interface IConverter {
        /**
         * @param object
         * @return 转换实现方法
         */
        Object toConvert(Object object);
    }


}
