package com.jxkj.fxtc.conpoment.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/8/4.
 */

public class StringUtil {

    public static String replaceNull(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str;
    }

    public static String replaceNullToZero(String str) {
        if (TextUtils.isEmpty(str)) {
            return "0";
        }
        return str;
    }

    public static String replaceNullToOne(String str) {
        if (TextUtils.isEmpty(str)) {
            return "1";
        }
        return str;
    }

    public static boolean checkIsNotEmpty(TextView edt) {
        if (ObjectUtils.isEmpty(edt.getText())) {
            return false;
        }
        if (TextUtils.isEmpty(edt.getText().toString())) {
            return false;
        }
        return true;
    }

    public static String format(String format, Object v1, Object v2) {
        return String.format(format, v1, v2);
    }

    public static String replaceHtmlImgToAbsolutelyUrl(String baseUrl, String html) {
        Pattern pattern = Pattern.compile("src\\s*=\\s*\"(.+?)\"");
        Matcher matcher = pattern.matcher(html);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String str = matcher.group(0);
            if (!str.contains("http://")) {
                matcher.appendReplacement(sb, str.substring(0, 5) + baseUrl + str.substring(6));
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String replacrPhoneNum2Star(String phoneNum) {
        if (ObjectUtils.isEmpty(phoneNum)) {
            return "";
        }
        if (phoneNum.length() < 7) {
            return phoneNum;
        }
        return phoneNum.substring(0, 3) + "****" + phoneNum.substring(7, phoneNum.length());
    }

    /**
     * 银行卡号中间每四位插入一个空格
     *
     * @param str
     * @return
     */
    public static String insertSpacePerFour(String str) {
        if (ObjectUtils.isEmpty(str)) {
            return "";
        }
        if (str.length() < 16) {
            return str;
        }
        return str.substring(0, 4) + " **** ****  " + str.substring(12, 16) + " " + str.substring(16, str.length());
    }

    /**
     * 校验银行卡卡号
     *
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return ("".equals(str) || "null".equals(str) || str == null);
    }

    /**
     * 替换非utf8字符，慎用  会出现死循环
     *
     * @param text
     * @return
     */
    public static String filterOffUtf8Mb4(String text) {
        try {
            byte[] bytes = text.getBytes("UTF-8");
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            int i = 0;
            while (i < bytes.length) {
                short b = bytes[i];
                if (b > 0) {
                    buffer.put(bytes[i++]);
                    continue;
                }
                b += 256;
                if ((b ^ 0xC0) >> 4 == 0) {
                    buffer.put(bytes, i, 2);
                    i += 2;
                } else if ((b ^ 0xE0) >> 4 == 0) {
                    buffer.put(bytes, i, 3);
                    i += 3;
                } else if ((b ^ 0xF0) >> 4 == 0) {
                    i += 4;
                }
            }
            buffer.flip();
            return new String(buffer.array(), "utf-8");
        } catch (Exception e) {
            Log.e("Exception", e.getMessage().toString());
        }
        return text;
    }

    public static boolean isUTF8(String key) {
        try {
            key.getBytes("utf-8");
            return true;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    /**
     * 检测是否有emoji字符
     *
     * @param source
     * @return FALSE，包含图片
     */
    public static boolean containsEmoji(String source) {
        if (source.equals("")) {
            return false;
        }

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint)) {
                //do nothing，判断到了这里表明，确认有表情字符
                return true;
            }
        }

        return false;
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     *
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {

        if (!containsEmoji(source)) {
            return source;//如果不包含，直接返回
        }
        //到这里铁定包含
        StringBuilder buf = null;

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }

                buf.append(codePoint);
            } else {
            }
        }

        if (buf == null) {
            return source;//如果没有找到 emoji表情，则返回源字符串
        } else {
            if (buf.length() == len) {
                //这里的意义在于尽可能少的toString，因为会重新生成字符串
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }

    }

    /**
     * 监听输入框输的变化
     */
    public static void etSearchChangedListener(final EditText et, final View btn, final EtChange etChange) {
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0 && et.getText().toString().trim().length() != 0) {
                    etChange.etYes();
                }
                if (et.getText().toString().trim().length() == 0) {
                    if (btn != null) {
                        btn.setSelected(false);
                    }
                    etChange.etNo();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public static String getTimeToYMD(long seconds, String layout) {
        Date d = new Date(seconds);
        SimpleDateFormat sdf = new SimpleDateFormat(layout);
        return sdf.format(d).toString();
    }

    /**
     * 描述: 将字符串转成毫秒数 格式年月日
     */
    public static long getMsToTime(String time, String layout) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(new SimpleDateFormat(layout).parse(time));
            return c.getTimeInMillis();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public abstract static class EtChange {
        //有内容
        public abstract void etYes();

        //无内容
        public void etNo() {
        }
    }

    /**
     * 将html文本内容中包含img标签的图片，宽度变为屏幕宽度，高度根据宽度比例自适应
     **/
    public static String getNewContent(String htmltext) {
        try {
            if (!htmltext.contains("&nbsp;") && htmltext.contains("&nbsp")) {
                htmltext = htmltext.replaceAll("&nbsp", "&nbsp;");
            }
            Document doc = Jsoup.parse(htmltext);

            Elements elementsAll = doc.getAllElements();
            for (Element span : elementsAll) {
//                Elements p = span.getElementsByTag("p");
////                p.attr("style","font-size:16px;width:100%;margin:1rem 0px");
////                Elements h1 = span.getElementsByTag("h1");
                if (!span.toString().contains("<html") &&
                        !span.toString().contains("<head") &&
                        !span.toString().contains("<body") &&
                        !span.toString().contains("<div")) {//.p:last-child{margin-bottom!important}
                    span.attr("style", "font-size:16px;width:100%;margin-bottom:1rem;line-height:26px;letter-spacing:1px;");
                }
            }
            Elements head = doc.getElementsByTag("head");
            head.get(0).html("<style>*{border:0;margin:0;padding:0;};p:last-child{margin-bottom:0px;!important}</style>");
            Elements elements = doc.getElementsByTag("img");
            for (Element element : elements) {
                element.attr("width", "100%").attr("height", "auto");
                String str = element.attr("src");
                if (str.contains("image/png;base64")) {
                    element.attr("src", "");
                }
            }
            return doc.toString();
        } catch (Exception e) {
            return htmltext;
        }
    }

    /**
     * 得到网页中图片的地址
     */
    public static ArrayList<String> getImgStr(String htmlStr) {
        ArrayList<String> pics = new ArrayList<>();
        String img = "";
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        Pattern p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        Matcher m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }

    public static int getTotalPage(int totalCount, int pageSize) {
        return (totalCount + pageSize - 1) / pageSize;
    }

    public static List<Double> getAverageV(double maxV) {
        List<Double> lists = new ArrayList<>();
        double b = Math.ceil(maxV / 5);
        double c = b;
        lists.add(0d);
        for (int i = 0; i < 5; i++) {
            c = c + b;
            lists.add(c);
        }
        return lists;
    }

    public static void loginNo(Context mContext) {
        ToastUtil.showLongStrToast(mContext, "请先登录");
    }

    public static void showKf(Context mContext) {
        ToastUtil.showLongStrToast(mContext, "努力开发中....");
    }

    public static Object[] deleteSubString(String str1, String str2) {
        StringBuffer sb = new StringBuffer(str1);
        int delCount = 0;
        Object[] obj = new Object[2];

        while (true) {
            int index = sb.indexOf(str2);
            if (index == -1) {
                break;
            }
            sb.delete(index, index + str2.length());
            delCount++;

        }
        if (delCount != 0) {
            obj[0] = sb.toString();
            obj[1] = delCount;
        } else {
            //不存在返回-1
            obj[0] = -1;
            obj[1] = -1;
        }

        return obj;
    }

    public static int getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
            } else {
                age--;//当前月份在生日之前，年龄减
            }
        }
        return age;
    }

    public static String trimZero(String s) {
        if (s.indexOf(".") > 0) {
            // 去掉多余的0
            s = s.replaceAll("0+?$", "");
            // 如最后一位是.则去掉
            s = s.replaceAll("[.]$", "");
        }
        return s;
    }
}
