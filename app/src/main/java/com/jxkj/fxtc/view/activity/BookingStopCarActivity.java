package com.jxkj.fxtc.view.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.conpoment.utils.IntentUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class BookingStopCarActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @Override
    protected int getContentView() {
        return R.layout.activity_booking_stop_car;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("预约车位");
        mIvBack.setImageDrawable(getResources().getDrawable(R.mipmap.back_h));
    }


    @OnClick({R.id.ll_back, R.id.rl_1, R.id.rl_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.rl_1:
                IntentUtils.getInstence().intent(this,BookingStopCarDeActivity.class);
                break;
            case R.id.rl_2:
                ToastUtils.showShort("努力开发中...");
                break;
        }
    }
}
