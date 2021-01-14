package com.jxkj.fxtc;


public class MianJavaDame {
    public static void main(String[] args) {
        String time = "2020-12-16 16:51:42";

        System.out.println(time.substring(11,16));


    }

    public static int getP(int totalCount, int pageSize){
        return (totalCount + pageSize - 1)/pageSize;
    }
}
