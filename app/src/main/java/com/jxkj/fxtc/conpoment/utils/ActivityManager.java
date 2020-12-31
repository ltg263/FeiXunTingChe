package com.jxkj.fxtc.conpoment.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.List;
import java.util.Stack;

/**
 * 作者：wzh
 * 创建时间：2018/4/16 14:36
 * LemonGoleden
 * 用法：Application.onCreate 中 ActivityManager.init(this)
 */

public class ActivityManager {

    private static ActivityManager instance;

    private static Application application;

    private static Stack<Activity> activityStack;

    public static void init(Application application){
        ActivityManager.application = application;
        activityStack = new Stack<>();
        addCallback();
    }


    /**
     * 销毁指定activity
     * @param activityClass
     */
    public static void remove(Class<?> activityClass){
        for (int i=0;i<activityStack.size();i++){
            if (activityStack.get(i).getClass().equals(activityClass)){
                activityStack.get(i).finish();
                activityStack.remove(i);
            }
        }
    }

    /**
     * 销毁指定activity list
     * @param activityClassList
     */
    public static void remove(List<Class<?>> activityClassList){
        for (Class<?> listItem : activityClassList){
            remove(listItem);
        }
    }

    /**
     * 获取当前所有未销毁的activity stack
     * @return
     */
    public static Stack getActivityStack(){
        if (activityStack == null){
            activityStack = new Stack<>();
        }
        return activityStack;
    }

    private static void addCallback(){
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                if (activityStack == null){
                    activityStack = new Stack<>();
                }
                activityStack.push(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                if (activityStack.search(activity)>0){
                    activityStack.remove(activity);
                }
            }
        });
    }
}
