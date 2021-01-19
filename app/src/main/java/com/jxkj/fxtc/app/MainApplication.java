package com.jxkj.fxtc.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.jxkj.fxtc.R;
import com.jxkj.fxtc.conpoment.utils.ActivityManager;
import com.jxkj.fxtc.conpoment.utils.SpUtil;
import com.jxkj.fxtc.conpoment.utils.ToastUtil;
import com.jxkj.fxtc.view.deme.CrashHandler;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.util.Stack;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2017/8/28.
 */

public class MainApplication extends Application {
    /**
     * 上下文对象
     */
    private static MainApplication mContext;
    private static Stack<Activity> activityStack;
    private SpUtil spUtil;
    private ToastUtil toastUtil;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        //sp初始化
        spUtil = SpUtil.getInstance(mContext);

        toastUtil = ToastUtil.getInstance(mContext);
        ActivityManager.init(this);

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        initUMShare();
    }
    private void initUMShare() {
        UMShareAPI.get(this);
        PlatformConfig.setWeixin(ConstValues.WX_APP_ID,"c706d670856a9afc92ee1805f71bd2c8");
        CrashHandler.getInstance().init(getApplicationContext());
    }

    /**
     * 全局上下文
     */
    public static MainApplication getContext() {
        return mContext;
    }

    /**
     * add Activity 添加Activity到栈
     */
    public static void addActivity(Activity activity){
        if(activityStack ==null){
            activityStack =new Stack<>();
        }
        if(!activityStack.contains(activity)){
            activityStack.add(activity);
        }
    }

    /**
     * 退出应用程序
     */
    public void AppExit() {
        try {
            finishAllActivity();
        } catch (Exception e) {
        }
    }
    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.white, R.color.color_666666);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }
}
