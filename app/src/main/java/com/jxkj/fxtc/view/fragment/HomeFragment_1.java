package com.jxkj.fxtc.view.fragment;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jxkj.fxtc.MainActivity;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.api.RetrofitUtil;
import com.jxkj.fxtc.base.BaseFragment;
import com.jxkj.fxtc.base.Result;
import com.jxkj.fxtc.conpoment.utils.GlideImageLoader;
import com.jxkj.fxtc.conpoment.utils.IntentUtils;
import com.jxkj.fxtc.entity.HomeBean;
import com.jxkj.fxtc.view.activity.AddCarActivity;
import com.jxkj.fxtc.view.activity.BookingSpaceActivity;
import com.jxkj.fxtc.view.activity.MineClglActivity;
import com.jxkj.fxtc.view.activity.SeekCarActivity;
import com.jxkj.fxtc.view.activity.ShopCarLogActivity;
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
public class HomeFragment_1 extends BaseFragment {


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

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_1;
    }

    @Override
    protected void initViews() {
        getHome();
    }

    @Override
    public void onResume() {
        super.onResume();
        getHome();
    }

    @Override
    public void initImmersionBar() {
        getHome();
    }

    public static HomeFragment_1 newInstance() {
        HomeFragment_1 homeFragment = new HomeFragment_1();
        return homeFragment;
    }


    @OnClick({R.id.btn_home_1, R.id.btn_home_2, R.id.btn_home_3, R.id.btn_home_4,R.id.rl_add_car,R.id.tv_car_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_home_1:
                IntentUtils.getInstence().intent(getActivity(), BookingSpaceActivity.class);
                break;
            case R.id.btn_home_2:
                IntentUtils.getInstence().intent(getActivity(), SeekCarActivity.class);
                break;
            case R.id.btn_home_3:
                ((MainActivity) getActivity()).homeBack(2);
                break;
            case R.id.btn_home_4:
                IntentUtils.getInstence().intent(getActivity(), ShopCarLogActivity.class);
                break;
            case R.id.rl_add_car:
                IntentUtils.getInstence().intent(getActivity(), AddCarActivity.class);
                break;
            case R.id.tv_car_name:
                IntentUtils.getInstence().intent(getActivity(), MineClglActivity.class,"type","0");
                break;
        }
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
                                rl_add_car.setVisibility(View.GONE);
                                rl_car.setVisibility(View.VISIBLE);
                                initUserCar(result.getData().getUserCar());
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
        if(userCar.getStatus().equals("2")){//0未停车1已停车2已预约
            mTvCarName.setText(userCar.getParkingSeatDTO().getLicense());
            mTvCarJg.setText(userCar.getParkingSeatDTO().getUseTime());
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
}