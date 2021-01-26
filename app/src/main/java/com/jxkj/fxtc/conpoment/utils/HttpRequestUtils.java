package com.jxkj.fxtc.conpoment.utils;

import android.content.Context;

import com.jxkj.fxtc.R;
import com.jxkj.fxtc.api.RetrofitUtil;
import com.jxkj.fxtc.base.Result;
import com.jxkj.fxtc.entity.ListApkInfo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import constant.UiType;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import listener.Md5CheckResultListener;
import listener.UpdateDownloadListener;
import model.UiConfig;
import model.UpdateConfig;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import update.UpdateAppUtils;

public class HttpRequestUtils {
    public static void uploadFiles(String filePath,UploadFileInterface fileInterface) {
        if(StringUtil.isBlank(filePath)){
            fileInterface.succeed("-1");
            return;
        }
        File file = new File(filePath);
        Map<String, RequestBody> map = new HashMap<>();
//        map.put("dirtype", toRequestBody("3"));//头像：3，申诉 ：2 ，收款码：1
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part  和后端约定好Key，这里的name是用file
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
//        MultipartBody.Part[] ba =  {MultipartBody.Part.createFormData("files", file.getName(), requestFile)};
        RetrofitUtil.getInstance().apiService()
                .submitFiles(body, map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Result result) {

                        if (result.getStatus() == 0 && result.getData()!=null &&StringUtil.isNotBlank(result.getData().toString())) {

                            fileInterface.succeed(result.getData().toString());
                        }else{
                            fileInterface.failure();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        fileInterface.failure();
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    public interface UploadFileInterface{
        void succeed(String path);
        void failure();
    }
    public static void getVersionUpdating(Context mContext,UploadFileInterface fileInterface) {
        RetrofitUtil.getInstance().apiService()
                .getVersionUpdating()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ListApkInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ListApkInfo> result) {
                        if (result.getStatus()==0) {
                            goUpdating(mContext,result.getData(),fileInterface);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    public static void goUpdating(Context mContext, ListApkInfo data, UploadFileInterface fileInterface) {
        if(data.getApkName().equals(BaseUtils.getVersionName(mContext))){
            fileInterface.failure();
            return;
        }
        UpdateAppUtils.init(mContext);
        UpdateConfig updateConfig = new UpdateConfig();
        updateConfig.setCheckWifi(true);
        updateConfig.setNeedCheckMd5(false);
        updateConfig.setNotifyImgRes(R.drawable.ic_icon_img);
        UiConfig uiConfig = new UiConfig();
        uiConfig.setUiType(UiType.PLENTIFUL);
        uiConfig.setUpdateLogoImgRes(R.drawable.ic_icon_img);
        uiConfig.setUpdateBtnBgRes(R.drawable.btn_shape_theme);
        UpdateAppUtils
                .getInstance()
                .apkUrl(data.getApkUrl())
                .updateTitle("发现新版本:V"+data.getApkName())
                .updateContent(data.getDescription())
                .uiConfig(uiConfig)
                .updateConfig(updateConfig)
                .setMd5CheckResultListener(new Md5CheckResultListener() {
                    @Override
                    public void onResult(boolean result) {

                    }
                })
                .setUpdateDownloadListener(new UpdateDownloadListener() {
                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onDownload(int progress) {

                    }

                    @Override
                    public void onFinish() {

                    }

                })
                .update();
    }
}
