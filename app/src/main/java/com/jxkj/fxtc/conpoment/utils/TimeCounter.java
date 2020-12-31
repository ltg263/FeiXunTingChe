package com.jxkj.fxtc.conpoment.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

public class TimeCounter extends CountDownTimer {

    private TextView tv;
    public TimeCounter(long millisInFuture, long countDownInterval, TextView tv) {
        super(millisInFuture, countDownInterval);
        this.tv=tv;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        tv.setClickable(false);
        tv.setText(millisUntilFinished/1000+"秒");
    }

    @Override
    public void onFinish() {
        tv.setClickable(true);
        tv.setText("获取验证码");
    }
}
