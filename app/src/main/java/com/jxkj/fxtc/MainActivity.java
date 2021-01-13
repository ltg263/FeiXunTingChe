package com.jxkj.fxtc;


import android.content.pm.PackageManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.ShellUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.deepexp.zsnavi.bean.CoordinateBean;
import com.deepexp.zsnavi.callback.ILocationCallback;
import com.deepexp.zsnavi.core.ZsnaviManager;
import com.jxkj.fxtc.app.MainApplication;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.conpoment.utils.SharedUtils;
import com.jxkj.fxtc.view.deme.ZsnaviDemoActivity;
import com.jxkj.fxtc.view.fragment.HomeFragment_1;
import com.jxkj.fxtc.view.fragment.HomeFragment_2;
import com.jxkj.fxtc.view.fragment.HomeFragment_3;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fl_content)
    FrameLayout mFlContent;
    @BindView(R.id.iv_main_1)
    ImageView mIvMain1;
    @BindView(R.id.ll_main_1)
    LinearLayout mLlMain1;
    @BindView(R.id.iv_main_2)
    ImageView mIvMain2;
    @BindView(R.id.ll_main_2)
    LinearLayout mLlMain2;
    @BindView(R.id.iv_main_3)
    ImageView mIvMain3;
    @BindView(R.id.ll_main_3)
    LinearLayout mLlMain3;
    @BindView(R.id.tv_main_1)
    TextView mTvMain1;
    @BindView(R.id.tv_main_2)
    TextView mTvMain2;
    @BindView(R.id.tv_main_3)
    TextView mTvMain3;
    private Fragment mFragments;
    private HomeFragment_1 mHomeFragment1;
    private HomeFragment_2 mHomeFragment2;
    private HomeFragment_3 mHomeFragment3;

    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;


    //声明mlocationClient对象
    public AMapLocationClient mlocationClient;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        MainApplication.addActivity(this);
        mHomeFragment1 = HomeFragment_1.newInstance();
        mHomeFragment2 = HomeFragment_2.newInstance();
        mHomeFragment3 = HomeFragment_3.newInstance();

        fragmentManager = getSupportFragmentManager();

        mFragments = mHomeFragment1;
        howFragment(1,mIvMain1,mTvMain1);
        fragmentManager.beginTransaction().replace(R.id.fl_content, mFragments, "A").commitAllowingStateLoss();

        openLocation();
//        setDw();
    }

    @OnClick({R.id.ll_main_1, R.id.ll_main_2, R.id.ll_main_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_main_1:
                howFragment(1,mIvMain1,mTvMain1);
                switchFragment(mHomeFragment1,"A");
                break;
            case R.id.ll_main_2:
                howFragment(2,mIvMain2,mTvMain2);
                switchFragment(mHomeFragment2,"B");
                break;
            case R.id.ll_main_3:
                howFragment(3,mIvMain3,mTvMain3);
                switchFragment(mHomeFragment3,"C");
                break;
        }
    }

    public void homeBack(int pos){

        switch (pos) {
            case 1:
                howFragment(1,mIvMain1,mTvMain1);
                switchFragment(mHomeFragment1,"A");
                break;
            case 2:
                howFragment(2,mIvMain2,mTvMain2);
                switchFragment(mHomeFragment2,"B");
                break;
            case 3:
                howFragment(3,mIvMain3,mTvMain3);
                switchFragment(mHomeFragment3,"C");
                break;
        }
    }


    private void howFragment(int pos, ImageView iv,TextView tv){
        mIvMain1.setSelected(false);
        mIvMain2.setSelected(false);
        mIvMain3.setSelected(false);
        mTvMain1.setTextColor(getResources().getColor(R.color.main_home_no));
        mTvMain2.setTextColor(getResources().getColor(R.color.main_home_no));
        mTvMain3.setTextColor(getResources().getColor(R.color.main_home_no));

        tv.setTextColor(getResources().getColor(R.color.main_home_yes));
        iv.setSelected(true);
    }

    /**
     * 切换Fragment
     * <p>(hide、show、add)860722
     *
     */
    private void switchFragment(Fragment mCurrentFragment, String tag) {
        if (mFragments != mCurrentFragment) {
            fragmentTransaction = fragmentManager.beginTransaction();
            if (!mCurrentFragment.isAdded() && null == fragmentManager.findFragmentByTag(tag)) {    // 先判断是否被add过
                fragmentTransaction.hide(mFragments).add(R.id.fl_content, mCurrentFragment, tag).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到activity中, 并添加已显示存在的fangment唯一标示tag
            } else {
                fragmentTransaction.hide(mFragments).show(mCurrentFragment).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
            }
            mFragments = mCurrentFragment;
        }
    }


    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (mFragments != mHomeFragment1) {
                homeBack(1);
            } else {
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    ToastUtils.showShort("再按一次退出" + getString(R.string.app_name));
                    exitTime = System.currentTimeMillis();
                } else {
                    finish();
                    System.exit(0);
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 开始定位（使用定位前必须请求定位权限，否则定位失败）
     */
    private void openLocation() {
        ZsnaviManager.getInstance(this).setOnLocationCallback(locationCallback);//设置定位回调
        ZsnaviManager.getInstance(this).startLocation();//开启定位，该定位只会回调一次定位信息，建议使用完后调用停止定位接口
    }


    /**
     * 定位回调，定位成功后才能计算距离
     */
    ILocationCallback locationCallback = new ILocationCallback() {
        @Override
        public void onLocationSuccess(CoordinateBean position) {

            SharedUtils.singleton().put("Latitude",position.getLatitude()+"");
            SharedUtils.singleton().put("Longitude",position.getLongitude()+"");
            ZsnaviManager.getInstance(MainActivity.this).stopLocation();//因为是一次定位，建议每次用完后关闭
//            calDistance(position);
        }

        @Override
        public void onLocationFailure() {
            Toast.makeText(MainActivity.this, "定位坐标失败", Toast.LENGTH_SHORT).show();
            ZsnaviManager.getInstance(MainActivity.this).stopLocation();//因为是一次定位，建议每次用完后关闭
        }
    };
    /**
     * 计算距离
     *
     */
//    private void calDistance(CoordinateBean position) {
//        List<CoordinateBean> ends = new ArrayList<>();
//
//        ends.add(new CoordinateBean(Double.valueOf(mEdtLat.getText().toString()), Double.valueOf(mEdtLon.getText().toString())));
//
//        List<Float> distances = ZsnaviManager.getInstance(this).calculateDistance(position, ends);//计算距离
//
//        Float distance = distances != null && distances.size() > 0 ? distances.get(0) : 0f;
//
//        Toast.makeText(ZsnaviDemoActivity.this, "定位距离" + distance, Toast.LENGTH_SHORT).show();
//    }

    private void setDw() {
        mlocationClient = new AMapLocationClient(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位监听
        mlocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        Log.e("AmapError","location Error, ErrCode:"+aMapLocation.getLatitude());
                        //定位成功回调信息，设置相关消息
                        aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                        aMapLocation.getLatitude();//获取纬度
                        aMapLocation.getLongitude();//获取经度
                        aMapLocation.getAccuracy();//获取精度信息
                        SharedUtils.singleton().put("Latitude",aMapLocation.getLatitude()+"");
                        SharedUtils.singleton().put("Longitude",aMapLocation.getLongitude()+"");
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(aMapLocation.getTime());
                        df.format(date);//定位时间
                    } else {
                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError","location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                    }
                }
            }
        });
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        mlocationClient.startLocation();
    }


}
