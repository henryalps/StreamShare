package com.tencent.streamshare.Network.RequestBuilder;

import com.ihongqiqu.util.JSONUtils;
import com.tencent.streamshare.Model.User;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhaoyongfei on 2016/8/21.
 */
public class UserLoginRequestBuilder implements RequestBuilderInterface {
    @Override
    public JSONObject build(){
        JSONObject forReturn = new JSONObject();
        try {
            forReturn.put("passwd", User.getInstance().getmPasswd());
            forReturn.put("userid", User.getInstance().getmId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return forReturn;
    }
}
