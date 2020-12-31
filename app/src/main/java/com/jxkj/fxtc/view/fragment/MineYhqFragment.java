package com.jxkj.fxtc.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.api.RetrofitUtil;
import com.jxkj.fxtc.base.BaseFragment;
import com.jxkj.fxtc.base.Result;
import com.jxkj.fxtc.entity.CouponCanListUser;
import com.jxkj.fxtc.view.adapter.MineYhqListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * author : LiuJie
 * date   : 2020/6/116:57
 */
public class MineYhqFragment extends BaseFragment {
    @BindView(R.id.rv_shopping_cart)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.lv_not)
    LinearLayout mLvNot;

    private int page = 1;
    private Bundle bundle;

    @Override
    protected int getContentView() {
        return R.layout.fragment_yhq;
    }

    private int type = 0;
    MineYhqListAdapter mYhqListAdapter;

    @Override
    protected void initViews() {
        bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt("type");
        }
        initOrder();
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableLoadMore(false);

        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getData(type);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getData(type);
            }
        });
    }

    @Override
    public void initImmersionBar() {
        getData(type);
    }

    private void initOrder() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mYhqListAdapter = new MineYhqListAdapter(null, type);
        mRecyclerView.setAdapter(mYhqListAdapter);
        mYhqListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                getActivity().finish();
            }
        });
    }

    private void getData(int type) {
        RetrofitUtil.getInstance().apiService()
                .userCouponQuery(String.valueOf(type),null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<CouponCanListUser>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<CouponCanListUser> result) {
                        if (isDataInfoSucceed(result)) {
                            mLvNot.setVisibility(View.VISIBLE);
                            mRefreshLayout.setVisibility(View.GONE);
                            if (result.getData() != null && result.getData().getList()!=null &&result.getData().getList().size()>0) {
                                mYhqListAdapter.setNewData(result.getData().getList());
                                mLvNot.setVisibility(View.GONE);
                                mRefreshLayout.setVisibility(View.VISIBLE);
                            }

                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        dismiss();
                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });

    }
}

