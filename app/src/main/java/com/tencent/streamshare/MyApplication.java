package com.tencent.streamshare;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by zhaoyongfei on 2016/8/20.
 */
public class MyApplication extends Application {
    private String mAppId = "";
    @Override
    public void onCreate()
    {
        super.onCreate();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

    }

    public String getmAppId() {
        return mAppId;
    }

    public void setmAppId(String mAppId) {
        this.mAppId = mAppId;
    }
}
