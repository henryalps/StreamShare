package com.tencent.streamshare.Model;

import java.util.ArrayList;

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
    private long mTime;
    private int mStatus;

    private boolean mCouldShare = false;
    private int mShareNum = 0;
    private ArrayList<BaseUser> mSharingUser; // 此流分享给的用户

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

    public long getmTime() {
        return mTime;
    }

    public void setmTime(long mTime) {
        this.mTime = mTime;
    }

    public int getmStatus() {
        return mStatus;
    }

    public void setmStatus(int mStatus) {
        this.mStatus = mStatus;
    }

    public boolean ismCouldShare() {
        return mCouldShare;
    }

    public void setmCouldShare(boolean mCouldShare) {
        this.mCouldShare = mCouldShare;
    }
}
