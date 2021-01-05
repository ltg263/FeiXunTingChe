package com.jxkj.fxtc.view.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.api.RetrofitUtil;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.base.Result;
import com.jxkj.fxtc.conpoment.utils.GlideImgLoader;
import com.jxkj.fxtc.entity.UserDetailBean;
import com.jxkj.fxtc.entity.UserEnvelopesBean;
import com.jxkj.fxtc.view.fragment.MineYhqFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineYhqActivity extends BaseActivity {
    String[] tabListBlq = {"已使用", "未使用", "已过期"};
    @BindView(R.id.tabs_tribe)
    TabLayout mTabTribe;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_actionbar)
    RelativeLayout rlActionbar;
    public static final int ORDER  = 1010;
    private int position;

    @Override
    protected int getContentView() {
        return R.layout.activity_my_yhq;
    }
    private void initTitle() {
        ivBack.setImageDrawable(getResources().getDrawable(R.mipmap.back_h));
        ImmersionBar.with(this).statusBarDarkFont(true).titleBar(R.id.rl_actionbar).fitsSystemWindows(true).init();
        tvTitle.setText("停车券");
    }
    @Override
    protected void initViews() {
        mTabTribe.setTabMode(TabLayout.MODE_FIXED);
        mTabTribe.setTabGravity(TabLayout.GRAVITY_FILL);
        position = getIntent().getIntExtra("position",0);
        initTitle();
        initVP();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initVP() {
         getFragments();
        mViewpager.setOffscreenPageLimit(tabListBlq.length);
        mViewpager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return tabListBlq.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return tabListBlq[position];
            }
        });

        mTabTribe.setupWithViewPager(mViewpager);
        mViewpager.setCurrentItem(position);
    }
    List<MineYhqFragment> fragments = new ArrayList<>();
    private List<MineYhqFragment> getFragments() {
        for (int i = 0; i < tabListBlq.length; i++) {
            MineYhqFragment fragment = new MineYhqFragment();
            Bundle bundle = new Bundle();
            int type = 0;//：1，未使用,；2，已使用；3,过期
            switch (i){
                case 0:
                    type= 2;
                    break;
                case 1:
                    type= 1;
                    break;
                case 2:
                    type= 3;
                    break;
                    default:
            }
            bundle.putInt("type", type);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        return fragments;
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
