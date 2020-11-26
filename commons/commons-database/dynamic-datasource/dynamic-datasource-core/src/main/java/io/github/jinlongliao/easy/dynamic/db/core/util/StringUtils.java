/*
 *  Copyright 2018-2020 , 廖金龙 (mailto:jinlongliao@foxmail.com).
 * <p>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package io.github.jinlongliao.easy.dynamic.db.core.util;

/**
 * 字符串工具类
 *
 * @author liaojinlong
 * @since 2020/9/14 18:16
 */
public class StringUtils {
  private static final char SEPARATOR = '_';
  private static final char SEPARATOR2 = '-';

  /**
   * 判断字符串是否为空
   *
   * @param str 字符串
   * @return 是否为空
   */
  public static boolean isEmpty(String str) {
    return str == null || "".equals(str);
  }

  /**
   * 判断字符串是否不为空
   *
   * @param str 字符串
   * @return 是否不为空
   */
  public static boolean isNotEmpty(String str) {
    return str != null && !"".equals(str);
  }

  /**
   * 驼峰命名法工具
   *
   * @return toCamelCase(" hello_world ") == "helloWorld"
   * toCapitalizeCamelCase("hello_world") == "HelloWorld"
   * toUnderScoreCase("helloWorld") = "hello_world"
   */
  public static String toCamelCase(String s) {
    if (s == null) {
      return null;
    }

    s = s.toLowerCase();

    StringBuilder sb = new StringBuilder(s.length());
    boolean upperCase = false;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (c == SEPARATOR) {
        upperCase = true;
      } else if (upperCase) {
        sb.append(Character.toUpperCase(c));
        upperCase = false;
      } else {
        sb.append(c);
      }
    }

    return sb.toString();
  }

  /**
   * 驼峰命名法工具
   *
   * @return toCamelCase(" hello_world ") == "helloWorld"
   * toCapitalizeCamelCase("hello_world") == "HelloWorld"
   * toUnderScoreCase("helloWorld") = "hello_world"
   */
  public static String toCapitalizeCamelCase(String s) {
    if (s == null) {
      return null;
    }
    s = toCamelCase(s);
    return s.substring(0, 1).toUpperCase() + s.substring(1);
  }

  /**
   * 驼峰命名法工具
   *
   * @return toCamelCase(" hello_world ") == "helloWorld"
   * toCapitalizeCamelCase("hello_world") == "HelloWorld"
   * toUnderScoreCase("helloWorld") = "hello_world"
   */
  static String toUnderScoreCase(String s) {
    if (s == null) {
      return null;
    }

    StringBuilder sb = new StringBuilder();
    boolean upperCase = false;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      boolean nextUpperCase = true;

      if (i < (s.length() - 1)) {
        nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
      }

      if ((i > 0) && Character.isUpperCase(c)) {
        if (!upperCase || !nextUpperCase) {
          sb.append(SEPARATOR);
        }
        upperCase = true;
      } else {
        upperCase = false;
      }

      sb.append(Character.toLowerCase(c));
    }

    return sb.toString();
  }

  public static String toFirstUpper(String key) {
    final char[] chars = key.toCharArray();
    if (chars[0] > 96) {
      chars[0] -= 32;
    }
    return new String(chars);
  }

}
