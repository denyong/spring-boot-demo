package com.niuke;

/**
 * @author dengyong
 * @description
 * @create 2022/2/24 20:56
 */
public class Solution3 {
    public static void main(String[] args) {
        String IP = "192.168.169.30";
        String[] strs = IP.split("\\.");
        System.out.println(strs);
    }

    private boolean validIPv4(String IP) {
        String[] arr = IP.split("\\.");

        if (arr.length != 4){
            return false;
        }

        for (int i = 0;i<arr.length;i++){
            if (arr[i].length() > 1 && arr[i].startsWith("0")){
                return false;
            }
            try {
                int i1 = Integer.parseInt(arr[i]);
                if (i1<0 || i1>255){
                    return false;
                }
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        return true;
    }

    private boolean validIPv6(String IP) {
        String[] arr = IP.split(":");
        if (arr.length != 8){
            return false;
        }

        for (int i = 0;i<arr.length;i++){
          if (arr[i].length() > 4 || arr.length == 0){
            return false;
          }
          try{
              int i1 = Integer.parseInt(arr[i], 16);
          }catch (Exception e){
              e.printStackTrace();
          }
        }
        return true;
    }
}
