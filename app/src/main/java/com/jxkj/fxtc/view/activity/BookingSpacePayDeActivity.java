package com.jxkj.fxtc.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxkj.fxtc.R;
import com.jxkj.fxtc.api.RetrofitUtil;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.base.Result;
import com.jxkj.fxtc.conpoment.utils.IntentUtils;
import com.jxkj.fxtc.entity.OrdersDetailBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BookingSpacePayDeActivity extends BaseActivity {

    @BindView(R.id.ll_tcf)
    LinearLayout mLlTcf;
    @BindView(R.id.ll_0)
    LinearLayout mLl0;
    @BindView(R.id.ll_2)
    LinearLayout mLl2;
    @BindView(R.id.ll_not_pay)
    LinearLayout mLlNotPay;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv_dw)
    TextView tv_dw;
    String id;
    @BindView(R.id.tv_jcsj)
    TextView mTvJcsj;
    @BindView(R.id.tv_yycp)
    TextView mTvYycp;
    @BindView(R.id.tv_szcw)
    TextView mTvSzcw;
    @BindView(R.id.tv_tcjs)
    TextView mTvTcjs;
    @BindView(R.id.tv_jfsj)
    TextView mTvJfsj;
    @BindView(R.id.tv_tcf)
    TextView mTvTcf;
    @BindView(R.id.tv_yyf)
    TextView mTvYyf;
    @BindView(R.id.tv_yhq)
    TextView mTvYhq;
    @BindView(R.id.tv_zfy)
    TextView mTvZfy;

    @Override
    protected int getContentView() {
        return R.layout.activity_booking_space_pay_1;
    }

    @Override
    protected void initViews() {
        id = getIntent().getStringExtra("id");
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
                BookingSpacePayDeActivity.class, "id", id);
    }

    private void getOrdersDetail() {
        RetrofitUtil.getInstance().apiService()
                .getOrdersDetail(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<OrdersDetailBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<OrdersDetailBean> result) {
                        if (isDataInfoSucceed(result)) {
                            tv1.setText(result.getData().getParkingName() + "-停车场");
                            tv_dw.setText(result.getData().getAddress());
                            mTvJcsj.setText(result.getData().getStartTime());
                            mTvYycp.setText(result.getData().getLicense());
                            mTvSzcw.setText(result.getData().getSeatName());
                            mTvTcjs.setText(result.getData().getUseTime());
                            mTvYyf.setText(result.getData().getAppointmentPrice());
                            mTvZfy.setText("￥"+result.getData().getOrderPrice());
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
