package io.github.jinlonghliao.commons.mapstruct.core;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 内部基本类型数据转换
 *
 * @author liaojinlong
 * @since 2020/11/23 21:14
 */
public class InnerCoreDataConverter {
    /**
     * 数据转换为byte
     *
     * @param data
     * @return byte
     */
    public static byte getByte(Object data) {
        if (data == null) {
            return 0;
        }
        if (data instanceof String) {
            return Byte.valueOf(String.valueOf(data));
        }
        return (byte) data;
    }

    /**
     * 数据转换为Short
     *
     * @param data
     * @return Short
     */
    public static short getShort(Object data) {
        if (data == null) {
            return 0;
        }
        if (data instanceof String) {
            return Short.valueOf(String.valueOf(data));
        }
        return (short) data;
    }

    /**
     * 数据转换为float
     *
     * @param data
     * @return float
     */
    public static float getFloat(Object data) {
        if (data == null) {
            return 0;
        }
        if (data instanceof String) {
            return Float.valueOf(String.valueOf(data));
        }
        return (float) data;
    }

    /**
     * 数据转换为double
     *
     * @param data
     * @return double
     */
    public static double getDouble(Object data) {
        if (data == null) {
            return 0;
        }
        if (data instanceof String) {
            return Double.valueOf(String.valueOf(data));
        }
        return (double) data;
    }

    /**
     * 数据转换为long
     *
     * @param data
     * @return long
     */
    public static long getLong(Object data) {
        if (data == null) {
            return 0;
        }
        if (data instanceof String) {
            return Long.valueOf(String.valueOf(data));
        }
        return (long) data;
    }

    /**
     * 数据转换为int
     *
     * @param data
     * @return int
     */
    public static int getInt(Object data) {
        if (data == null) {
            return 0;
        }
        if (data instanceof String) {
            return Integer.valueOf(String.valueOf(data));
        }
        return (int) data;
    }

    /**
     * 数据转换为String
     *
     * @param data
     * @return String
     */
    public static String getStr(Object data) {
        if (data == null) {
            return null;
        }
        return String.valueOf(data);
    }

    /**
     * 数据转换为 char
     *
     * @param data
     * @return char
     */
    public static char getChar(Object data) {
        return (char) getInt(data);
    }

    /**
     * 数据转换为 Date
     *
     * @param data
     * @return Date
     */
    public static Date geDate(Object data) {
        return (Date) (data);
    }

    /**
     * 数据转换为 List
     *
     * @param data
     * @return List
     */
    public static List<Object> getList(Object data) {

        return (List<Object>) data;
    }

    /**
     * 数据转换为 Map
     *
     * @param data
     * @return Map
     */
    public static Map getMap(Object data) {
        return (Map) data;
    }

    /**
     * 数据转换为 Integer[]
     *
     * @param data
     * @return Integer[]
     */
    public static Integer[] getIntArr(Object data) {
        if (!data.getClass().getName().equals("[Ljava.lang.Integer;")) {
            if (data.getClass().isArray()) {
                Object[] objects = (Object[]) data;
                Integer[] result = new Integer[objects.length];
                int index = 0;
                for (Object object : objects) {
                    if (object instanceof String) {
                        result[index++] = Integer.valueOf(String.valueOf(object));
                    } else {
                        result[index++] = ((Integer) object);
                    }
                }
                data = result;
            } else {
                data = new Integer(Integer.valueOf(String.valueOf(data)));
            }
        }
        return (Integer[]) data;
    }

    /**
     * 数据转换为 int[]
     *
     * @param data
     * @return int[]
     */
    public static int[] getInt2Arr(Object data) {
        if (!data.getClass().getName().equals("[I")) {
            if (data.getClass().isArray()) {
                Object[] objects = (Object[]) data;
                int[] result = new int[objects.length];
                int index = 0;
                for (Object object : objects) {
                    if (object instanceof String) {
                        result[index++] = Integer.parseInt(String.valueOf(object));
                    } else {
                        result[index++] = ((Integer) object);
                    }
                }
                data = result;
            } else {
                data = new Integer(Integer.parseInt(String.valueOf(data)));
            }
        }


        return (int[]) data;
    }

    /**
     * 数据转换为 String[]
     *
     * @param data
     * @return String[]
     */
    public static String[] getStringArr(Object data) {
        return (String[]) data;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1};
        String name = a.getClass().getName();
        System.out.println("args = " + Arrays.deepToString(args));
    }
}
