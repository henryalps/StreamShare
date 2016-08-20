package com.tencent.streamshare.Controller;

import android.util.Log;

import com.ihongqiqu.util.StringUtils;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.pili.pldroid.player.widget.PLVideoView;

/**
 * Created by henryrhe on 2016/8/20.
 * 播放器控制器
 */
public class PlayerController {
    private String mStreamUrl = "";
    private PLVideoTextureView mVideoView;
    private boolean mHasInit = false;

    public PlayerController(String mStreamUrl, PLVideoTextureView mVideoView) {
        this.mStreamUrl = mStreamUrl;
        this.mVideoView = mVideoView;
    }

    public PlayerController init() {
        if (!StringUtils.isEmpty(mStreamUrl) && mVideoView != null) {
            mVideoView.setVideoPath(mStreamUrl);
            mHasInit = true;
        } else {
            mHasInit = false;
        }
        return this;
    }

    public void start() {
        if (ismHasInit()) {
            mVideoView.start();
        }
    }

    public boolean ismHasInit() {
        if (!mHasInit) {
            Log.e("henryrhe", "播放器没有初始化,调用init()初始化");
        }
        return mHasInit;
    }
}