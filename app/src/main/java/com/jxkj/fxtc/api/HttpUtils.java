package com.jxkj.fxtc.api;

import com.blankj.utilcode.util.ToastUtils;
import com.jxkj.fxtc.base.Result;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HttpUtils {
    public static void goGetYzm(String sjh,int verifyType) {
        RetrofitUtil.getInstance().apiService()
                .getVerifyCode(sjh)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getStatus()==0){
                            ToastUtils.showShort("发送成功");
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

}
