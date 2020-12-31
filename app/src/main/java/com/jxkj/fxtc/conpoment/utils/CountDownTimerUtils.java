package com.jxkj.fxtc.conpoment.utils;

import android.os.CountDownTimer;
import android.widget.Button;

public final class CountDownTimerUtils extends CountDownTimer {

    private Button mBtnGetCheckCode;

    public CountDownTimerUtils(Button button) {
        super(60000, 1000);
        this.mBtnGetCheckCode = button;
    }

    @Override
    public void onFinish() {
        mBtnGetCheckCode.setEnabled(true);
        mBtnGetCheckCode.setClickable(true);
        mBtnGetCheckCode.setText("获取验证码");
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mBtnGetCheckCode.setEnabled(false);
        mBtnGetCheckCode.setClickable(false);
        mBtnGetCheckCode.setText(millisUntilFinished / 1000 + "s");
    }
}