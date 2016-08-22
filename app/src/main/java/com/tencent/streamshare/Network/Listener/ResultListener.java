package com.tencent.streamshare.Network.Listener;

/**
 * Created by zhaoyongfei on 2016/8/21.
 */
public interface ResultListener {
    void onSuccess(Object data);
    void onFail(int Code, String Msg);
}
