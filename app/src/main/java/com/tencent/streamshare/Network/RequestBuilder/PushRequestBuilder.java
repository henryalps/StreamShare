package com.tencent.streamshare.Network.RequestBuilder;

import com.tencent.streamshare.Model.User;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhaoyongfei on 2016/8/22.
 */
public class PushRequestBuilder extends BaseRequestBuilder {
    @Override
    protected JSONObject doBuilder(JSONObject forReturn) throws JSONException {
        forReturn.put("userid", User.getInstance().getmId());
        forReturn.put("streamid", User.getInstance().getmCurrentStream().getmId());
        return forReturn;
    }
}
