package com.jxkj.fxtc.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.SimpleImmersionFragment;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.conpoment.utils.SharedUtils;
import com.jxkj.fxtc.conpoment.utils.StringUtil;
import com.jxkj.fxtc.conpoment.widget.LoadDialog;
import com.luck.picture.lib.permissions.RxPermissions;

import butterknife.ButterKnife;

/**
 * author： TongGuHermit
 * created on： 2018/12/27
 */

public abstract class BaseFragment extends SimpleImmersionFragment {

    protected View mRootView;
    protected RxPermissions mRxPermissions;
    protected Gson mGson;
    private LoadDialog dialog;
    protected int pageSize = 10;//
    protected Bundle savedInstanceState;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getContentView(), null);
        this.savedInstanceState = savedInstanceState;
        ButterKnife.bind(this, mRootView);
        ImmersionBar.with(this).statusBarDarkFont(true).titleBar(R.id.rl_actionbar).fitsSystemWindows(true).init();
        //解决fragment点击事件穿透问题
        mRootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        mGson = new Gson();
        initViews();
        return mRootView;
    }

    /**
     * 显示网络加载弹窗
     *
     */
    public void show(Context context, String tips) {
        dialog = new LoadDialog(getActivity(), tips);
        if (!((Activity) context).isFinishing()) {
            dialog.show();
        }
    }

    public void show(Context context) {
        if(dialog==null){
            dialog = new LoadDialog(context, "");
        }

        if (!((Activity) context).isFinishing()) {
            dialog.show();
        }
    }

    public void dismiss() {
        if (dialog != null)
            dialog.dismiss();
    }

    protected abstract int getContentView();

    protected abstract void initViews();

    public void ifNotLoginTurnToLogin(int status) {
        if (status == 101) {
            StringUtil.loginNo(getActivity());
            SharedUtils.singleton().clear();
            return;
        }
    }

    public boolean isDataInfoSucceed(Result result){
        if(result.getStatus()==0){
            return true;
        }
        ToastUtils.showShort(result.getError());
        return false;
    }

}
