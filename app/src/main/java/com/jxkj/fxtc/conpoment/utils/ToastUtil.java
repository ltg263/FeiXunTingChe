package com.jxkj.fxtc.conpoment.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * author : LiuJie
 * date   : 2020/5/2915:01
 */
public class ToastUtil {
    private static ToastUtil instance;
    private static Toast mToast;

    public static ToastUtil getInstance(Context mContext) {
        if (null == instance) {
            synchronized (ToastUtil.class) {
                if (null == instance) {
                    instance = new ToastUtil(mContext);
                }
            }
        }
        return instance;
    }

    private ToastUtil(Context mContext) {
        if (null == mContext) {
            throw new NullPointerException("此类没有进行初始化");
        }
        if (null == mToast) {
            mToast = Toast.makeText(mContext, null, Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER, 0, 0);
        }
    }

    public static void showShortToast(final Context context, final String message){
        if (mToast == null){
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER, 0, 0);
        }else{
            mToast.setText(message);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
    public static void showShortServerToast(final Context context){
        if (mToast == null){
            mToast = Toast.makeText(context, "连接超时，请求失败", Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER, 0, 0);
        }else{
            mToast.setText("连接超时，请求失败");
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
    public static void showShortNoDataToast(final Context context){
        if (mToast == null){
            mToast = Toast.makeText(context, "没有更多数据了", Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER, 0, 0);
        }else{
            mToast.setText("没有更多数据了");
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
    public static void showLongToast(final Context context, final int messageResId){
        if (mToast == null){
            mToast = Toast.makeText(context, messageResId, Toast.LENGTH_LONG);
        }else{
            mToast.setText(messageResId);
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        mToast.show();
    }

    public static void showLongStrToast(final Context context, final String message){
        if (mToast == null){
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        }else{
            mToast.setText(message);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void cancleToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
