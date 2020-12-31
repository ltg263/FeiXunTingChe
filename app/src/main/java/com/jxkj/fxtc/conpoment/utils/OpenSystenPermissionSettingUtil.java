package com.jxkj.fxtc.conpoment.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;

import java.util.List;

/**
 * 作者：wzh
 * 创建时间：2018/5/9 15:10
 * JinWanChiJi
 */

public class OpenSystenPermissionSettingUtil {

    public static void settingPermissionActivity(Activity activity) {
        //判断是否为小米系统
        if (TextUtils.equals(BrandUtils.getSystemInfo().getOs(), BrandUtils.SYS_MIUI)) {
            Intent miuiIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
            miuiIntent.putExtra("extra_pkgname", activity.getPackageName());
            //检测是否有能接受该Intent的Activity存在
            List<ResolveInfo> resolveInfos = activity.getPackageManager().queryIntentActivities(miuiIntent, PackageManager.MATCH_DEFAULT_ONLY);
            if (resolveInfos.size() > 0) {
                activity.startActivityForResult(miuiIntent, 0);
                return;
            }
        }
        //如果不是小米系统 则打开Android系统的应用设置页
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivityForResult(intent, 0);
    }
}
