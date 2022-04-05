package com.duqi.algorithm;

/**
 * @author dengyong
 * @description
 * @create 2022/3/7 20:41
 */

public class Test {
    public static void main(String[] args) {
        int[] arr  = {2,7,11,15};
        int target = 9;
        test(arr,target);
    }

    private static void test(int[] arr, int target) {
        for (int i = 0;i<arr.length;i++){
            if (i  == arr.length){
                return;
            }
            for (int j = i+1;j<arr.length;j++){
                if (target == arr[i] + arr[j]){

                    return;
                }
            }
        }
    }
}
