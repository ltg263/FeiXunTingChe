package com.jxkj.fxtc.conpoment.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/10/9.
 */

public class PwdCheckUtil {

    public static boolean ispsd(String psd) {
        Pattern p = Pattern
                .compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{6,8}$");
        Matcher m = p.matcher(psd);

        return m.matches();
    }

}
