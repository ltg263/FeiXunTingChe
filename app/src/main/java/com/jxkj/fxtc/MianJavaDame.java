package com.jxkj.fxtc;


public class MianJavaDame {
    public static void main(String[] args) {
        String time = "12-34 56:78";

        System.out.println(time.substring(0,2));
        System.out.println(time.substring(3,5));
        System.out.println(time.substring(6,8));
        System.out.println(time.substring(9,11));


    }

    public static int getP(int totalCount, int pageSize){
        return (totalCount + pageSize - 1)/pageSize;
    }
}
