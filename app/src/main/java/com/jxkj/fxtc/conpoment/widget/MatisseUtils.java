package com.jxkj.fxtc.conpoment.widget;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.util.Log;

import com.jxkj.fxtc.R;
import com.jxkj.fxtc.app.ConstValues;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;

import pub.devrel.easypermissions.EasyPermissions;

public class MatisseUtils {
    public static final String PHONE_NAME = "AIMEIXIN";
    public static final int PERMISSION_CAMERA = 110;
    public static final int REQUEST_CODE_XC = 1;
    public static final int REQUEST_CODE_AD = 2;
    public static final int REQUEST_CROP_PHOTO = 5;
    public static String[] params = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};


    public static void selectImg(Activity mActivity, int requestCode, int selectedNum) {
        Matisse.from(mActivity)
                .choose(MimeType.ofImage(), false)
                .countable(false)
                .capture(true)
                .captureStrategy(
                        new CaptureStrategy(true, "com.jxkj.aimeixin.fileprovider", "test"))
                .maxSelectable(9)
                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(mActivity.getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .setOnSelectedListener((uriList, pathList) -> {
                    Log.e("onSelected", "onSelected: pathList=" + pathList);
                })
                .showSingleMediaType(true)
                .originalEnable(true)
                .maxOriginalSize(10)
                .autoHideToolbarOnSingleTap(true)
                .setOnCheckedListener(isChecked -> {
                    Log.e("isChecked", "onCheck: isChecked=" + isChecked);
                })
                .maxSelectable(selectedNum)
                .forResult(requestCode);

    }
    // 启动手机相机拍摄照片作为头像
    public static void choseHeadImageFromCameraCapture(Activity mContext,int requestCode) {
        //6.0以上动态获取权限
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //申请权限，REQUEST_TAKE_PHOTO_PERMISSION是自定义的常量
            ActivityCompat.requestPermissions(mContext,new String[]{Manifest.permission.CAMERA},1);

        } else {
            Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            // 判断存储卡是否可用，存储照片文件
            if (hasSDCard()) {
                intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory(), PHONE_NAME)));
            }
            mContext.startActivityForResult(intentFromCapture, requestCode);
        }

    }
    public static boolean hasSDCard() {
        return Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState());
    }

    public static void gotoSelectPhoto(Activity mConext,int selectedNum) {
        if (EasyPermissions.hasPermissions(mConext, params)) {
            //具备权限 直接进行操作
            MatisseUtils.selectImg(mConext, REQUEST_CODE_XC, selectedNum);
        } else {
            //权限拒绝 申请权限
            EasyPermissions.requestPermissions(mConext, "为了您更好使用本应用，请允许应用获取以下权限", PERMISSION_CAMERA, params);
        }
    }

    /**
     * 裁剪原始的图片
     */
    public static void cropRawPhoto(Activity mContext ,Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);
        intent.putExtra("return-data", true);

        /**
         * 此方法返回的图片只能是小图片（sumsang测试为高宽160px的图片）
         * 故只保存图片Uri，调用时将Uri转换为Bitmap，此方法还可解决miui系统不能return data的问题
         */

        //裁剪后的图片Uri路径，uritempFile为Uri类变量
        Uri uritempFile = Uri.parse("file://" + "/" + ConstValues.FILE_DIRECTORY_IMG + "/" + System.currentTimeMillis() + "aimeixin.png");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
        mContext.startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }
}
