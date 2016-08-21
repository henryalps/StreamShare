package com.tencent.streamshare.Network.RequestBuilder;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhaoyongfei on 2016/8/21.
 * 请求工厂基类
 */
public abstract class BaseRequestBuilder implements RequestBuilderInterface {
    @Override
    public JSONObject build() {
        JSONObject forReturn = new JSONObject();
        try {
            forReturn = doBuilder(forReturn);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return forReturn;
    }

    protected abstract JSONObject doBuilder(JSONObject forReturn) throws JSONException;
}
