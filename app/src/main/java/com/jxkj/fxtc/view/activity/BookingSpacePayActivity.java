package com.jxkj.fxtc.view.activity;

import android.view.View;

import com.jxkj.fxtc.R;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.conpoment.utils.IntentUtils;

import butterknife.OnClick;

public class BookingSpacePayActivity extends BaseActivity {

    @Override
    protected int getContentView() {
        return R.layout.activity_booking_space_pay;
    }

    @Override
    protected void initViews() {

    }


    @OnClick({R.id.iv_back, R.id.bnt, R.id.bnt_1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.bnt:

                break;
            case R.id.bnt_1:
                IntentUtils.getInstence().intent(this,BookingSpaceOkActivity.class);
                break;
        }
    }
}
