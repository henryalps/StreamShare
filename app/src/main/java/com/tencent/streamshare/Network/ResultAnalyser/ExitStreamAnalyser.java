package com.tencent.streamshare.Network.ResultAnalyser;

import com.tencent.streamshare.Network.Listener.ResultListener;

import org.json.JSONObject;

/**
 * Created by zhaoyongfei on 2016/8/21.
 * TODO 没有处理退出的结果
 */
public class ExitStreamAnalyser extends BaseAnalyser {
    public ExitStreamAnalyser(ResultListener mListener) {
        super(mListener);
    }

    @Override
    protected boolean doAnalysis(JSONObject data) {
        return true;
    }
}
