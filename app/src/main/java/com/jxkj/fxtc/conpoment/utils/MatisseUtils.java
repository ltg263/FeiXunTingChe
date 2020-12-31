package com.jxkj.fxtc.conpoment.utils;

import android.Manifest;
import android.app.Activity;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;

import pub.devrel.easypermissions.EasyPermissions;

public class MatisseUtils {

    public static final int PERMISSION_CAMERA = 110;
    public static String[] params = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static void gotoSelectPhoto(Activity mActivity, int selectedNum,boolean isEnableCrop) {
        if (EasyPermissions.hasPermissions(mActivity, params)) {
            //具备权限 直接进行操作
            MatisseUtils.selectImg(mActivity, selectedNum,isEnableCrop);
        } else {
            //权限拒绝 申请权限
            EasyPermissions.requestPermissions(mActivity, "为了您更好使用本应用，请允许应用获取以下权限", PERMISSION_CAMERA, params);
        }
    }



    public static void selectImg(Activity mActivity,int selectedNum,boolean isEnableCrop) {
        PictureSelector.create(mActivity)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio
                .maxSelectNum(selectedNum)// 最大图片选择数量 int
                .minSelectNum(1)// 最小选择数量 int
                .imageSpanCount(3)// 每行显示个数 int
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .enableCrop(isEnableCrop)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                .isGif(false)// 是否显示gif图片 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code

    }

    public static void gotoSelectPhotoBl(Activity mActivity, int selectedNum,boolean isEnableCrop) {
        if (EasyPermissions.hasPermissions(mActivity, params)) {
            //具备权限 直接进行操作
            MatisseUtils.selectImgBl(mActivity, selectedNum,isEnableCrop);
        } else {
            //权限拒绝 申请权限
            EasyPermissions.requestPermissions(mActivity, "为了您更好使用本应用，请允许应用获取以下权限", PERMISSION_CAMERA, params);
        }
    }

    public static void selectImgBl(Activity mActivity,int selectedNum,boolean isEnableCrop) {
        PictureSelector.create(mActivity)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio
                .maxSelectNum(selectedNum)// 最大图片选择数量 int
                .minSelectNum(1)// 最小选择数量 int
                .imageSpanCount(3)// 每行显示个数 int
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .enableCrop(isEnableCrop)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .withAspectRatio(4, 3)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                .isGif(false)// 是否显示gif图片 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .forResult(PictureConfig.REQUEST_CAMERA);//结果回调onActivityResult code

    }
}
