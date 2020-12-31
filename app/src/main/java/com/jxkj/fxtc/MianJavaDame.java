package com.jxkj.fxtc;


public class MianJavaDame {
    public static void main(String[] args) {

        System.out.println(":22222"+(System.currentTimeMillis() - 0));
            double a = 1/0.204;
        System.out.println(":22222"+a);


    }

    public static int getP(int totalCount, int pageSize){
        return (totalCount + pageSize - 1)/pageSize;
    }
}
