package com.tencent.streamshare.Network.ResultAnalyser;

import com.ihongqiqu.util.JSONUtils;
import com.ihongqiqu.util.RandomUtils;
import com.tencent.streamshare.Model.StreamInfo;
import com.tencent.streamshare.Network.Listener.ResultListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by zhaoyongfei on 2016/8/21.
 */
public class StreamListResultAnalyser implements ResultAnalyserInterface {
    private ResultListener mListener;

    public StreamListResultAnalyser(ResultListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public boolean analysis(JSONObject result) throws JSONException {
        JSONArray streamArray = JSONUtils.getJSONArray(
                JSONUtils.getJSONObject(result, "data", result),
                "node",
                new JSONArray()
        );
        ArrayList<StreamInfo> data = new ArrayList<>();
        for(int i=0; i<streamArray.length();i++) {
            data.add(convertNodeToStruct(streamArray.getJSONObject(i)));
        }
        mListener.onSuccess(data);
        return true;
    }

    public StreamInfo convertNodeToStruct(JSONObject node) {
        StreamInfo forReturn = new StreamInfo();
        forReturn.setmId(JSONUtils.getString(node, "streamid", ""));
        forReturn.setmName(JSONUtils.getString(node, "name", ""));
        forReturn.setmUrl(JSONUtils.getString(node, "url", ""));
        forReturn.setmImgUrl(JSONUtils.getString(node, "cover_img", ""));
        forReturn.setmVIewCount(JSONUtils.getInt(node, "total_view", RandomUtils.getRandom(1000)));
        forReturn.setmTime(JSONUtils.getString(node, "start_time", "2016-08-17 19:03:26"));
        forReturn.setmStatus(JSONUtils.getInt(node, "status", 1));
        forReturn.setmHasRight(JSONUtils.getInt(node, "right", 0) == 1);
        return forReturn;
    }
}
