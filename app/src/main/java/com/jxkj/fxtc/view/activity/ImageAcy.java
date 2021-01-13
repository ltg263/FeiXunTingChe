package com.jxkj.fxtc.view.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxkj.fxtc.R;
import com.jxkj.fxtc.base.BaseActivity;

import butterknife.BindView;

public class ImageAcy extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv)
    ImageView iv;
    String parkingName;
    @Override
    protected int getContentView() {
        return R.layout.image_acy;
    }

    @Override
    protected void initViews() {
        parkingName = getIntent().getStringExtra("parkingName");
        mTvTitle.setText("我的账单");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mLlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        switch (parkingName){
            case "樱花公园":
                iv.setImageDrawable(getResources().getDrawable(R.mipmap.ic_linshi_yh));
                break;
            case "桂花城路侧":
                iv.setImageDrawable(getResources().getDrawable(R.mipmap.ic_linshi_gh));
                break;
            case "翁浦公园":
                iv.setImageDrawable(getResources().getDrawable(R.mipmap.ic_linshi_wp));
                break;
        }
    }
}
