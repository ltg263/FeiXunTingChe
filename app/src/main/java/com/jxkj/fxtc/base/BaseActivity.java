package com.jxkj.fxtc.base;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.app.ConstValues;
import com.jxkj.fxtc.conpoment.utils.ActivityCollector;
import com.jxkj.fxtc.conpoment.utils.SharedUtils;
import com.jxkj.fxtc.conpoment.utils.ToastUtil;
import com.jxkj.fxtc.conpoment.widget.LoadDialog;
import com.luck.picture.lib.permissions.RxPermissions;

import java.io.File;
import java.util.Stack;

import butterknife.ButterKnife;


/**
 * author： TongGuHermit
 * created on： 2018/12/27
 */
public abstract class BaseActivity extends AppCompatActivity{

    protected RxPermissions mRxPermissions;
    protected Stack<Fragment> mFragStack;
    protected int statusBarHeight;
    private LoadDialog dialog;
    //protected SwipeBackLayout mSwipeBackLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setTheme(R.style.base_blue_style);//修改主题
        setContentView(getContentView());
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarDarkFont(true).titleBar(R.id.rl_actionbar).fitsSystemWindows(true).init();
        statusBarHeight = BarUtils.getStatusBarHeight();
        mRxPermissions = new RxPermissions(this);
        requestPermissions();
//        setSwipeBackEnable(true);
//        mSwipeBackLayout = getSwipeBackLayout();
//        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
//        mSwipeBackLayout.setEdgeSize(400);
        initViews();
        ActivityCollector.getAppManager().addActivity(this);

    }

    protected abstract int getContentView();
    protected abstract void initViews();

    private void requestPermissions(){
        mRxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE)
                .subscribe(new io.reactivex.functions.Consumer<Boolean>() {

                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (!aBoolean){
                            Toast.makeText(BaseActivity.this,"为保证应用正常运行,请在权限管理开启必要权限!", Toast.LENGTH_SHORT).show();
                        }else {
                            File file = new File(Environment.getExternalStorageDirectory().getPath()+ ConstValues.FILE_ROOT_DIRECTORY);
                            if (!file.exists()){
                                file.mkdirs();
                            }
                        }
                    }
                });
    }

    public boolean ifNotLoginTurnToLogin(){
//        if (!SPUtils.getInstance().getBoolean(ConstValues.ISLOGIN)){
//            startAcvityWithNoData(this, LoginActivity.class);
//            return false;
//        }
        return true;
    }
    public void ifNotLoginTurnToLogin(int status){
        if (status==101){
            ToastUtil.showLongStrToast(this,"登录过期，请重新登录");
            SharedUtils.singleton().clear();
            return ;
        }
    }
    public void startAcvityWithNoData(Context context, Class<?> cls){
        Intent intent = new Intent(context,cls);
        startActivity(intent);
    }

    public void hideFragment(Fragment fragment){
        if (fragment!=null & mFragStack !=null){
            getSupportFragmentManager().beginTransaction().hide(fragment).commitAllowingStateLoss();
        }
    }


    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }

    public void showFragment(Fragment fragment, boolean hidePrevius){
        if (fragment!=null & mFragStack !=null){
            for (Fragment fragment1:mFragStack){
                if (fragment1 == fragment){
                    getSupportFragmentManager().beginTransaction().show(fragment1).commitAllowingStateLoss();
                }else {
                    if (hidePrevius){
                        getSupportFragmentManager().beginTransaction().hide(fragment1).commitAllowingStateLoss();
                    }
                }
            }
        }
    }

    public int getStatusBarHeight(){
        return statusBarHeight;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    public boolean isDataInfoSucceed(Result result){
        if(result.getStatus()==0){
            return true;
        }
        ToastUtils.showShort(result.getError());
        return false;
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 显示网络加载弹窗
     */
    public void show(Context context, String tips) {
        dialog = new LoadDialog(this, tips);
        if (!((Activity) context).isFinishing()) {
            dialog.show();
        }
    }
    public void show() {
        if(dialog==null){
            dialog = new LoadDialog(this, "");
        }

        if (!isFinishing()) {
            dialog.show();
        }
    }
    public void dismiss() {
        if (dialog != null)
            dialog.dismiss();
    }
    /**
     * 获取InputMethodManager，隐藏软键盘
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
