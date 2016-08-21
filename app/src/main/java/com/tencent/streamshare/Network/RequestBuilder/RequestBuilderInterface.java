package com.tencent.streamshare.Network.RequestBuilder;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by henryrhe on 2016/8/21.
 * 网络请求构造器接口
 * 目前简单实现，就是在build里简单返回一个包含所有请求字段的JSONObject，即可
 */
public interface RequestBuilderInterface{
    JSONObject build();
}
