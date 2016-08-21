package com.tencent.streamshare.Network.RequestBuilder;

import com.tencent.streamshare.Model.User;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhaoyongfei on 2016/8/21.
 * 票据换取播放地址
 */
public class GetStreamAddressRequestBuilder extends BaseRequestBuilder {
    private String mToken;

    @Override
    protected JSONObject doBuilder(JSONObject forReturn) throws JSONException {
        forReturn.put("userid", User.getInstance().getmId());
        forReturn.put("token", mToken);
        return forReturn;
    }
}
