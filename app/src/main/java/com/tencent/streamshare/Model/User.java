package com.tencent.streamshare.Model;

/**
 * Created by henryrhe on 2016/8/20.
 * 用户模型
 */
public class User {
    private static User mInstance;
    private String mId = "";
    private String mNickName = "guest";
    private String mPasswd = "";
    private boolean mIsVip = false;
    private String mToken = "";
    private String mHeadImagUrl = ""; // TODO 需要默认值？

    /*一次只能播放一个视频*/
    private int mCurrentVideoId; // 当前播放的视频id
    private int mShareNum; // 当前视频的分享数

    private User() {

    }

    public static User getInstance() {
        if (mInstance==null) {
            mInstance  = new User();
        }
        return mInstance;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
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

    public int getmCurrentVideoId() {
        return mCurrentVideoId;
    }

    public void setmCurrentVideoId(int mCurrentVideoId) {
        this.mCurrentVideoId = mCurrentVideoId;
    }

    public int getmShareNum() {
        return mShareNum;
    }

    public void setmShareNum(int mShareNum) {
        this.mShareNum = mShareNum;
    }

    public String getmPasswd() {
        return mPasswd;
    }

    public void setmPasswd(String mPasswd) {
        this.mPasswd = mPasswd;
    }

    public String getmHeadImagUrl() {
        return mHeadImagUrl;
    }

    public void setmHeadImagUrl(String mHeadImagUrl) {
        this.mHeadImagUrl = mHeadImagUrl;
    }

    public String getmNickName() {
        return mNickName;
    }

    public void setmNickName(String mNickName) {
        this.mNickName = mNickName;
    }
}
