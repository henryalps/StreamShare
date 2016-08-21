package com.tencent.streamshare.Network.ResultAnalyser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by henryrhe on 2016/8/21.
 * 网络请求解析器接口
 */
public interface ResultAnalyserInterface {
    boolean analysis(JSONObject result) throws JSONException;
}
