package com.jxkj.fxtc.view.activity;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxkj.fxtc.R;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.conpoment.utils.IntentUtils;
import com.jxkj.fxtc.entity.AppointmentBean;

import butterknife.BindView;
import butterknife.OnClick;

public class BookingSpacePayActivity extends BaseActivity {

    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv_szcw)
    TextView tv_szcw;
    @BindView(R.id.tv_jcsj)
    TextView tv_jcsj;
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
    AppointmentBean data;
    @Override
    protected int getContentView() {
        return R.layout.activity_booking_space_pay;
    }

    @Override
    protected void initViews() {
        data = (AppointmentBean) getIntent().getSerializableExtra("data");
        if(data!=null){
            tv1.setText(data.getParkingName() + "-停车场");
            tv_dw.setText(data.getAddress());
            tv_jcsj.setText(data.getAppointmentTime());
            tv_yycp.setText(data.getLicense());
            tv_szcw.setText(data.getSeatID());
            tv_tcjs.setText(data.getUseTime());
            tv_yyf.setText("¥ "+data.getAppointmentPrice());
            tv_zfy.setText("¥ "+data.getOrderPrice());
        }
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

    public static void startActivityIntent(Context mContext, AppointmentBean appointmentBean) {
        IntentUtils.getInstence().intent(mContext,
                BookingSpacePayActivity.class, "data", appointmentBean);
    }

    public static void startActivityIntent(Context mContext, String id) {
        IntentUtils.getInstence().intent(mContext,
                BookingSpacePayActivity.class, "id", id);
    }

}
