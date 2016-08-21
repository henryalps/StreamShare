package com.tencent.streamshare.Network.RequestBuilder;

import com.tencent.streamshare.Model.StreamInfo;
import com.tencent.streamshare.Model.User;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhaoyongfei on 2016/8/21.
 */
public class GetBarCodeBuilder extends BaseRequestBuilder {
    private StreamInfo mInfo;

    public GetBarCodeBuilder(StreamInfo mInfo) {
        this.mInfo = mInfo;
    }

    @Override
    protected JSONObject doBuilder(JSONObject forReturn) throws JSONException {
        forReturn.put("streamid", mInfo.getmId());
        forReturn.put("userid", User.getInstance().getmId());
        return forReturn;
    }
}