package com.jxkj.fxtc.view.activity;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.base.Result;
import com.jxkj.fxtc.conpoment.utils.PickerViewUtils;
import com.jxkj.fxtc.conpoment.utils.StringUtil;
import com.jxkj.fxtc.entity.LotListBean;
import com.jxkj.fxtc.entity.PostCarData;

import java.text.SimpleDateFormat;
import java.util.Locale;

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
    @BindView(R.id.start_time)
    TextView mStartTime;
    @BindView(R.id.end_time)
    TextView mEndTime;
    @BindView(R.id.et_cph)
    EditText mEtCph;
    @BindView(R.id.et_sjh)
    EditText mEtSjh;

    @Override
    protected int getContentView() {
        return R.layout.activity_booking_space_de;
    }

    @Override
    protected void initViews() {
        data = (LotListBean.ListBean) getIntent().getSerializableExtra("data");
        if (data != null) {
            mTv1.setText(data.getParkingName() + "-停车场");
            String str = "<font color=\"#0199FC\">¥<big><big>" + data.getParkingPrice() + "</big></big></font>/小时";
            mTvJg.setText(Html.fromHtml(str));
            mTvDw.setText(data.getAddress());
        }
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


    @OnClick({R.id.start_time, R.id.end_time, R.id.bnt_go})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start_time:
                PickerViewUtils.selectorDate(2021, 2021, this,
                        new boolean[]{false, true, true, true, true, false},
                        time -> mStartTime.setText(new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(time)));
                break;
            case R.id.end_time:
                PickerViewUtils.selectorDate(2021, 2021, this,
                        new boolean[]{false, true, true, true, true, false},
                        time -> mEndTime.setText(new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(time)));
                break;
            case R.id.bnt_go:
                postAppointment();
                break;
        }
    }
    private void postAppointment(){
        String sjh = mEtSjh.getText().toString();
        String cph = mEtCph.getText().toString();
        String staTime = mStartTime.getText().toString();
        String endTime = mEndTime.getText().toString();
        if(StringUtil.isBlank(sjh) ||StringUtil.isBlank(cph) ||StringUtil.isBlank(staTime) ||StringUtil.isBlank(endTime)){
            ToastUtils.showShort("信息不完整");
            return;
        }

        PostCarData.AppointmentInfo appointmentInfo = new PostCarData.AppointmentInfo();
        appointmentInfo.setMobile(sjh);
        appointmentInfo.setLicense(cph);
        appointmentInfo.setAppointmentTime("2021-"+staTime+":00");
        appointmentInfo.setAppointmentEndTime("2021-"+endTime+":00");
        appointmentInfo.setOrderType("1");
        appointmentInfo.setSeatID(data.getId());
        show();
        RetrofitUtil.getInstance().apiService()
                .postAppointment(appointmentInfo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            ToastUtils.showShort("添加成功");
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
}