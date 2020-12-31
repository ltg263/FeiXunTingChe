package com.jxkj.fxtc.conpoment.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches {

    public static boolean isIdCard(String idcard) {

        String pattern = "\\d{17}[0-9Xx]|\\d{15}";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(idcard);
        return m.matches();
    }
}
