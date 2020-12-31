package com.jxkj.fxtc.conpoment.utils;

import android.util.Log;

/**
 * author : LiuJie
 * date   : 2019/12/3012:59
 */
public class TextUtil {
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str) || "null".equalsIgnoreCase(str)) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }
    public static void logOut(String str){
        Log.w("ltg_263",str);
    }
}
