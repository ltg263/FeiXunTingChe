package com.jxkj.fxtc.conpoment.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.os.StrictMode;
import android.provider.Settings;
import androidx.annotation.NonNull;


import com.jxkj.fxtc.R;
import com.jxkj.fxtc.app.MainApplication;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 6.0 M权限认证管理
 * Created by Liyeyu on 2016/6/2.
 */
public class PermissionsManager extends MainApplication {
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;
    //管理危险权限集合-申请一组
    private List<String> mPermmisions = new ArrayList<>();
    private CheckCallBack mCheckCallBack;
    private static PermissionsManager mManager;

    private PermissionsManager() {
    }

    public static PermissionsManager get() {
        if (mManager == null) {
            mManager = new PermissionsManager();
        }
        return mManager;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void checkPermissions(Activity mActivity, String permission, CheckCallBack mCheckCallBack) {
        this.mCheckCallBack = mCheckCallBack;
        if (Build.VERSION.SDK_INT >= 23) {
            if (addPermission(mActivity,permission)) {
                if (!mActivity.shouldShowRequestPermissionRationale(permission)) {
                    mActivity.requestPermissions(new String[]{permission}, REQUEST_CODE_ASK_PERMISSIONS);
                } else {
                    //已禁止过，弹窗提示
                    showPermission(mActivity,MainApplication.getContext().getString(R.string.permission_ask_now, permission), permission);
                }
            } else {
                if (mCheckCallBack != null) {
                    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                    StrictMode.setVmPolicy(builder.build());
                    builder.detectFileUriExposure();
                    mCheckCallBack.onSuccess(permission);
                }
            }
        } else {
            if (mCheckCallBack != null) {
                mCheckCallBack.onSuccess(permission);
            }
        }

        //检测用户是否已禁止过该权限
    }

    /**
     * 处理结果
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_ASK_PERMISSIONS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (mCheckCallBack != null) {
                    mCheckCallBack.onSuccess(permissions[0]);
                }
            } else {
//                ToastUtils.getInstance().show(MainApplication.getContext(), MainApplication.getContext().getString(R.string.permission_ask_error, (permissions[0])));
                if (mCheckCallBack != null) {
                    mCheckCallBack.onError(permissions[0]);
                }
            }
        }
    }


    /**
     * 检查权限是否被添加过
     *
     * @param permission
     */
    @TargetApi(Build.VERSION_CODES.M)
    private boolean addPermission(Activity mActivity, String permission) {
        int hasPermission = mActivity.checkSelfPermission(permission);
        return hasPermission != PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 显示权限被禁止弹窗
     *
     * @param msg
     * @param permission
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void showPermission(final Activity mActivity, String msg, final String permission) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(mActivity);
        mBuilder.setMessage(msg);
        mBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mActivity.requestPermissions(new String[]{permission}, REQUEST_CODE_ASK_PERMISSIONS);
            }
        });
        mBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        mBuilder.create().show();
    }

    /**
     * 检查Notification是否被禁止
     * 不能再service中检查，因为进程uid不同
     * @param context
     * @return
     */
    public boolean checkNotificationPermissions(Context context) {
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        try {
            Field opPostNotification = appOpsManager.getClass().getField("OP_POST_NOTIFICATION");
            Method checkOpNoThrow = appOpsManager.getClass().getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String.class);
            boolean b = (int) checkOpNoThrow.invoke(appOpsManager
                    , opPostNotification.getInt(appOpsManager)
                    , Process.myUid(), context.getPackageName()) == AppOpsManager.MODE_ALLOWED;
            return b;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 打开应用详情设置界面
     * @param context
     */
    public void openAppSetting(Context context) {
        Intent i = new Intent(Settings.ACTION_APPLICATION_SETTINGS);
        i.setPackage(context.getPackageName());
        context.startActivity(i);
    }

    public interface CheckCallBack {
        void onSuccess(String permission);

        void onError(String permission);
    }
}
