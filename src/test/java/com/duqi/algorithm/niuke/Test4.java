package com.niuke;

/**
 * @author dengyong
 * @description
 * @create 2022/3/2 18:33
 */
public class Test4 {
    public static void main(String[] args) {
        int[] arr = {5,10,10};

        boolean result = test(arr);
        System.out.println(result);
    }

    private static boolean test(int[] arr) {
        int five = 1;
        int ten = 0;
        if (arr == null || arr.length == 0){
            return false;
        }

        if (arr[0] != 5){
            return false;
        }

        for (int i = 1;i<arr.length;i++){
            if (arr[i] == 5){
                five++;
                continue;
            }

            if (arr[i] == 10 && five<=0){
                return false;
            }else {
                five--;
                ten++;
            }

            if (arr[i] == 20){
                if (ten >=1 && five>=1){
                    ten--;
                    five--;
                }else if (five>=3){
                    five = five - 3;
                }else {
                    return false;
                }
            }
        }
        return true;
    }
}
