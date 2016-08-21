package com.tencent.streamshare.Network.ResultAnalyser;

import com.ihongqiqu.util.JSONUtils;
import com.tencent.streamshare.Network.Listener.ResultListener;
import com.tencent.streamshare.Utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhaoyongfei on 2016/8/21.
 *  返回包头解析器, 在所有的其它解析器前调用：
 *  result:{
 *      ret : int,    //返回结果, 0 成功， 非0失败
         code:int,    //附带的服务端错误码，客户端可忽略这个参数
         msg : string,  //相关的错误信息，可能为空
         costtime : int   //该请求耗时
 }
 */
public class CommonAnalyser implements ResultAnalyserInterface  {
    private ResultListener mListener;

    public CommonAnalyser(ResultListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public boolean analysis(JSONObject result) throws JSONException{
        JSONObject object = JSONUtils.getJSONObject(result,"result",new JSONObject());
        int ret = JSONUtils.getInt(object, "ret", Constants.CODE_RESULT_JSON_FAIL);
        if (ret == Constants.CODE_RESULT_RET_SUCCESS) {
            mListener.onSuccess(result);
        } else {
            mListener.onFail(ret, JSONUtils.getString(object, "msg", "未知错误"));
        }
        return true;
    }
}
