package com.jxkj.fxtc.view.fragment;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

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
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fxtc.MainActivity;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.api.RetrofitUtil;
import com.jxkj.fxtc.base.BaseFragment;
import com.jxkj.fxtc.base.Result;
import com.jxkj.fxtc.conpoment.utils.GlideImageLoader;
import com.jxkj.fxtc.conpoment.utils.IntentUtils;
import com.jxkj.fxtc.conpoment.utils.SharedUtils;
import com.jxkj.fxtc.conpoment.utils.StringUtil;
import com.jxkj.fxtc.conpoment.widget.MyRecyclerView;
import com.jxkj.fxtc.entity.HomeBean;
import com.jxkj.fxtc.entity.LotListBean;
import com.jxkj.fxtc.view.activity.AddCarActivity;
import com.jxkj.fxtc.view.activity.BookingSpaceActivity;
import com.jxkj.fxtc.view.activity.MineClglActivity;
import com.jxkj.fxtc.view.activity.SeekCarActivity;
import com.jxkj.fxtc.view.activity.ShopCarLogActivity;
import com.jxkj.fxtc.view.activity.ShotCarDeActivity;
import com.jxkj.fxtc.view.adapter.BookingSpaceAdapter;
import com.jxkj.fxtc.view.search.SearchGoodsActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 1000D 订单管理
 */
public class HomeFragment_1 extends BaseFragment implements LocationSource {


    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.banner_ad)
    Banner mBannerAd;
    @BindView(R.id.tv_car_name)
    TextView mTvCarName;
    @BindView(R.id.tv_car_jg)
    TextView mTvCarJg;
    @BindView(R.id.tv_car_cw)
    TextView mTvCarCw;
    @BindView(R.id.tv_car_sj)
    TextView mTvCarSj;
    @BindView(R.id.rl_add_car)
    RelativeLayout rl_add_car;
    @BindView(R.id.rl_car)
    RelativeLayout rl_car;
    @BindView(R.id.rv_list)
    MyRecyclerView mRvList;
    @BindView(R.id.mMapView)
    MapView mMapView;
    AMap aMap;
    String carId = "";
    private BookingSpaceAdapter mBookingSpaceAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_1;
    }

    @Override
    protected void initViews() {

        mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvList.setHasFixedSize(true);
        mRvList.setVisibility(View.VISIBLE);
        mBookingSpaceAdapter = new BookingSpaceAdapter(null);
        mRvList.setAdapter(mBookingSpaceAdapter);

        mBookingSpaceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LotListBean.ListBean data = mBookingSpaceAdapter.getData().get(position);
                Intent mIntent = new Intent(getActivity(), ShotCarDeActivity.class);
                mIntent.putExtra("data",data);
                startActivity(mIntent);
            }
        });
        initMap();
        getHome();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            mMapView.setVisibility(View.INVISIBLE);
        }else {
            mMapView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initImmersionBar() {
//        getHome();
    }

    public static HomeFragment_1 newInstance() {
        HomeFragment_1 homeFragment = new HomeFragment_1();
        return homeFragment;
    }


    @OnClick({R.id.ll_search,R.id.btn_home_1, R.id.btn_home_2, R.id.btn_home_3, R.id.btn_home_4,R.id.rl_add_car,R.id.tv_car_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_home_1:
                IntentUtils.getInstence().intent(getActivity(), BookingSpaceActivity.class);
                break;
            case R.id.btn_home_2:
                if(StringUtil.isBlank(carId)){
                    ToastUtils.showShort("无车辆在停车");
                    return;
                }
                IntentUtils.getInstence().intent(getActivity(), SeekCarActivity.class,"carName",mTvCarName.getText().toString().trim());
                break;
            case R.id.btn_home_3:
                ((MainActivity) getActivity()).homeBack(2);
                break;
            case R.id.btn_home_4:
                IntentUtils.getInstence().intent(getActivity(), ShopCarLogActivity.class);
                break;
            case R.id.rl_add_car:
                AddCarActivity.startActivityIntent(getActivity(),"","");
                break;
            case R.id.tv_car_name:
                AddCarActivity.startActivityIntent(getActivity(),carId,mTvCarName.getText().toString().trim());
                break;
            case R.id.ll_search:
                IntentUtils.getInstence().intent(getActivity(), SearchGoodsActivity.class,"searchType",2);
                break;
        }
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
                Log.i("lgq","dianjiddd===="+marker.getPeriod());//获取markerID
//                getBoxReceive(marker.getPeriod()+"");
                return true;
            }
        });
    }

    private void getHome() {
        RetrofitUtil.getInstance().apiService()
                .getHome()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<HomeBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<HomeBean> result) {
                        if (isDataInfoSucceed(result)) {
                            if (result.getData().getBanners() != null) {
                                initBanner(result.getData().getBanners());
                            }
                            if (result.getData().getUserCar() != null) {
//                                rl_add_car.setVisibility(View.GONE);
//                                rl_car.setVisibility(View.VISIBLE);
//                                initUserCar(result.getData().getUserCar());
                            }
                            if (result.getData().getAd() != null) {
                                initBannerAd(result.getData().getAd());
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

    private void initBannerAd(List<HomeBean.AdBean> ad) {
        List<String> bannerUrls = new ArrayList<>();
        for (int i = 0; i < ad.size(); i++) {
            bannerUrls.add(ad.get(i).getImgUrl());
        }
        mBannerAd.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
//                Log.i("tag", "你点了第" + position + "张轮播图:" + titles.get(position));
            }
        });

        mBannerAd.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        mBannerAd.setIndicatorGravity(BannerConfig.CENTER);

        //设置图片加载器
        mBannerAd.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBannerAd.setImages(bannerUrls);
        //设置banner动画效果
//        mTopBanner.setBannerAnimation(Transformer.Stack);
        //设置自动轮播，默认为true
        mBannerAd.isAutoPlay(true);
        //设置轮播时间
        mBannerAd.setDelayTime(3000);
        //banner设置方法全部调用完毕时最后调用
        mBannerAd.start();
    }

    private void initUserCar(HomeBean.UserCarBean userCar) {
        carId = userCar.getId();
        if(userCar.getStatus().equals("2")){//0未停车1已停车2已预约
            mTvCarName.setText(userCar.getParkingSeatDTO().getLicense());
            mTvCarJg.setText(StringUtil.getUseTime(userCar.getParkingSeatDTO().getUseTime()));
            mTvCarCw.setText(userCar.getParkingSeatDTO().getSeatName());
            mTvCarSj.setText(userCar.getParkingSeatDTO().getDelTF());
        }else{
            mTvCarName.setText(userCar.getLicense());
        }
    }

    private void initBanner(List<HomeBean.BannersBean> ad) {
        List<String> bannerUrls = new ArrayList<>();
        for (int i = 0; i < ad.size(); i++) {
            bannerUrls.add(ad.get(i).getImgUrl());
        }
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
//                Log.i("tag", "你点了第" + position + "张轮播图:" + titles.get(position));
            }
        });

        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        mBanner.setIndicatorGravity(BannerConfig.CENTER);

        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(bannerUrls);
        //设置banner动画效果
//        mTopBanner.setBannerAnimation(Transformer.Stack);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(3000);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    OnLocationChangedListener mListener;
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;

    @Override
    public void activate(OnLocationChangedListener listener) {

        mListener = listener;
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(getActivity());
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位回调监听
            mlocationClient.setLocationListener(new AMapLocationListener() {
                @Override
                public void onLocationChanged(AMapLocation aMapLocation) {
                    if (mListener != null&&aMapLocation != null) {
                        if (aMapLocation != null &&aMapLocation.getErrorCode() == 0) {
                            mlocationClient.stopLocation();
                            mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                            getLotList(aMapLocation.getLongitude(),aMapLocation.getLatitude());
                        } else {
                            String errText = "定位失败," + aMapLocation.getErrorCode()+ ": " + aMapLocation.getErrorInfo();
                            Log.e("AmapErr",errText);
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

    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    private void getLotList(double lng,double lat) {
        RetrofitUtil.getInstance().apiService()
                .getLotList(null, null, String.valueOf(lng), String.valueOf(lat))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<LotListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<LotListBean> result) {
                        if (isDataInfoSucceed(result)) {
                            mBookingSpaceAdapter.setNewData(result.getData().getList());
//                            initUiD(result.getData().getList(),lng,lat);
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

    private void initUiD(List<LotListBean.ListBean> infos, double lng, double lat) {
        //绘制适应大小
        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();//存放所有点的经纬度
        boundsBuilder.include(new LatLng(lat,lng));
        for (int i = 0; i < infos.size(); i++) {
            LotListBean.ListBean info = infos.get(i);
            LatLng latLng = new LatLng(Double.valueOf(info.getLat()), Double.valueOf(info.getLng()));
            MarkerOptions mMarkerOptions = new MarkerOptions().position(latLng);
            mMarkerOptions.icon(BitmapDescriptorFactory.
                    fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_tcc)));
            mMarkerOptions.period(Integer.valueOf(info.getId()));
            aMap.addMarker(mMarkerOptions);
            boundsBuilder.include(latLng);//把所有点都include进去（LatLng类型）

        }
        aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 200));//第二个参数为四周留空宽度
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
        if(null != mlocationClient){
            mlocationClient.onDestroy();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        getHome();
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