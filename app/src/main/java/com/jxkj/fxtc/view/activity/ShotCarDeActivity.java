package com.jxkj.fxtc.view.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.deepexp.zsnavi.bean.CoordinateBean;
import com.deepexp.zsnavi.bean.OptionBean;
import com.deepexp.zsnavi.core.ZsnaviManager;
import com.deepexp.zsnavi.enums.NaviWay;
import com.gyf.immersionbar.ImmersionBar;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.conpoment.utils.IntentUtils;
import com.jxkj.fxtc.conpoment.utils.ZsnaviMapUtils;
import com.jxkj.fxtc.entity.LotListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 1000D 订单管理
 */
public class ShotCarDeActivity extends BaseActivity implements LocationSource {

    @BindView(R.id.mMapView)
    MapView mMapView;
    AMap aMap;
    LotListBean.ListBean data;
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.iv1)
    ImageView mIv1;
    @BindView(R.id.iv2)
    ImageView mIv2;
    @BindView(R.id.iv3)
    ImageView mIv3;
    @BindView(R.id.tv_jg)
    TextView mTvJg;
    @BindView(R.id.tv_dw)
    TextView mTvDw;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.rl)
    RelativeLayout mRl;
    @BindView(R.id.tv_1)
    TextView mTv11;
    @BindView(R.id.tv_2)
    TextView mTv2;
    @BindView(R.id.tv_3)
    TextView mTv3;
    @BindView(R.id.tv_4)
    TextView mTv4;
    @BindView(R.id.tv_sfbz1)
    TextView tv_sfbz1;
    @BindView(R.id.tv_yysj1)
    TextView tv_yysj1;
    @BindView(R.id.tv_cws)
    TextView tv_cws;
    @BindView(R.id.bnt_go)
    TextView bnt_go;
    @BindView(R.id.bnt_go1)
    TextView bnt_go1;

    @Override
    protected int getContentView() {
        return R.layout.activity_shot_car;
    }

    @Override
    protected void initViews() {
        data = (LotListBean.ListBean) getIntent().getSerializableExtra("data");
        if(data!=null){
            mTv1.setText(data.getParkingName() + "-停车场");
            String str = "<font color=\"#0199FC\">¥<big><big>" + data.getParkingPrice() + "</big></big></font>/小时";
            mTvJg.setText(Html.fromHtml(str));
            mTvDw.setText(data.getAddress());
            mTvContent.setText("营业时间：" + data.getStartTime().substring(11,16) + "-" + data.getEndTime().substring(11,16));
            mTv2.setText(data.getDescription());
            mTv4.setText("共 "+data.getSlotPrice()+" 个车位，剩余车位 "+data.getSeatCount()+" 个。");
            tv_sfbz1.setText(data.getDescription());
            tv_yysj1.setText(data.getStartTime().substring(11,16) + "-" + data.getEndTime().substring(11,16));
            tv_cws.setText(data.getSlotPrice());
        }
        initMap();
        bnt_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZsnaviMapUtils.openNavi(ShotCarDeActivity.this,data.getParkingName(),
                        NaviWay.Drive,Double.valueOf(data.getLat()), Double.valueOf(data.getLng()),
                        data.getMapCode(),data.getPoiName());
            }
        });
        bnt_go1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.getInstence().intent(ShotCarDeActivity.this, WebViewActivity.class,"data",data);
            }
        });
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
                            initUiD(aMapLocation.getLongitude(),aMapLocation.getLatitude());
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
        boundsBuilder.include(new LatLng(lat,lng));

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
}