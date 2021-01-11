package com.jxkj.fxtc.view.activity;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jxkj.fxtc.MainActivity;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.api.RetrofitUtil;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.base.Result;
import com.jxkj.fxtc.conpoment.utils.IntentUtils;
import com.jxkj.fxtc.entity.OrdersDetailBean;
import com.jxkj.fxtc.entity.PostCarData;

import butterknife.BindView;
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
    private OrdersDetailBean data;

    @Override
    protected int getContentView() {
        return R.layout.activity_booking_space_pay_1;
    }

    @Override
    protected void initViews() {
        id = getIntent().getStringExtra("id");
        getOrdersDetail();
    }


    @OnClick({R.id.iv_back, R.id.bnt, R.id.bnt_1,R.id.ll_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.bnt:
                postAppointment(data.getOrderNo());
                break;
            case R.id.bnt_1:
                goPay();
                break;
            case R.id.ll_2:
                IntentUtils.getInstence().intent(this, MineFqsqActivity.class);
                break;
        }
    }

    public static void startActivityIntent(Context mContext, String id) {
        IntentUtils.getInstence().intent(mContext,
                BookingSpacePayDeActivity.class, "id", id);
    }
    private void postAppointment(String orderNo){

        RetrofitUtil.getInstance().apiService()
                .postCancelAppointment(orderNo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            ToastUtils.showShort("取消预约成功");
                            IntentUtils.getInstence().intent(BookingSpacePayDeActivity.this, MainActivity.class);
                            finish();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });

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
                            data = result.getData();
                            tv1.setText(data.getLotName() + "-停车场");
                            tv_dw.setText(data.getAddress());
                            mTvJcsj.setText(data.getStartTime());
                            mTvYycp.setText(data.getLicense());
                            mTvSzcw.setText(data.getSeatName());
                            mTvTcjs.setText(data.getUseTime());
                            mTvYyf.setText(data.getAppointmentPrice());
                            mTvZfy.setText("￥"+data.getOrderPrice());
                            if(data.getStatus().equals("0")){
                                mLl0.setVisibility(View.VISIBLE);
                            }else{
                                mLl2.setVisibility(View.VISIBLE);
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

    private void goPay() {
        PostCarData.PayOrdersBaen dataOrders = new PostCarData.PayOrdersBaen();
        dataOrders.setOrderNo(data.getOrderNo());
        dataOrders.setPayType("3");//1微信2支付宝3余额
        RetrofitUtil.getInstance().apiService()
                .postPayOrders(dataOrders)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            IntentUtils.getInstence().intent(BookingSpacePayDeActivity.this,
                                    BookingSpaceOkActivity.class,"dataD",data);
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
