package com.tencent.streamshare.Network.ResultAnalyser;
import com.ihongqiqu.util.JSONUtils;
import com.ihongqiqu.util.StringUtils;
import com.tencent.streamshare.MyApplication;
import com.tencent.streamshare.Network.Listener.ResultListener;
import com.tencent.streamshare.Utils.Constants;

import org.json.JSONObject;

/**
 * Created by zhaoyongfei on 2016/8/21.
 */
public class GetBarCodeAnalyser extends BaseAnalyser {
    private MyApplication mMyApplication;
    public GetBarCodeAnalyser(ResultListener mListener, MyApplication myApplication) {
        super(mListener);
        mMyApplication = myApplication;
    }

    @Override
    protected boolean doAnalysis(JSONObject data) {
        String token = JSONUtils.getString(data,"token","");
        if (StringUtils.isEmpty(token)) {
            mListener.onFail(Constants.CODE_RESULT_ANALYSIS_FAIL, "请求失败");
            return false;
        }
        mMyApplication.setmAppId(JSONUtils.getString(data,"appid","OMG_SIX"));
        mListener.onSuccess(token);
        return true;
    }
}
