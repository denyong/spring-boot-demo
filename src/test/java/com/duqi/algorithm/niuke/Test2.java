package com.niuke;

/**
 * @author dengyong
 * @description
 * @create 2022/3/2 10:10
 */
public class Test2 {
    private static volatile Test2 test2 = null;
    private Test2(){}

    public synchronized Test2 getInstance(){
        if (test2 == null){
            synchronized (this){
                test2 = new Test2();
            }
        }
        return test2;
    }

}
