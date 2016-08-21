package com.tencent.streamshare.Network.RequestBuilder;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhaoyongfei on 2016/8/21.
 */
public class CommonRequestBuilder implements RequestBuilderInterface {
    @Override
    public JSONObject build() {
        JSONObject forReturn  =  new JSONObject();
        try {
            forReturn.put("otype", "json");
            forReturn.put("jsonp","");
            forReturn.put("format", "json");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return forReturn;
    }
}
