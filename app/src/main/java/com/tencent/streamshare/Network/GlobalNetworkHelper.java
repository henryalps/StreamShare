package com.tencent.streamshare.Network;

import android.content.Context;
import android.util.Log;

import com.google.zxing.common.StringUtils;
import com.sdsmdg.tastytoast.TastyToast;
import com.tencent.streamshare.Network.Listener.ResultListener;
import com.tencent.streamshare.Network.RequestBuilder.CommonRequestBuilder;
import com.tencent.streamshare.Network.ResultAnalyser.CommonAnalyser;
import com.tencent.streamshare.Network.ResultAnalyser.ResultAnalyserInterface;
import com.tencent.streamshare.Utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.OkHttpRequestBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by henryrhe on 2016/8/21.
 * 统一的网络类
 * 1. addRequest 添加一个requestbuilder生成的request
 * 2. addAnalyser 添加一个Analyser解析器
 * 3. start 开始网络请求，开始请求后，无法继续添加request
 */
public class GlobalNetworkHelper implements ResultListener{
    private Context mContext; // 需要统一的弹窗提醒
    private boolean mHasStarted = false; // 是否正在请求网络
    private boolean mNeedAnalyse = true; // 通用解析器会阻塞后面的解析器

    private String mUrl = "";
    private int mType = Constants.REQ_TYPE_GET; // 默认GET

    private ArrayList<JSONObject> mRequestArray = new ArrayList<>(); // 所有的请求字段
    private ArrayList<ResultAnalyserInterface> mResultAnalysers = new ArrayList<>(); // 所有的结果解析器

    public GlobalNetworkHelper(Context mContext, String mUrl, int mType) {
        this.mContext = mContext;
        this.mUrl = mUrl;
        this.mType = mType;

        addRequest(new CommonRequestBuilder().build()); // 通用的请求格式定义

        addAnalyser(new CommonAnalyser(this)); // 保证通用解析器处在第一位
    }

    public GlobalNetworkHelper addRequest(JSONObject request) {
        if (mHasStarted) {
            Log.e("henryrhe", "正在网络请求，无法添加请求字段");
            return this;
        }
        mRequestArray.add(request);
        return this;
    }

    public GlobalNetworkHelper addAnalyser(ResultAnalyserInterface analyser) {
        mResultAnalysers.add(analyser);
        return this;
    }

    public void start(){
        mHasStarted =  true;
        OkHttpRequestBuilder builder;
        if (mType == Constants.REQ_TYPE_GET){
            builder = OkHttpUtils.get();
        } else {
            builder = OkHttpUtils.post();
        }

        builder = builder.url(mUrl);

        for(JSONObject obj:mRequestArray) {
            Iterator<String> keys = obj.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                try {
                    if (mType == Constants.REQ_TYPE_GET){
                        ((GetBuilder)builder).addParams(key, obj.getString(key));
                    } else {
                        ((PostFormBuilder)builder).addParams(key, obj.getString(key));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }


        builder.build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mHasStarted = false;
            }

            @Override
            public void onResponse(String response, int id) {
                mHasStarted = false;
                for(ResultAnalyserInterface analyser:mResultAnalysers) {
                    if (mNeedAnalyse){
                        try {
                            analyser.analysis(new JSONObject(response));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            onFail(Constants.CODE_RESULT_JSON_FAIL, "Json解析失败");
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onSuccess(Object data) {
        mNeedAnalyse = true;
    }

    @Override
    public void onFail(int Code, String Msg) {
        // 统一的Toast提醒
        TastyToast.makeText(mContext, com.ihongqiqu.util.StringUtils.isEmpty(Msg) ?
                "错误码：" + Code : Msg, TastyToast.LENGTH_LONG, TastyToast.ERROR);
        // 停止以后的解析器 TODO 实现不够优雅
        mNeedAnalyse = false;
    }
}
