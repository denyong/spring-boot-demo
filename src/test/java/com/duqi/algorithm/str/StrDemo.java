package com.duqi.algorithm.str;

import io.swagger.models.auth.In;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

/**
 * @Auther: dengyong
 * @Date: 2021/02/20/9:02
 * @Description:
 */
public class StrDemo {

  /**
   * 滑动窗口算法 找出其中不含有重复字符的 最长子串 的长度 。
   * abcabcbb:因为无重复字符的最长子串是"abc"，所以其长度为3。
   */
  @Test
  public void lengthOfLongestSubstring() {
    String str = "abcabcbb";
    int start = 0;
    int max = 0;

    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < str.length(); i++) {
      if (map.containsKey(str.charAt(i))) {
        start = map.get(str.charAt(i)) + 1;
      }
      map.put(str.charAt(i), i);
      max = Math.max(max, i - start + 1);
    }
    System.out.println(max);

  }

  @Test
  public void test1() {
    System.out.println(123);
  }
}
