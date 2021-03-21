package com.duqi.algorithm.array;

import java.util.Arrays;
import java.util.Comparator;
import org.junit.Test;

/**
 * @Auther: dengyong
 * @Date: 2021/02/20/9:15
 * @Description: 数组
 */
public class ArrayDemo {

  // 把数组排成最小的数
  @Test
  public void minNumByArray() {
    int[] arr = {3, 32, 321};
    Integer[] integers = new Integer[arr.length];
    for (int i = 0; i < integers.length; i++) {
      integers[i] = arr[i];
    }
    Arrays.sort(integers, new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o1<o2?o1:o2;
      }
    });

    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < integers.length; i++) {
      buffer.append(arr[i]);
    }
    System.out.println(buffer);

  }

  // 子数组的最大累加问题
  @Test
  public void sumMaxInChildArray() {
    // 分治算法的思想：重新将计算后的最大值，赋值给原数组。赋值后的数组的倒数第二位即是原数组的子数组的最大累加值
    // 步骤： 从第二个元素开始遍历,当前值arr[i] = max(当前值arr[i],当前值arr[i]+当前值的前一位arr[i-1])
    int[] arr = {1, -2, 3, 5, 2000, 6, -1};
    for (int i = 1; i < arr.length; i++) {
      arr[i] = arr[i] > arr[i] + arr[i - 1] ? arr[i] : arr[i] + arr[i - 1];
    }
    System.out.println(arr[arr.length - 1]);

  }

}
