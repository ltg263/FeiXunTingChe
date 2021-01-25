package com.jxkj.fxtc.view.activity;

import android.content.Intent;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.deepexp.zsnavi.enums.NaviWay;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.conpoment.utils.ZsnaviMapUtils;
import com.jxkj.fxtc.conpoment.view.MyWebView;
import com.jxkj.fxtc.entity.LotListBean;

import butterknife.BindView;
import butterknife.OnClick;

public class WebViewActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.rl)
    RelativeLayout mRl;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.web)
    MyWebView mWeb;

    LotListBean.ListBean data;
    @BindView(R.id.mane)
    TextView mMane;
    @BindView(R.id.tv_dz)
    TextView mTvDz;

    @Override
    protected int getContentView() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initViews() {
        data = (LotListBean.ListBean) getIntent().getSerializableExtra("data");
        mIvBack.setImageDrawable(getResources().getDrawable(R.mipmap.back_h));
        mTvTitle.setText("车位导航");
        setWebViewClient();
        switch (data.getParkingName()){
            case "樱花公园":
                mWeb.loadUrl("https://unimap.co/demo/sakura_park/");
                break;
            case "桂花城路侧":
                mWeb.loadUrl("https://unimap.co/demo/gangdao_road/");
                break;
            case "翁浦公园":
                mWeb.loadUrl("https://unimap.co/demo/wengpu/");
                break;
            case "岱山县政府":
                mWeb.loadUrl("https://unimap.co/demo/daishan/");
                break;
        }
        mMane.setText(data.getParkingName()+ "-停车场");
        mTvDz.setText(data.getAddress());
    }

    private void setWebViewClient() {
        WebSettings webSettings = mWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);//支持js交互，可以点击网页中按钮链接
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//支持js可以打开新的页面
        webSettings.setSupportZoom(true);//是否可以缩放，默认true
        webSettings.setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webSettings.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        webSettings.setAppCacheEnabled(false);//是否使用缓存
        webSettings.setDomStorageEnabled(true);//DOM Storage


//        mWeb.setOnTouchScreenListener(new MyWebView.OnTouchScreenListener() {
//
//            @Override
//            public void onTouchScreen() {
//                Log.w("-->>","++++++");
////                isFlowing = true;
//                if (mRl.getVisibility() == View.GONE) {
//                    mRl.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onReleaseScreen() {
////                isFlowing = false;
//                Log.w("-->>","--->>");
//                if (mRl.getVisibility() == View.VISIBLE) {
//                    mRl.setVisibility(View.GONE);
//                }
//            }
//        });
    }

    @OnClick({R.id.ll_back, R.id.bnt, R.id.bnt_1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                break;
            case R.id.bnt:
                Intent mIntent = new Intent(this, BookingSpaceDeActivity.class);
                mIntent.putExtra("data", data);
                startActivity(mIntent);
                break;
            case R.id.bnt_1:

                ZsnaviMapUtils.openNavi(this, data.getParkingName(),
                        NaviWay.Drive, Double.valueOf(data.getLat()), Double.valueOf(data.getLng()),
                        data.getMapCode(), data.getPoiName());
                break;
        }
    }
}