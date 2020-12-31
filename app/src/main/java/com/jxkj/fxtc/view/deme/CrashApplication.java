package com.jxkj.fxtc.view.deme;

import android.app.Application;

/**
 * @Author:Created by LC125
 * @Description:应用程序入口类
 * @Time:Created on 2020/11/4 14:01
 */
public class CrashApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        super.onCreate();
        this.start();
    }



    /**
     * @应用程序启动类，在该方法中加载应用程序配置
     */
    private void start()
    {
        CrashHandler.getInstance().init(getApplicationContext());
    }
}
