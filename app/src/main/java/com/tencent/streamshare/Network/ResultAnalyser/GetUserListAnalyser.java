package com.tencent.streamshare.Network.ResultAnalyser;

import com.ihongqiqu.util.JSONUtils;
import com.ihongqiqu.util.LogUtils;
import com.tencent.streamshare.Model.BaseUser;
import com.tencent.streamshare.Model.User;
import com.tencent.streamshare.Network.Listener.ResultListener;
import com.tencent.streamshare.Utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by zhaoyongfei on 2016/8/22.
 */
public class GetUserListAnalyser extends BaseAnalyser {
    public GetUserListAnalyser(ResultListener mListener) {
        super(mListener);
    }

    @Override
    protected boolean doAnalysis(JSONObject data) {
        JSONArray users = JSONUtils.getJSONArray(data, "users", new JSONArray());
        int x = JSONUtils.getInt(data, "total", 0);
        ArrayList<BaseUser> list = new ArrayList<>();
        if (x > 0) {



        for (int i = 0; i <= x; i++) {
            try {
                list.add(convertJsonNode2User(users.getJSONObject(i)));
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
        }
        LogUtils.d("henryrhe", "******A user list query has been successfully performed , list size is " + list.size() + " data is " + data.toString());
        User.getInstance().getmCurrentStream().setmSharingUser(list);
        mListener.onSuccess(data);
        return true;
    }

    private BaseUser convertJsonNode2User(JSONObject node) {
        BaseUser forReturn = new BaseUser();
        forReturn.setmHeadImagUrl(JSONUtils.getString(node, "head_img_url", Constants.URL_DEFAULT_IMAGE));
        forReturn.setmNickName(JSONUtils.getString(node, "name", "guest"));
        forReturn.setmId(JSONUtils.getString(node, "userid", ""));

        return forReturn;
    }
}
