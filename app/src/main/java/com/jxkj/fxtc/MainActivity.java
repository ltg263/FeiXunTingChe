package com.jxkj.fxtc;


import android.content.pm.PackageManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.blankj.utilcode.util.ToastUtils;
import com.jxkj.fxtc.app.MainApplication;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.view.fragment.HomeFragment_1;
import com.jxkj.fxtc.view.fragment.HomeFragment_2;
import com.jxkj.fxtc.view.fragment.HomeFragment_3;

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

    private void openLocation() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
            //开启定位权限,200是标识码
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        } else {
            //开始定位
//            getCurrentLocationLatLng();
        }
    }

}
