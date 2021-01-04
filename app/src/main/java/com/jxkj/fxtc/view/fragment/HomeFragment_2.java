package com.jxkj.fxtc.view.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.jxkj.fxtc.R;
import com.jxkj.fxtc.base.BaseFragment;
import com.jxkj.fxtc.conpoment.drawerView.DrawerLayout;
import com.jxkj.fxtc.conpoment.widget.MyRecyclerView;
import com.jxkj.fxtc.view.adapter.BookingSpaceAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 1000D 订单管理
 */
public class HomeFragment_2 extends BaseFragment {


    @BindView(R.id.drawerHandle)
    ImageView mDrawerHandle;
    @BindView(R.id.drawerContent)
    LinearLayout mDrawerContent;
    @BindView(R.id.drawer2)
    RelativeLayout mDrawer2;
    @BindView(R.id.dial_drawer)
    DrawerLayout mDialDrawer;
    @BindView(R.id.rv_list)
    MyRecyclerView mRvList;
    private BookingSpaceAdapter mBookingSpaceAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_2;
    }

    @Override
    protected void initViews() {
        List<String> list  = new ArrayList<>();
        for(int i = 0;i<11;i++){
            list.add("");
        }

        mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvList.setHasFixedSize(true);
        mRvList.setVisibility(View.VISIBLE);
        mBookingSpaceAdapter = new BookingSpaceAdapter(list);
        mRvList.setAdapter(mBookingSpaceAdapter);

        mDialDrawer.setInitialState(DrawerLayout.State.Close); //set drawer initial state: open or close
        mDialDrawer.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void drawerOpened() {

            }

            @Override
            public void drawerClosed() {

            }
        });

    }

    @Override
    public void initImmersionBar() {

    }

    public static HomeFragment_2 newInstance() {
        HomeFragment_2 homeFragment = new HomeFragment_2();
        return homeFragment;
    }


}