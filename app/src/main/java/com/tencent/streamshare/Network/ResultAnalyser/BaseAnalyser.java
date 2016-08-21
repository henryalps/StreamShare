package com.tencent.streamshare.Network.ResultAnalyser;

import com.ihongqiqu.util.JSONUtils;
import com.tencent.streamshare.Network.Listener.ResultListener;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhaoyongfei on 2016/8/21.
 * 带回调的分析器基类
 */
public abstract class BaseAnalyser implements ResultAnalyserInterface  {
    protected ResultListener mListener;

    public BaseAnalyser(ResultListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public boolean analysis(JSONObject result) throws JSONException {
        JSONObject object = JSONUtils.getJSONObject(result,"result",new JSONObject());
        return doAnalysis(object);
    }

    /**
     *
     * @param data json返回数据中的“result”字段
     * @return 分析成功或失败
     */
    protected abstract boolean doAnalysis(JSONObject data);
}
