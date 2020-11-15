//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。 
//
// 示例 1： 
//
// 输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
// 
//
// 示例 2： 
//
// 输入: "cbbd"
//输出: "bb"
// 
// Related Topics 字符串 动态规划 
// 👍 2812 👎 0


import java.util.HashMap;
import java.util.Map;


class Solution {

    //最大长度
    int maxLength = 1;

    //结束点记录元素
    int endLeft = 0;
    int endRight = 0;

    public String longestPalindrome(String s) {

        String result = "";

        if(s.length() == 0){
            return result;
        }

        if(s.length() > 0){
            result = s.substring(0,1);
        }

        //初始化
        maxLength = 1;
        endLeft = 0;
        endRight = 0;

        //指针
        int left = 0;
        int right = 0;

        //当前最大长度
        int currMaxLength = 0;

        //char array
        char[] chars = s.toCharArray();

        int i = 0;
        while(i<chars.length){
            left = i-1;
            right = i+1;
            currMaxLength = 1;

            //当前字符串
            char currrentStr = chars[i];

            //处理字符连续相等
            int j=i+1;
            while(j<chars.length){
                if(currrentStr == chars[j]){
                    right=j+1;
                    currMaxLength++;
                    //这一行很关键，字符连续相等，直接推进指针i到最后一个相等的字符。可以缩短8倍的运行时间
                    i++;
                }else{
                    break;
                }
                j++;
            }

            //处理字符left right相等
            while(left>=0&&right<chars.length){
                if(chars[left]==chars[right]){
                    currMaxLength++;
                    currMaxLength++;
                }else{
                    break;
                }
                left--;
                right++;
            }

            //处理结果
            if(currMaxLength>maxLength){

                maxLength = currMaxLength;
                //因为最后一次循环多偏移了一次指针，所以要复原回来
                endLeft = ++left;
                endRight = --right;

            }

            i++;

        }

        if(endRight!=endLeft){
            result = s.substring(endLeft,endRight+1);
        }

        return result;

    }

}