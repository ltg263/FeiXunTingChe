package com.jxkj.fxtc.view.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxkj.fxtc.R;
import com.jxkj.fxtc.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MineFqsqActivity extends BaseActivity {
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_back)
    ImageView mIvBack;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_fqsq;
    }

    @Override
    protected void initViews() {

        mTvTitle.setText("申请发票");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
    }

    @OnClick({R.id.ll_back, R.id.bnt_go})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.bnt_go:
                break;
        }
    }

}
