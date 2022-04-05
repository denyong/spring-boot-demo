package com.niuke;

import org.junit.Test;

/**
 * @author dengyong
 * @description
 * @create 2022/2/24 17:03
 */
public class Solution2 {
    @Test
    public void test() {
        String str = "Hello World";
        String[] arr = str.split(" ");
        StringBuilder builder = new StringBuilder();
        for (int i = arr.length-1; i >=0; i--) {
            builder.append(arr[i]);
            if (i==0){
                continue;
            }
            builder.append(" ");
        }
        char[] s = builder.toString().toCharArray();
        StringBuilder builder1 = new StringBuilder();
        for (int i = 0;i<s.length;i++){
            builder1.append(chat(s[i]));
        }
        System.out.println(builder1.toString());
    }

    public char chat(char i){
        if ('A' <= i && i<='Z'){
            return (char)(i+32);
        }

        if ('a' <= i && i<='z'){
            return (char)(i-32);
        }
        return i;
    }

    public String longestCommonPrefix (String[] strs) {
       if (strs == null || strs.length == 0){
           return "";
       }

       int arrLength = strs.length;
       int strLength = strs[0].length();

       for (int i = 0;i<strLength;i++){
           char c = strs[0].charAt(i);
           for (int j = 1;j<arrLength;j++){
               if (strs[j].length() == i || strs[j].charAt(i) != c){
                   return strs[0].substring(0,i);
               }
           }
       }
       return strs[0];
    }
}
