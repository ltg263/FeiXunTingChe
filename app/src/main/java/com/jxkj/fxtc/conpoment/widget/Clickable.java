package com.jxkj.fxtc.conpoment.widget;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.jxkj.fxtc.R;
import com.jxkj.fxtc.app.MainApplication;

/**
 * 作者： litongge
 * 时间：  2018/5/10 11:47
 * 邮箱；ltg263@126.com
 * 描述：自定以TextView  同一TV设置不同的颜色 和事件
 */
public class Clickable extends ClickableSpan {
    private final View.OnClickListener mListener;
    public Clickable(View.OnClickListener l) {
        mListener = l;
    }
    /**
     * 重写父类点击事件
     */
    @Override
    public void onClick(View v) {
        mListener.onClick(v);
    }
    /**
     * 重写父类updateDrawState方法  我们可以给TextView设置字体颜色,背景颜色等等...
     */
    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(MainApplication.getContext().getResources().getColor(R.color.color_E8643D));
    }

    public static void getSpannableString(String all , final String name[], TextView tv, final ClickListener clickListener){
        SpannableString spannableString = new SpannableString(all);
        for(int i=0;i<name.length;i++){
            //评论人
            int var = all.indexOf(name[i]);

            final int finalI = i;
            spannableString.setSpan(new Clickable(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.setOnClick(name[finalI]);
                }
            }),var,var+name[i].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.setMovementMethod(LinkMovementMethod.getInstance());
        }

        tv.setText(spannableString);
    }
    public static abstract class ClickListener{
        public abstract void setOnClick(String name);
    }
}
