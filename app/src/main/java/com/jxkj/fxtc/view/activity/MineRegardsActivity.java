package com.jxkj.fxtc.view.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jxkj.fxtc.R;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.conpoment.utils.BaseUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class MineRegardsActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_bbh)
    TextView tv_bbh;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_regards;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("关于我们");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        tv_bbh.setText("飞寻停车APP V"+BaseUtils.getVersionName(this));
    }

    @OnClick({R.id.ll_back, R.id.ll1, R.id.ll2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll1:
                break;
            case R.id.ll2:
                break;
        }
    }
}
