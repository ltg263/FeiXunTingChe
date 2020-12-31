package com.jxkj.fxtc.view.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jxkj.fxtc.R;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.view.adapter.MineMessageAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MineMessageActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.lv_not)
    LinearLayout mLvNot;
    private MineMessageAdapter mMineMessageAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_message;
    }

    @Override
    protected void initViews() {
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableLoadMore(false);
        mTvTitle.setText("我的消息");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mLlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


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

}
