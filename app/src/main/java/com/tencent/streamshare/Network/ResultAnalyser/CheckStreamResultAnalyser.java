package com.tencent.streamshare.Network.ResultAnalyser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhaoyongfei on 2016/8/21.
 */
public class CheckStreamResultAnalyser implements ResultAnalyserInterface {
    @Override
    public boolean analysis(JSONObject result) throws JSONException {
        return false;
    }
}
