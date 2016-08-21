package com.tencent.streamshare.Network.ResultAnalyser;

import com.tencent.streamshare.Network.Listener.ResultListener;

import org.json.JSONObject;

/**
 * Created by zhaoyongfei on 2016/8/21.
 */
public class GetStreamAddressRequestAnalyser extends BaseAnalyser {
    public GetStreamAddressRequestAnalyser(ResultListener mListener) {
        super(mListener);
    }

    @Override
    protected boolean doAnalysis(JSONObject data) {
        return false;
    }
}
