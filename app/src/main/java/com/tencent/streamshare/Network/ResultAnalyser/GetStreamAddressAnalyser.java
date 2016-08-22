package com.tencent.streamshare.Network.ResultAnalyser;

import com.ihongqiqu.util.JSONUtils;
import com.tencent.streamshare.Model.StreamInfo;
import com.tencent.streamshare.Model.User;
import com.tencent.streamshare.Network.Listener.ResultListener;

import org.json.JSONObject;

/**
 * Created by zhaoyongfei on 2016/8/21.
 */
public class GetStreamAddressAnalyser extends BaseAnalyser {
    public GetStreamAddressAnalyser(ResultListener mListener) {
        super(mListener);
    }

    @Override
    protected boolean doAnalysis(JSONObject data) {
        StreamInfo info = new StreamInfo();
        info.setmUrl(JSONUtils.getString(data,"address",""));
        info.setmCouldShare(JSONUtils.getInt(data, "share", 0) == 1);
        User.getInstance().setmCurrentStream(info);
        User.getInstance().setGroupkey(JSONUtils.getString(data,"groupkey",""));
        mListener.onSuccess(data);
        return true;
    }
}
