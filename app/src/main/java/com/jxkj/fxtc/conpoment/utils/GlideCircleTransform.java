package com.jxkj.fxtc.conpoment.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.app.MainApplication;
import com.jxkj.fxtc.conpoment.widget.RoundedCornersTransform;

import java.security.MessageDigest;


public class GlideCircleTransform extends BitmapTransformation {

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return circleCrop(pool, toTransform);
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }
    private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;

        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

        Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);
        return result;
    }

    public static void glideCircleImg(String url,ImageView imageView){
        Glide.with(MainApplication.getContext()).applyDefaultRequestOptions(new RequestOptions().transform(new GlideCircleTransform())).
                load(url).into(imageView);
    }
    public static void glideCircleImg(int url,ImageView imageView){
        Glide.with(MainApplication.getContext()).applyDefaultRequestOptions(new RequestOptions().transform(new GlideCircleTransform())).
                load(url).into(imageView);
    }
    public static void glideZdCircleImg(Context mContext,String url,
                                        ImageView imageView,boolean leftTop, boolean rightTop, boolean leftBottom, boolean rightBottom){
        RoundedCornersTransform transformation = new RoundedCornersTransform(mContext, DensityUtil.dip2px(mContext, 20));
        //只是绘制左上角和右上角圆角

        transformation.setNeedCorner(leftTop, rightTop, leftBottom, rightBottom);
        RequestOptions options = new RequestOptions().placeholder(R.color.color_transparent).transform(transformation);
        Glide.with(mContext)
                .asBitmap()
                .load(url)
                .apply(options)
                .into(imageView);
    }


    public static void glideCircleImg(Context mContext,int url,ImageView imageView){
        Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().transform(new GlideCircleTransform())).
                load(url).into(imageView);
    }
    public static void glideZdCircleImg(Context mContext,int url,
                                        ImageView imageView){
        glideZdCircleImg(mContext,url,imageView,true,true,true,true);
    }
    public static void glideZdCircleImg(Context mContext,int url,
                                        ImageView imageView,boolean leftTop, boolean rightTop, boolean leftBottom, boolean rightBottom){
        RoundedCornersTransform transformation = new RoundedCornersTransform(mContext, DensityUtil.dip2px(mContext, 5));
        //只是绘制左上角和右上角圆角

        transformation.setNeedCorner(leftTop, rightTop, leftBottom, rightBottom);
        RequestOptions options = new RequestOptions().placeholder(R.color.color_transparent).transform(transformation);
        Glide.with(mContext)
                .asBitmap()
                .load(url)
                .apply(options)
                .into(imageView);
    }


}
