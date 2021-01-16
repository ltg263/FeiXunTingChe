package com.jxkj.fxtc.view.activity;

import android.view.View;

import com.jxkj.fxtc.R;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.conpoment.utils.DialogUtils;
import com.jxkj.fxtc.conpoment.utils.IntentUtils;

import butterknife.OnClick;

public class BookingStopCarDeActivity extends BaseActivity {
    @Override
    protected int getContentView() {
        return R.layout.activity_booking_stop_car_de;
    }

    @Override
    protected void initViews() {

    }

    @OnClick({R.id.iv_back, R.id.tv1, R.id.tv2, R.id.tv3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv1:
                DialogUtils.showDialogHint(this, "这是预约的规则", true, new DialogUtils.ErrorDialogInterface() {
                    @Override
                    public void btnConfirm() {

                    }
                });
                break;
            case R.id.tv2:
                IntentUtils.getInstence().intent(this, BookingSpaceActivity.class);
                break;
            case R.id.tv3:
                IntentUtils.getInstence().intent(this, ShopCarLogActivity.class);
                break;
        }
    }
}
