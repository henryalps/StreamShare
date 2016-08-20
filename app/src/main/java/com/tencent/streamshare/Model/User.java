package com.tencent.streamshare.Model;

/**
 * Created by henryrhe on 2016/8/20.
 * 用户模型
 */
public class User {
    private static User mInstance;
    private int mId;
    private boolean mIsVip;
    private boolean mToken;

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

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public boolean ismIsVip() {
        return mIsVip;
    }

    public void setmIsVip(boolean mIsVip) {
        this.mIsVip = mIsVip;
    }

    public boolean ismToken() {
        return mToken;
    }

    public void setmToken(boolean mToken) {
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
}
