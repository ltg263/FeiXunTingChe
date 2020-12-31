package com.jxkj.fxtc.conpoment.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import android.widget.ImageView;

import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lxj.xpopup.interfaces.XPopupImageLoader;

import java.io.File;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public abstract class ImageLoader implements XPopupImageLoader {

    @Override
    public void loadImage(int position, @NonNull Object uri, @NonNull final ImageView imageView) {

        int w = 1000* ScreenUtils.getScreenWidth()/ ScreenUtils.getScreenHeight();
        Glide.with(imageView).load(uri).apply(
                new RequestOptions()
//                        .override(w,w)
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))

                .into(imageView);

//        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE).dontAnimate();
//        Glide.with(imageView.getContext()).load(uri).apply(options).into(new SimpleTarget<Drawable>() {
//            @Override
//            public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
//                if(drawable!=null){
//                    imageView.setImageDrawable(drawable);
//                }
//            }
//        });

    }

    //必须实现这个方法，返回uri对应的缓存文件，可参照下面的实现，内部保存图片会用到。如果你不需要保存图片这个功能，可以返回null。
    @Override
    public File getImageFile(@NonNull Context context, @NonNull Object uri) {
        try {
            return Glide.with(context).downloadOnly().load(uri).submit().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract void displayImage(Context context, Object path, ImageView imageView);

    /**
     * uri转String path
     * @param context
     * @param uri
     * @return
     */
    public static String uri2StringPath(final Context context, final Uri uri ) {
        if (null == uri) {
            return null;
        }
        final String scheme = uri.getScheme();
        String path = null;
        if (scheme == null) {
            path = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            path = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        path = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return path;
    }
    /**
     * 处理Uri类型
     *
     * @param mContext
     * @param uri
     * @param compressListener
     */
    public static void compressImg(Context mContext, Uri uri, final OnMyCompressListener compressListener) {
        Luban.with(mContext).load(new File(uri2StringPath(mContext, uri)))
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(File file) {
                        compressListener.onSuccess(file);
                    }

                    @Override
                    public void onError(Throwable e) {
                        compressListener.onError(e);
                    }
                })
                .launch();
    }

    public interface OnMyCompressListener {
        /**
         * 压缩成功
         *
         * @param file
         */
        void onSuccess(File file);

        /**
         * 压缩失败
         *
         * @param e
         */
        void onError(Throwable e);
    }

//    /**
//     * 压缩图片
//     *
//     * @param uri
//     */
//    private void compressImg(Uri uri) {
//        compressImg(MyAccountInfoActivity.this, uri, new LuBanUtils.OnMyCompressListener() {
//                    @Override
//                    public void onSuccess(final File file) {
//                        imgPath = file.getPath();
//                        LogUtils.e("压缩后的图片路径---" + imgPath);
//                        setImg(ivHeader);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Toast.makeText(MyAccountInfoActivity.this, "图片压缩失败, 请稍后重试", Toast.LENGTH_SHORT).show();
//                    }
//                }
//        );
//    }

}