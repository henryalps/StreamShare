package com.tencent.streamshare.Model;

/**
 * Created by henryrhe on 2016/8/20.
 * 用户模型
 */
public class User extends BaseUser{
    private static User mInstance;
    private String mNickName = "guest";
    private String mPasswd = "";
    private boolean mIsVip = false;
    private String mToken = "";

    /*一次只能播放一个视频*/
    private StreamInfo mCurrentStream;

    private User() {

    }

    public static User getInstance() {
        if (mInstance==null) {
            mInstance  = new User();
        }
        return mInstance;
    }

    public boolean ismIsVip() {
        return mIsVip;
    }

    public void setmIsVip(boolean mIsVip) {
        this.mIsVip = mIsVip;
    }

    public String getmToken() {
        return mToken;
    }

    public void setmToken(String mToken) {
        this.mToken = mToken;
    }

    public StreamInfo getmCurrentStream() {
        return mCurrentStream;
    }

    public void setmCurrentStream(StreamInfo mCurrentStream) {
        this.mCurrentStream = mCurrentStream;
    }

    public String getmPasswd() {
        return mPasswd;
    }

    public void setmPasswd(String mPasswd) {
        this.mPasswd = mPasswd;
    }

    public String getmNickName() {
        return mNickName;
    }

    public void setmNickName(String mNickName) {
        this.mNickName = mNickName;
    }
}
