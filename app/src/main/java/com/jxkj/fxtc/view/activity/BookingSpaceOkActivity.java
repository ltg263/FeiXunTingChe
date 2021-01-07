package com.jxkj.fxtc.view.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jxkj.fxtc.R;
import com.jxkj.fxtc.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class BookingSpaceOkActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_zt)
    TextView mTvZt;
    @BindView(R.id.tv_sm)
    TextView mTvSm;
    @BindView(R.id.tv_dz)
    TextView mTvDz;
    @BindView(R.id.tv_cw)
    TextView mTvCw;
    @BindView(R.id.tv_c)
    TextView mTvC;
    @BindView(R.id.tv_fy)
    TextView mTvFy;

    @Override
    protected int getContentView() {
        return R.layout.activity_booking_space_ok;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("预约成功");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
    }

    @OnClick({R.id.ll_back, R.id.bnt, R.id.bnt_1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.bnt:
                break;
            case R.id.bnt_1:
                BookingSpacePayActivity.startActivityIntent(this,"");
                break;
        }
    }
}
