package com.tencent.streamshare.Network.ResultAnalyser;

import com.ihongqiqu.util.JSONUtils;
import com.tencent.streamshare.Network.Listener.ResultListener;

import org.json.JSONObject;

/**
 * Created by zhaoyongfei on 2016/8/22.
 */
public class PushAnalyser extends BaseAnalyser {
    public PushAnalyser(ResultListener mListener) {
        super(mListener);
    }

    @Override
    protected boolean doAnalysis(JSONObject data) {
        if (JSONUtils.getInt(data, "command", 0) == 1) { // 简化处理，认为只有在命令号为1时才对应请求成功
            mListener.onSuccess(JSONUtils.getString(data, "content", ""));
        }
        return false;
    }
}
