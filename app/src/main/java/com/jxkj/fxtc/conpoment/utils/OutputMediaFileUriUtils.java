package com.jxkj.fxtc.conpoment.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import androidx.core.content.FileProvider;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OutputMediaFileUriUtils {

    private static File mediafile = null;

    public static Uri getOutputMediaFileUri(Context context) {

        String cameraPath;
        try {
            File mediaStorageDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    return null;
                }
            }
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String imageFileName = "/xjd_" + timeStamp;
            mediafile = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES)
                    + imageFileName + ".jpg");
            mediafile.getParentFile().mkdirs();
            cameraPath = mediafile.getAbsolutePath();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {// sdk >= 24  android7.0以上
            Uri uri = FileProvider.getUriForFile(context,
                    "com.jxkj.fxtc",//与清单文件中android:authorities的值保持一致
                    mediafile);//FileProvider方式或者ContentProvider。也可使用VmPolicy方式
            return uri;

        } else {
            return Uri.fromFile(mediafile);//或者 Uri.isPaise("file://"+file.toString()

        }
    }
}
