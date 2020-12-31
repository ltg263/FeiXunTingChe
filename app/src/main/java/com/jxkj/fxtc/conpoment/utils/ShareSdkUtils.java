package com.jxkj.fxtc.conpoment.utils;

import android.app.Activity;
import android.util.Log;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

public class ShareSdkUtils {


    public static void shareSDKImg(Activity mContext, String url, LoginInterface loginInterface){
        new ShareAction(mContext).withMedia(new UMImage(mContext,url))
                .setDisplayList(SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                    }
                    @Override
                    public void onResult(SHARE_MEDIA platform) {
                        Log.i("minfo","分享成功");
                        loginInterface.succeed(null);
                    }

                    @Override
                    public void onError(SHARE_MEDIA platform, Throwable t) {
                        Log.i("minfo","分享失败"+t.getMessage());
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA platform) {
                        Log.i("minfo","分享取消");
                    }
                }).open();
    }
    public interface LoginInterface{
        void succeed(String path);
//        void failure();
    }

}
