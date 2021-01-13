package com.jxkj.fxtc.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.viewpager.widget.ViewPager;

import com.jxkj.fxtc.MainActivity;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.app.ConstValues;
import com.jxkj.fxtc.app.MainApplication;
import com.jxkj.fxtc.conpoment.utils.GlideImageLoader;
import com.jxkj.fxtc.conpoment.utils.SharedUtils;
import com.jxkj.fxtc.entity.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class SplashScreenActivity extends Activity {

    Banner mBanner;
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        //去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splashscreen);
        mBanner = findViewById(R.id.banner);
        initBanner();
    }


    private void initBanner() {
        List<Integer> bannerUrls = new ArrayList<>();
        bannerUrls.add(R.mipmap.home_splash_1);
        bannerUrls.add(R.mipmap.home_splash_2);
        bannerUrls.add(R.mipmap.home_splash_3);
//        mBanner.setOnBannerListener(new OnBannerListener() {
//            @Override
//            public void OnBannerClick(int position) {
////                Log.i("tag", "你点了第" + position + "张轮播图:" + titles.get(position));
//                if(position==2){
//                    new Handler().postDelayed(() -> startUi(), 3000);
//                }
//            }
//        });

        mBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==2){
                    new Handler().postDelayed(() -> startUi(), 2000);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

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


    private void startUi() {
        MainApplication.addActivity(this);
        startActivity(new Intent(this, MainActivity.class));
//        if(SharedUtils.singleton().get(ConstValues.ISLOGIN,false)){
//            startActivity(new Intent(this, MainActivity.class));
//        }else{
//            LoginActivity.startActivityIntent(this);
//        }
        finish();
    }


}
