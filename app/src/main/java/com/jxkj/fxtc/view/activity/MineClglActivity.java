package com.jxkj.fxtc.view.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.api.RetrofitUtil;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.base.Result;
import com.jxkj.fxtc.conpoment.utils.IntentUtils;
import com.jxkj.fxtc.entity.UserCarListBean;
import com.jxkj.fxtc.view.adapter.MineClglAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
    private MineClglAdapter mMineClglAdapter;

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

        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mLvNot.setVisibility(View.GONE);
        mRvList.setVisibility(View.VISIBLE);
        mMineClglAdapter = new MineClglAdapter(null);
        mRvList.setAdapter(mMineClglAdapter);
        mMineClglAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(getIntent().getStringExtra("type").equals("0")){
                    postDefaultCar(mMineClglAdapter.getData().get(position).getId());
                }
            }
        });

        getAllCar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllCar();
    }

    @OnClick({R.id.ll_back, R.id.rl_add_car})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.rl_add_car:
                AddCarActivity.startActivityIntent(this,"","");
                break;
        }
    }


    private void postDefaultCar(String id) {
        RetrofitUtil.getInstance().apiService()
                .postDefaultCar(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            MineClglActivity.this.finish();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    private void getAllCar() {
        RetrofitUtil.getInstance().apiService()
                .getAllCar()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<UserCarListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<UserCarListBean> result) {
                        if (isDataInfoSucceed(result)) {
                            if(result.getData().getList()!=null && result.getData().getList().size()>0){
                                mLvNot.setVisibility(View.GONE);
                                mRefreshLayout.setVisibility(View.VISIBLE);
                                mMineClglAdapter.setNewData(result.getData().getList());
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
