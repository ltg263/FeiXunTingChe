package com.jxkj.fxtc.view.activity;

import android.content.Intent;
import android.view.View;

import com.jxkj.fxtc.R;
import com.jxkj.fxtc.app.MainApplication;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.conpoment.utils.SharedUtils;

import butterknife.OnClick;

public class MineSetActivity extends BaseActivity {

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_set;
    }

    @Override
    protected void initViews() {
        MainApplication.addActivity(this);

    }

    @OnClick({R.id.ll1, R.id.ll2, R.id.ll3, R.id.tv_tui,R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll1:
                startActivity(new Intent(this,LoginWjmmActivity.class));
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll2:
                break;
            case R.id.ll3:
                break;
            case R.id.tv_tui:
                SharedUtils.singleton().clear();
                MainApplication.getContext().AppExit();
                LoginActivity.startActivityIntent(this);
                break;
        }
    }
}
