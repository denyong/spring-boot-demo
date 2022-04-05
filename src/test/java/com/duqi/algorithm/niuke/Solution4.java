package com.niuke;

/**
 * @author dengyong
 * @description
 * @create 2022/3/1 16:00
 */
public class Solution4 {
    public static void main(String[] args) {
        String target = "abc";
        String source = "abcaybec";
        int r = -1;
        int index = 0;
        for (int i  = 0;i<target.length();i++){
            // 目标子字符
            char tc = target.charAt(i);
            for (int j = index;j<source.length();j++){
                char sc = source.charAt(j);
                if (tc == sc){
                    break;
                }
            }
        }


    }
}
