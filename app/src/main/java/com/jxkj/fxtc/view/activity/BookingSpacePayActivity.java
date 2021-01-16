package com.jxkj.fxtc.view.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jxkj.fxtc.MainActivity;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.api.RetrofitUtil;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.base.Result;
import com.jxkj.fxtc.conpoment.utils.IntentUtils;
import com.jxkj.fxtc.conpoment.utils.ToastUtil;
import com.jxkj.fxtc.entity.AppointmentBean;
import com.jxkj.fxtc.entity.PostCarData;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BookingSpacePayActivity extends BaseActivity {

    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv_szcw)
    TextView tv_szcw;
    @BindView(R.id.tv_jcsj)
    TextView tv_jcsj;
    @BindView(R.id.tv_jfsj)
    TextView tv_jfsj;
    @BindView(R.id.tv_yycp)
    TextView tv_yycp;
    @BindView(R.id.tv_dw)
    TextView tv_dw;
    @BindView(R.id.tv_tcjs)
    TextView tv_tcjs;
    @BindView(R.id.tv_yyf)
    TextView tv_yyf;
    @BindView(R.id.tv_zfy)
    TextView tv_zfy;
    AppointmentBean dataYY;
    @Override
    protected int getContentView() {
        return R.layout.activity_booking_space_pay;
    }

    @Override
    protected void initViews() {
        dataYY = (AppointmentBean) getIntent().getSerializableExtra("dataYY");
        if(dataYY!=null){
            tv1.setText(dataYY.getParkingName() + "-停车场");
            tv_dw.setText(dataYY.getAddress());
            tv_jcsj.setText(dataYY.getAppointmentTime());
            tv_yycp.setText(dataYY.getLicense());
            tv_szcw.setText(dataYY.getSeatName());
            tv_tcjs.setText(dataYY.getUseTime()+"小时");
            tv_yyf.setText("¥ "+dataYY.getAppointmentPrice());
            tv_zfy.setText("¥ "+dataYY.getOrderPrice());
            tv_jfsj.setText(dataYY.getExpressTime());
        }
    }


    @OnClick({R.id.iv_back, R.id.bnt, R.id.bnt_1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.bnt:
                postAppointment(dataYY.getOrderNo());
                break;
            case R.id.bnt_1:
                goPay();
                break;
        }
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
                            IntentUtils.getInstence().intent(BookingSpacePayActivity.this, MainActivity.class);
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



    private void goPay() {
        PostCarData.PayOrdersBaen dataOrders = new PostCarData.PayOrdersBaen();
        dataOrders.setOrderNo(dataYY.getOrderNo());
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
                            IntentUtils.getInstence().intent(BookingSpacePayActivity.this,
                                    BookingSpaceOkActivity.class,"data",dataYY);
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

    public static void startActivityIntent(Context mContext, AppointmentBean appointmentBean) {
        Intent mIntent = new Intent(mContext,BookingSpacePayActivity.class);
        mIntent.putExtra("dataYY",appointmentBean);
        mContext.startActivity(mIntent);
    }
}
