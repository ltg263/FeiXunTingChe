package com.jxkj.fxtc.view.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.deepexp.zsnavi.bean.CoordinateBean;
import com.deepexp.zsnavi.core.ZsnaviManager;
import com.deepexp.zsnavi.enums.NaviWay;
import com.jxkj.fxtc.MainActivity;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.conpoment.utils.IntentUtils;
import com.jxkj.fxtc.entity.AppointmentBean;
import com.jxkj.fxtc.entity.OrdersDetailBean;

import butterknife.BindView;
import butterknife.OnClick;

public class BookingSpaceOkActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_zt)
    TextView mTvZt;
    @BindView(R.id.tv_sm)
    TextView mTvSm;
    @BindView(R.id.tv_dz)
    TextView mTvDz;
    @BindView(R.id.tv_cw)
    TextView mTvCw;
    @BindView(R.id.tv_c)
    TextView mTvC;
    @BindView(R.id.tv_fy)
    TextView mTvFy;
    @BindView(R.id.tv_z)
    TextView tv_z;
    private AppointmentBean data;
    private OrdersDetailBean dataD;

    @Override
    protected int getContentView() {
        return R.layout.activity_booking_space_ok;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("预约成功");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        data = (AppointmentBean) getIntent().getSerializableExtra("data");
        dataD = (OrdersDetailBean)getIntent().getSerializableExtra("dataD");
        if(dataD!=null){
            mTvDz.setText(dataD.getAddress());
            mTvCw.setText("车位："+dataD.getSeatName());
            mTvC.setText("车牌："+dataD.getLicense());
            mTvFy.setText("￥："+dataD.getOrderPrice());
            tv_z.setText("预留至:"+dataD.getExpressTime());
        }
        if(data!=null){
            mTvDz.setText(data.getAddress());
            mTvCw.setText("车位："+data.getSeatName());
            mTvC.setText("车牌："+data.getLicense());
            mTvFy.setText("￥："+data.getOrderPrice());
            tv_z.setText("预留至:"+data.getExpressTime());
        }
    }

    @OnClick({R.id.ll_back, R.id.bnt, R.id.bnt_1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.bnt:
                ZsnaviManager.getInstance(this).startNavi(NaviWay.Drive,
                        new CoordinateBean(Double.valueOf(data.getLat()), Double.valueOf(data.getLng())), true);//开启导航
                break;
            case R.id.bnt_1:
                IntentUtils.getInstence().intent(BookingSpaceOkActivity.this, MainActivity.class);
                finish();
                break;
        }
    }
}
