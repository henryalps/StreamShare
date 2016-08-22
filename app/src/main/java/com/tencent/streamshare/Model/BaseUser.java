package com.tencent.streamshare.Model;

/**
 * Created by zhaoyongfei on 2016/8/21.
 */
public class BaseUser {
    private String mId = "";

    private String mNickName = "guest";

    private String mHeadImagUrl = ""; // TODO 需要默认值？

    private String groupkey = "";

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmNickName() {
        return mNickName;
    }

    public void setmNickName(String mNickName) {
        this.mNickName = mNickName;
    }

    public String getmHeadImagUrl() {
        return mHeadImagUrl;
    }

    public void setmHeadImagUrl(String mHeadImagUrl) {
        this.mHeadImagUrl = mHeadImagUrl;
    }

    public String getGroupkey() {
        return groupkey;
    }

    public void setGroupkey(String groupkey) {
        this.groupkey = groupkey;
    }
}
