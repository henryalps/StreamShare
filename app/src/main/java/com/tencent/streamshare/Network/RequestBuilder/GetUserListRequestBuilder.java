package com.tencent.streamshare.Network.RequestBuilder;

import com.ihongqiqu.util.StringUtils;
import com.tencent.streamshare.Model.User;
import com.tencent.streamshare.Utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhaoyongfei on 2016/8/22.
 */
public class GetUserListRequestBuilder extends BaseRequestBuilder {
    @Override
    protected JSONObject doBuilder(JSONObject forReturn) throws JSONException {
        forReturn.put("userid", User.getInstance().getmId());
        forReturn.put("streamid", User.getInstance().getmCurrentStream().getmId());
        forReturn.put("groupkey", StringUtils.isEmpty(User.getInstance().getGroupkey())?
            User.getInstance().getmId() + Constants.STR_CONNECTOR + User.getInstance().getmCurrentStream().getmId():
            User.getInstance().getGroupkey());
        return forReturn;
    }
}
