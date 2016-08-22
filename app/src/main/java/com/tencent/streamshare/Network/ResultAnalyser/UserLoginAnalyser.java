package com.tencent.streamshare.Network.ResultAnalyser;

import com.ihongqiqu.util.JSONUtils;
import com.tencent.streamshare.Model.User;
import com.tencent.streamshare.Network.Listener.ResultListener;
import com.tencent.streamshare.Utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhaoyongfei on 2016/8/21.
 */
public class UserLoginAnalyser implements ResultAnalyserInterface {
    private ResultListener mListener;

    public UserLoginAnalyser(ResultListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public boolean analysis(JSONObject result) throws JSONException {
        JSONObject data = JSONUtils.getJSONObject(result, "data", result);
        if (JSONUtils.getInt(data, "state", -1) != Constants.CODE_LOGIN_SUCCESS) {
            mListener.onFail(JSONUtils.getInt(data, "state", -1), "登录失败");
            return false;
        }
        User.getInstance().setmIsVip(JSONUtils.getInt(data, "vipflag", 0) == Constants.CODE_LOGIN_IS_VIP);
        User.getInstance().setmNickName(JSONUtils.getString(data, "nickname", "guest"));
        User.getInstance().setmHeadImagUrl(JSONUtils.getString(data, "head_img_url", ""));
        mListener.onSuccess(data);
        return true;
    }
}
