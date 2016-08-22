package com.tencent.streamshare.Network.RequestBuilder;

import com.ihongqiqu.util.StringUtils;
import com.tencent.streamshare.Model.User;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhaoyongfei on 2016/8/21.
 */
public class ExitStreamRequestBuilder extends BaseRequestBuilder {
    @Override
    protected JSONObject doBuilder(JSONObject forReturn) throws JSONException {
        forReturn.put("userid", User.getInstance().getmId());
        forReturn.put("streamid", User.getInstance().getmCurrentStream().getmId());
        String groupKey = User.getInstance().getGroupkey();
        if (StringUtils.isEmpty(groupKey)) {
            groupKey = User.getInstance().getmId() + "+" +User.getInstance().getmCurrentStream().getmId();
        }
        forReturn.put("groupkey", groupKey);
        return forReturn;
    }
}
