package com.jxkj.fxtc.conpoment.utils;

import android.content.Context;

import java.io.File;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;


/**
 * Created by Administrator on 2017/9/27.
 */

public class CompressUtil {

    public void compressImgWithFile(Context context, File sourceFile, int size, String targetFile, final compressListener listener){
        Luban.with(context)
                .load(sourceFile)
                .ignoreBy(size)
                .setTargetDir(targetFile)
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(File file) {
                        listener.onSuccess(file);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e);
                    }
                }).launch();
    }

    public interface compressListener{
        void onSuccess(File file);
        void onError(Throwable e);
    }
}
