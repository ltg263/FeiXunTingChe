package com.jxkj.fxtc.conpoment.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;


/**
 * Created by ShiXL on 2018/1/31.
 */

public class GlideImgLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        /**
         注意：
         1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
         2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
         传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
         切记不要胡乱强转！
         */
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //Glide 加载图片简单用法
        Glide.with(context).load(path).into(imageView);

    }

    /**
     * Glide特点
     * 使用简单
     * 可配置度高，自适应程度高
     * 支持常见图片格式 Jpg png gif webp
     * 支持多种数据源  网络、本地、资源、Assets 等
     * 高效缓存策略    支持Memory和Disk图片缓存 默认Bitmap格式采用RGB_565内存使用至少减少一半
     * 生命周期集成   根据Activity/Fragment生命周期自动管理请求
     * 高效处理Bitmap  使用Bitmap Pool使Bitmap复用，主动调用recycle回收需要回收的Bitmap，减小系统回收压力
     * 这里默认支持Context，Glide支持Context,Activity,Fragment，FragmentActivity
     */


    //默认加载
    @SuppressLint("CheckResult")
    public static void loadImageAndDefault(Context mContext, String path, ImageView mImageView) {
        RequestOptions options = new RequestOptions();
        //options.error(R.mipmap.ic_launcher_round);
        Glide.with(mContext).load(path).apply(options).into(mImageView);

    }

    //默认加载
    @SuppressLint("CheckResult")
    public static void loadImageAndHeadDefault(Context mContext, String path, ImageView mImageView) {
        RequestOptions options = new RequestOptions();
        //options.error(R.mipmap.ic_launcher_round);
        Glide.with(mContext).load(path).apply(options).into(mImageView);

    }

//    //默认加载
//    public static void loadImage(Context mContext, String path, ImageView mImageView) {
//        Glide.with(mContext).load(path.contains("http")?path:RetrofitManager.IMAGE_URL+path).into(mImageView);
//    }

    //默认加载
    public static void loadImage(Context mContext, int path, ImageView mImageView) {
        Glide.with(mContext).load(path).into(mImageView);
    }

    //加载圆角
    public static void loadImageViewRadiusNoCenter(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).apply(new RequestOptions().transforms(new RoundedCorners(20))).into(mImageView);
    }

    //加载圆角
    public static void loadImageViewRadius(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).apply(new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(20))).into(mImageView);
    }

    //加载圆角
    public static void loadImageViewRadius(Context mContext, String path, int r, ImageView mImageView) {
        Glide.with(mContext).load(path).apply(new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(r))).into(mImageView);
    }

    //加载圆角
    public static void loadImageViewRadius(Context mContext, int path, ImageView mImageView) {
        Glide.with(mContext).load(path).apply(new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(20))).into(mImageView);
    }

    //加载圆角
    public static void loadImageViewRadius(Context mContext, int path, int r, ImageView mImageView) {
        Glide.with(mContext).load(path).apply(new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(r))).into(mImageView);
    }

    //加载圆形图片
    public static void loadImageViewWithCirclr(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).thumbnail(0.1f).apply(bitmapTransform(new CropCircleTransformation())).into(mImageView);
    }

    //加载高斯模糊图片
    public static void loadImageViewWithBlur(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path)
                .apply(bitmapTransform(new BlurTransformation(25, 3)))
                .into(mImageView);
    }

    public static void loadImageViewWithBlur(Context mContext, int path, ImageView mImageView) {
        Glide.with(mContext).load(path)
                .apply(bitmapTransform(new BlurTransformation(25, 3)))
                .into(mImageView);
    }

//    public static void loadImageViewWithBlurWithE(Context mContext, int path, int errorPath, ImageView mImageView) {
//        RequestOptions options = new RequestOptions()
//                .centerCrop()
//                .placeholder(errorPath)
//                .dontAnimate()
//                .error(errorPath)
//                .priority(Priority.HIGH);
//        MultiTransformation multi = new MultiTransformation(
//                new BlurTransformation(20, 2),
//                new ColorFilterTransformation(mContext.getResources().getColor(R.color.trans_black)));
//        Glide.with(mContext).load(path)
//                .apply(bitmapTransform(multi))
////                .apply(options)
//                .into(mImageView);
//    }
//
//    public static void loadImageViewWithBlurWithE(Context mContext, String path, int errorPath, ImageView mImageView) {
//        RequestOptions options = new RequestOptions()
//                .centerCrop()
//                .placeholder(errorPath)
//                .dontAnimate()
//                .error(errorPath)
//                .priority(Priority.HIGH);
//        MultiTransformation multi = new MultiTransformation(
//                new BlurTransformation(20, 2),
    //                new ColorFilterTransformation(mContext.getResources().getColor(R.color.trans_black)));
//        Glide.with(mContext).load(path)
//                .apply(bitmapTransform(multi))
////                .apply(options)
//                .into(mImageView);
//    }


    //清理磁盘缓存
    public static void GuideClearDiskCache(Context mContext) {
        //理磁盘缓存 需要在子线程中执行
        Glide.get(mContext).clearDiskCache();
    }

    //清理内存缓存
    public static void GuideClearMemory(Context mContext) {
        //清理内存缓存  可以在UI主线程中进行
        Glide.get(mContext).clearMemory();
    }

}