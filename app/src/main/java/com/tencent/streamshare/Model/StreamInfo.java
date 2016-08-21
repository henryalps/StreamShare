package com.tencent.streamshare.Model;

/**
 * Created by zhaoyongfei on 2016/8/21.
 */
public class StreamInfo {
    public final static int CODE_STREAM_STATUS_UNSTARTED = 0;
    public final static int CODE_STREAM_STATUS_STREAMING = 1;
    public final static int CODE_STREAM_STATUS_FINISH = 2;

    private String mId;
    private String mName;
    private String mUrl;
    private String mImgUrl;
    private int mVIewCount;
    private boolean mHasRight;
    private String mTime;
    private int mStatus;

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmImgUrl() {
        return mImgUrl;
    }

    public void setmImgUrl(String mImgUrl) {
        this.mImgUrl = mImgUrl;
    }

    public int getmVIewCount() {
        return mVIewCount;
    }

    public void setmVIewCount(int mVIewCount) {
        this.mVIewCount = mVIewCount;
    }

    public boolean ismHasRight() {
        return mHasRight;
    }

    public void setmHasRight(boolean mHasRight) {
        this.mHasRight = mHasRight;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public int getmStatus() {
        return mStatus;
    }

    public void setmStatus(int mStatus) {
        this.mStatus = mStatus;
    }
}
