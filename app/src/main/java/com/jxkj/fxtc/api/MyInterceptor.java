package com.jxkj.fxtc.api;


import android.util.Log;

import com.blankj.utilcode.util.LogUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

public class MyInterceptor implements Interceptor {
    private static String TAG = MyInterceptor.class.getSimpleName();
    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        //获得请求信息，此处如有需要可以添加headers信息
        Request request = chain.request();
        //添加Cookie信息
        request.newBuilder().addHeader("Cookie","aaaa");
        //打印请求信息
        syso("url:" + request.url());
        syso("method:" + request.method());
        syso("request-body:" + request.body());

        //记录请求耗时
        long startNs = System.nanoTime();
        okhttp3.Response response;
        try {
            //发送请求，获得相应，
            response = chain.proceed(request);
        } catch (Exception e) {
            throw e;
        }
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
        //打印请求耗时
        syso("耗时:"+tookMs+"ms");
        //使用response获得headers(),可以更新本地Cookie。
        syso("headers==========");
        Headers headers = response.headers();
        syso(headers.toString());

        //获得返回的body，注意此处不要使用responseBody.string()获取返回数据，原因在于这个方法会消耗返回结果的数据(buffer)
        ResponseBody responseBody = response.body();

        //为了不消耗buffer，我们这里使用source先获得buffer对象，然后clone()后使用
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        //获得返回的数据
        Buffer buffer = source.buffer();
        //使用前clone()下，避免直接消耗
//        syso("response:" + buffer.clone().readString(Charset.forName("UTF-8")));
        Log.d(TAG,"api url = "+ request.url() +" http response = "+ buffer.clone().readString(Charset.forName("UTF-8")));
        return response;
    }


    private void syso(String msg){
        LogUtils.i("HTTP","接口请求："+ msg);
//        TextUtil.logOut("接口请求："+ msg);
    }
}
