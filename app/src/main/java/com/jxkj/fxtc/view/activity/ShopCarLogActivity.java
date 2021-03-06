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
import com.jxkj.fxtc.entity.CarRecordListBean;
import com.jxkj.fxtc.entity.OrdersListBean;
import com.jxkj.fxtc.entity.UserBillListBean;
import com.jxkj.fxtc.entity.UserCarListBean;
import com.jxkj.fxtc.view.adapter.ShopCarlLogAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShopCarLogActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.lv_not)
    LinearLayout mLvNot;
    private ShopCarlLogAdapter mShopCarlLogAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_shop_car_log;
    }

    @Override
    protected void initViews() {
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableLoadMore(false);

        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mShopCarlLogAdapter = new ShopCarlLogAdapter(null);
        mRvList.setAdapter(mShopCarlLogAdapter);
        mShopCarlLogAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BookingSpacePayDeActivity.startActivityIntent(ShopCarLogActivity.this,mShopCarlLogAdapter.getData().get(position).getId());
//                if(mShopCarlLogAdapter.getData().get(position).getStatus().equals("0")){
//
//                }else{
//                }

            }
        });

        getBillList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getBillList();
    }

    @OnClick({R.id.ll_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }


    private void getBillList() {
        RetrofitUtil.getInstance().apiService()
                .getOrdersList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<OrdersListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<OrdersListBean> result) {
                        if (isDataInfoSucceed(result)) {
                            if(result.getData().getList()!=null && result.getData().getList().size()>0){
                                mLvNot.setVisibility(View.GONE);
                                mRefreshLayout.setVisibility(View.VISIBLE);
                                mShopCarlLogAdapter.setNewData(result.getData().getList());
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
