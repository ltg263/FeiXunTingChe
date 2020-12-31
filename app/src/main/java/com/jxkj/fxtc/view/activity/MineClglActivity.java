package com.jxkj.fxtc.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jxkj.fxtc.R;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.conpoment.utils.IntentUtils;
import com.jxkj.fxtc.view.adapter.MineMessageAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MineClglActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.lv_not)
    LinearLayout mLvNot;
    private MineMessageAdapter mMineMessageAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_clgl;
    }

    @Override
    protected void initViews() {
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableLoadMore(false);
        mTvTitle.setText("车辆管理");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));


        List<String> list  = new ArrayList<>();
        for(int i = 0;i<11;i++){
            list.add("");
        }

        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mLvNot.setVisibility(View.GONE);
        mRvList.setVisibility(View.VISIBLE);
        mMineMessageAdapter = new MineMessageAdapter(list);
        mRvList.setAdapter(mMineMessageAdapter);
        mLvNot.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.ll_back, R.id.rl_add_car})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.rl_add_car:
                IntentUtils.getInstence().intent(this,AddCarActivity.class);
                break;
        }
    }
}
