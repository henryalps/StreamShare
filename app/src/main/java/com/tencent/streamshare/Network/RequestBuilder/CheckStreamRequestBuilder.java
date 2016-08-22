package com.tencent.streamshare.Network.RequestBuilder;

import com.tencent.streamshare.Model.User;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhaoyongfei on 2016/8/21.
 */
public class CheckStreamRequestBuilder implements RequestBuilderInterface {
    private int mStreamId;
    @Override
    public JSONObject build() {
        JSONObject forReturn = new JSONObject();
        try {
            forReturn.put("streamid", mStreamId);
            forReturn.put("userid", User.getInstance().getmId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return forReturn;
    }
}
