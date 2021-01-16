package com.jxkj.fxtc.view.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.blankj.utilcode.util.ToastUtils;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.api.RetrofitUtil;
import com.jxkj.fxtc.app.ConstValues;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.base.Result;
import com.jxkj.fxtc.conpoment.utils.IntentUtils;
import com.jxkj.fxtc.conpoment.utils.SharedUtils;
import com.jxkj.fxtc.conpoment.utils.StringUtil;
import com.jxkj.fxtc.entity.AppointmentBean;
import com.jxkj.fxtc.entity.DefaultCarBean;
import com.jxkj.fxtc.entity.LotListBean;
import com.jxkj.fxtc.entity.PostCarData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * IntentUtils.getInstence().intent(BookingSpaceActivity.this,BookingSpacePayActivity.class);
 */
public class BookingSpaceDeActivity extends BaseActivity implements LocationSource {

    @BindView(R.id.mMapView)
    MapView mMapView;
    AMap aMap;
    LotListBean.ListBean data;
    @BindView(R.id.tv_jg)
    TextView mTvJg;
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.tv_dw)
    TextView mTvDw;
    @BindView(R.id.tv_fy)
    TextView tv_fy;
    @BindView(R.id.et_cph)
    TextView mEtCph;
    @BindView(R.id.tvfy)
    TextView tvfy;
    @BindView(R.id.tv_rq_s)
    TextView tv_rq_s;
    @BindView(R.id.tv_rq_x)
    TextView tv_rq_x;
    @BindView(R.id.tv_rq_1)
    TextView mTvRq1;
    @BindView(R.id.iv_rq_1)
    ImageView mIvRq1;
    @BindView(R.id.iv_rq_11)
    ImageView mIvRq11;
    @BindView(R.id.tv_rq_2)
    TextView mTvRq2;
    @BindView(R.id.iv_rq_2)
    ImageView mIvRq2;
    @BindView(R.id.iv_rq_22)
    ImageView mIvRq22;
    @BindView(R.id.tv_rq_3)
    TextView mTvRq3;
    @BindView(R.id.iv_rq_3)
    ImageView mIvRq3;
    @BindView(R.id.iv_rq_33)
    ImageView mIvRq33;

    String staTime = "";
    String endTime = "";
    String time1 = "";
    String time2 = "";
    String time3 = "";
    @Override
    protected int getContentView() {
        return R.layout.activity_booking_space_de;
    }

    @Override
    protected void initViews() {
        data = (LotListBean.ListBean) getIntent().getSerializableExtra("data");
        if (data != null) {
            mTv1.setText(data.getParkingName() + "-停车场");
            String str = "<font color=\"#4ACCE0\">¥<big><big>" + data.getParkingPrice() + "</big></big></font>/小时";
            mTvJg.setText(Html.fromHtml(str));
            mTvDw.setText(data.getAddress());
            tvfy.setText("¥" + data.getAppointPrice());
            tv_fy.setText("¥" + data.getAppointPrice() + " · 立即支付");
        }
        Calendar c=Calendar.getInstance();
        //当前的day_of_month加一就是明天时间
        c.add(Calendar.DAY_OF_MONTH,1);
        time1 = "2021-"+(new SimpleDateFormat("MM-dd").format(c.getTime()));
        mTvRq1.setText(new SimpleDateFormat("MM/dd").format(c.getTime()));

        c.add(Calendar.DAY_OF_MONTH,1);
        time2 = "2021-"+(new SimpleDateFormat("MM-dd").format(c.getTime()));
        mTvRq2.setText(new SimpleDateFormat("MM/dd").format(c.getTime()));

        c.add(Calendar.DAY_OF_MONTH,1);
        time3 = "2021-"+(new SimpleDateFormat("MM-dd").format(c.getTime()));
        mTvRq3.setText(new SimpleDateFormat("MM/dd").format(c.getTime()));
        initMap();
    }


    private void initMap() {
        //这个功能是去掉地图的logo和放大缩小图标
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        aMap = mMapView.getMap();
        UiSettings mUiSettings = aMap.getUiSettings();
        mUiSettings.setZoomControlsEnabled(false);
//        mUiSettings.setAllGesturesEnabled(false);

        // 设置定位监听
        aMap.setLocationSource(this);
        // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationEnabled(true);
        // 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);


        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.i("lgq", "dianjiddd====" + marker.getPeriod());//获取markerID
//                getBoxReceive(marker.getPeriod()+"");
                return true;
            }
        });
    }

    OnLocationChangedListener mListener;
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;

    @Override
    public void activate(OnLocationChangedListener listener) {

        mListener = listener;
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(this);
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位回调监听
            mlocationClient.setLocationListener(new AMapLocationListener() {
                @Override
                public void onLocationChanged(AMapLocation aMapLocation) {
                    if (mListener != null && aMapLocation != null) {
                        if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                            mlocationClient.stopLocation();
                            mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                            initUiD(aMapLocation.getLongitude(), aMapLocation.getLatitude());
                        } else {
                            String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                            Log.e("AmapErr", errText);
                        }
                    }
                }
            });
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();//启动定位
        }
    }


    private void initUiD(double lng, double lat) {
        //绘制适应大小
        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();//存放所有点的经纬度
        boundsBuilder.include(new LatLng(lat, lng));

        LatLng latLng = new LatLng(Double.valueOf(data.getLat()), Double.valueOf(data.getLng()));
        MarkerOptions mMarkerOptions = new MarkerOptions().position(latLng);
        mMarkerOptions.icon(BitmapDescriptorFactory.
                fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_tcc)));
        mMarkerOptions.period(Integer.valueOf(data.getId()));
        aMap.addMarker(mMarkerOptions);
        boundsBuilder.include(latLng);//把所有点都include进去（LatLng类型）

        aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 200));//第二个参数为四周留空宽度
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getUserDetail();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }


    @OnClick({R.id.bnt_go, R.id.et_cph,R.id.iv_rq_1, R.id.iv_rq_11, R.id.iv_rq_2, R.id.iv_rq_22, R.id.iv_rq_3, R.id.iv_rq_33})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bnt_go:
                postAppointment();
                break;
            case R.id.et_cph:
                IntentUtils.getInstence().intent(this, MineClglActivity.class, "type", "0");
                break;
            case R.id.iv_rq_1:
                setUiSelectTime(mIvRq1,time1+" 06:00:00",time1+" 11:00:00");
                break;
            case R.id.iv_rq_11:
                setUiSelectTime(mIvRq11,time1+" 11:00:00",time1+" 16:00:00");
                break;
            case R.id.iv_rq_2:
                setUiSelectTime(mIvRq2,time2+" 06:00:00",time2+" 11:00:00");
                break;
            case R.id.iv_rq_22:
                setUiSelectTime(mIvRq22,time2+" 11:00:00",time2+" 16:00:00");
                break;
            case R.id.iv_rq_3:
                setUiSelectTime(mIvRq3,time3+" 06:00:00",time3+" 11:00:00");
                break;
            case R.id.iv_rq_33:
                setUiSelectTime(mIvRq33,time3+" 11:00:00",time3+" 16:00:00");
                break;
        }
    }
    private void setUiSelectTime(ImageView iv,String staTime,String endTime){
        mIvRq1.setBackground(getResources().getDrawable(R.drawable.shape_eee_line_5));
        mIvRq11.setBackground(getResources().getDrawable(R.drawable.shape_eee_line_5));
        mIvRq2.setBackground(getResources().getDrawable(R.drawable.shape_eee_line_5));
        mIvRq22.setBackground(getResources().getDrawable(R.drawable.shape_eee_line_5));
        mIvRq3.setBackground(getResources().getDrawable(R.drawable.shape_eee_line_5));
        mIvRq33.setBackground(getResources().getDrawable(R.drawable.shape_eee_line_5));

        iv.setBackground(getResources().getDrawable(R.drawable.shape_there_line_5));

        this.staTime = staTime;
        this.endTime = endTime;
    }

    private void postAppointment() {
        String sjh = SharedUtils.singleton().get(ConstValues.USER_PHONE, "");
        String cph = mEtCph.getText().toString();
        if (StringUtil.isBlank(sjh) || StringUtil.isBlank(cph) || StringUtil.isBlank(staTime) || StringUtil.isBlank(endTime)) {
            ToastUtils.showShort("信息不完整");
            return;
        }

        PostCarData.AppointmentInfo appointmentInfo = new PostCarData.AppointmentInfo();
        appointmentInfo.setMobile(sjh);
        appointmentInfo.setLicense(cph);
        appointmentInfo.setAppointmentTime(staTime);
        appointmentInfo.setAppointmentEndTime(endTime);
        appointmentInfo.setOrderType("1");
        appointmentInfo.setLotId(data.getId());
        show();
        RetrofitUtil.getInstance().apiService()
                .postAppointment(appointmentInfo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<AppointmentBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<AppointmentBean> result) {
                        if (isDataInfoSucceed(result)) {
                            result.getData().setAddress(data.getAddress());
                            result.getData().setParkingName(data.getParkingName());
                            result.getData().setParkingName(data.getParkingName());
                            result.getData().setLat(data.getLat());
                            result.getData().setLng(data.getLng());
                            BookingSpacePayActivity.startActivityIntent(BookingSpaceDeActivity.this, result.getData());
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


    private void getUserDetail() {
        RetrofitUtil.getInstance().apiService()
                .getDefaultCar()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<DefaultCarBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<DefaultCarBean> result) {
                        if (isDataInfoSucceed(result)) {
                            if (result.getData() != null) {
                                mEtCph.setText(result.getData().getLicense());
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