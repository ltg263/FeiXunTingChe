package com.jxkj.fxtc.conpoment.utils;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;


import com.jxkj.fxtc.app.ConstValues;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * 作者： litongge
 * 时间：  2018/5/14 11:29
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class PicUtil {

    public static int CODE_SELECT_PIC_MULTIPLE = 5560;
    public static int CODE_SELECT_PIC = 5561;
    public static int CODE_CAMERA_PIC = 5562;
    public static int CODE_CROP_PIC = 5563;
    public static String filePath =  "";
    public static int CODE_CURRENT = 0;
    public static int CODE_REQUEST_TAG = 0;
    public static Uri uritempFile;

    /**
     * 开启单选图片相册
     * @param context
     */
    public static void openPhoto(Activity context){
        openPhoto(context,CODE_SELECT_PIC);
    }
    /**
     * 开启单选图片相册
     * @param context
     */
    public static void openPhoto(Activity context, int reqCode){
        CODE_CURRENT = reqCode;
        CODE_REQUEST_TAG = CODE_SELECT_PIC;
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        context.startActivityForResult(intent, reqCode);
    }

    public static Object onActivityResult(Activity context, int requestCode, int resultCode, Intent data){
        if (resultCode== Activity.RESULT_OK){
            if (CODE_REQUEST_TAG == CODE_SELECT_PIC && requestCode == CODE_CURRENT && data != null) {
                filePath = UriUtils.getImageAbsolutePath(context, data.getData());
                return  filePath;
            } else if (CODE_REQUEST_TAG == CODE_CAMERA_PIC && requestCode == CODE_CURRENT) {
                return filePath;
            } else if (CODE_REQUEST_TAG == CODE_SELECT_PIC_MULTIPLE && requestCode == CODE_CURRENT && data != null) {
                return data.getStringArrayListExtra("dataList");
            } else if (CODE_REQUEST_TAG == CODE_CROP_PIC && requestCode == CODE_CURRENT) {
                //                startPhotoZoom(Uri.fromFile(new File(filePath)),context);
                return filePath;
            }
        }
        return null;
    }
    /**
     * 打开照相
     * @param context
     * @return
     */
    public static void openCamera(Activity context) {
        openCamera(context,CODE_CAMERA_PIC);
    }
    /**
     * 打开照相
     * @param context
     * @return
     */
    public static void openCamera(final Activity context, final int reqCode) {
        if(!isEnvironment(context)){
            return;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            PermissionsManager.get().checkPermissions(context, Manifest.permission.CAMERA, new PermissionsManager.CheckCallBack() {
                @Override
                public void onSuccess(String permission) {
                    takeCamera(context,reqCode);
                }

                @Override
                public void onError(String permission) {

                }
            });
        } else {
            takeCamera(context,reqCode);
        }
    }
    private static String takeCamera(Activity context, int reqCode) {
        CODE_CURRENT = reqCode;
        CODE_REQUEST_TAG = CODE_CAMERA_PIC;
        ContentValues values = new ContentValues();
        long time = new Date(System.currentTimeMillis()).getTime();
        values.put(MediaStore.Images.Media.TITLE, new Date(System.currentTimeMillis()).getTime());
        File photoFile = new File(getSDCardPath() + File.separator + "DCIM"
                + File.separator + time + ".jpg");
        filePath = photoFile.getPath();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
        context.startActivityForResult(intent, reqCode);
        return filePath;
    }
    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
   public static void startPhotoZoom(Uri uri, Activity mActivity, boolean isShear) {
       File file = new  File(ConstValues.FILE_DIRECTORY_IMG);
       TextUtil.logOut("file:"+file.exists());
       if(!file.exists()){
           file.mkdirs();
       }
       TextUtil.logOut("file:"+file.exists());
       Intent intent = new Intent("com.android.camera.action.CROP");
       intent.setDataAndType(uri, "image/*");
       intent.putExtra("crop", "true");
       if(isShear){
           // aspectX aspectY 是宽高的比例
           intent.putExtra("aspectX", 1);
           intent.putExtra("aspectY", 1);
           // outputX outputY 是裁剪图片宽高
           intent.putExtra("outputX", 100);
           intent.putExtra("outputY", 100);
       }else{
       }
       // 设置裁剪
       intent.putExtra("return-data", true);
       /**
        * 此方法返回的图片只能是小图片（sumsang测试为高宽160px的图片）
        * 故只保存图片Uri，调用时将Uri转换为Bitmap，此方法还可解决miui系统不能return data的问题
        */

       //裁剪后的图片Uri路径，uritempFile为Uri类变量
       String path = "file://" + "/" +  ConstValues.FILE_DIRECTORY_IMG + "/" + System.currentTimeMillis()+"gt.png";
       uritempFile =Uri.parse(path);
       intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
       mActivity.startActivityForResult(intent, PicUtil.CODE_CROP_PIC);
   }


    /**
     * 获取sd卡
     *
     * @return
     */
    public static String getSDCardPath() {
        String cmd = "cat /proc/mounts";
        Runtime run = Runtime.getRuntime();// 返回与当前 Java 应用程序相关的运行时对象
        BufferedInputStream in = null;
        BufferedReader inBr = null;
        try {
            Process p = run.exec(cmd);// 启动另一个进程来执行命令
            in = new BufferedInputStream(p.getInputStream());
            inBr = new BufferedReader(new InputStreamReader(in));

            String lineStr;
            while ((lineStr = inBr.readLine()) != null) {
                // 获得命令执行后在控制台的输出信息
                if (lineStr.contains("sdcard")
                        && lineStr.contains(".android_secure")) {
                    String[] strArray = lineStr.split(" ");
                    if (strArray != null && strArray.length >= 5) {
                        String result = strArray[1].replace("/.android_secure",
                                "");
                        return result;
                    }
                }
                // 检查命令是否执行失败。
                if (p.waitFor() != 0 && p.exitValue() == 1) {
                    // p.exitValue()==0表示正常结束，1：非正常结束
                    Log.e("getSDCardPath", "命令执行失败!");
                }
            }
        } catch (Exception e) {
            Log.e("getSDCardPath", e.toString());
            return Environment.getExternalStorageDirectory().getPath();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (inBr != null) {
                    inBr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Environment.getExternalStorageDirectory().getPath();
    }

    // 判断时候有SD卡
    public static boolean isEnvironment(Context context) {

        String sdState = Environment.getExternalStorageState();
        if (!sdState.equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(context, "请检查SD卡是否安装", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }
    public static String getPrintSize(long size) {
        //如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }
        //如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        //因为还没有到达要使用另一个单位的时候
        //接下去以此类推
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            //因为如果以MB为单位的话，要保留最后1位小数，
            //因此，把此数乘以100之后再取余
            size = size * 100;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "MB";
        } else {
            //否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "GB";
        }
    }

}
