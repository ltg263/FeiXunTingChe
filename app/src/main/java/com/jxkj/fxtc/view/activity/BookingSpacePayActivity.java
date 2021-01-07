package com.jxkj.fxtc.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.jxkj.fxtc.R;
import com.jxkj.fxtc.api.RetrofitUtil;
import com.jxkj.fxtc.app.ConstValues;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.base.Result;
import com.jxkj.fxtc.conpoment.utils.GlideImgLoader;
import com.jxkj.fxtc.conpoment.utils.IntentUtils;
import com.jxkj.fxtc.conpoment.utils.SharedUtils;
import com.jxkj.fxtc.entity.OrdersDetailBean;
import com.jxkj.fxtc.entity.UserDetailBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BookingSpacePayActivity extends BaseActivity {

    int type = 0;//0:预约支付 1停车中  2详情-已支付
    @BindView(R.id.ll_tcf)
    LinearLayout mLlTcf;
    @BindView(R.id.ll_0)
    LinearLayout mLl0;
    @BindView(R.id.ll_2)
    LinearLayout mLl2;
    @BindView(R.id.ll_not_pay)
    LinearLayout mLlNotPay;

    @Override
    protected int getContentView() {
        return R.layout.activity_booking_space_pay;
    }

    @Override
    protected void initViews() {
        type = getIntent().getIntExtra("type",0);
        if(type == 0){
            mLl0.setVisibility(View.VISIBLE);
            mLlNotPay.setVisibility(View.VISIBLE);
        }
        if (type == 2) {
            mLl2.setVisibility(View.VISIBLE);
            mLlTcf.setVisibility(View.VISIBLE);
        }
        getOrdersDetail();
    }


    @OnClick({R.id.iv_back, R.id.bnt, R.id.bnt_1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.bnt:

                break;
            case R.id.bnt_1:
                IntentUtils.getInstence().intent(this, BookingSpaceOkActivity.class);
                break;
        }
    }

    public static void startActivityIntent(Context mContext, String id) {
        IntentUtils.getInstence().intent(mContext,
                BookingSpacePayActivity.class, "id", id);
    }

    private void getOrdersDetail() {
        RetrofitUtil.getInstance().apiService()
                .getOrdersDetail("")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<OrdersDetailBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<OrdersDetailBean> result) {
                        if (isDataInfoSucceed(result)) {

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
