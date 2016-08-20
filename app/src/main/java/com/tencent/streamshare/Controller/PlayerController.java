package com.tencent.streamshare.Controller;

import com.ihongqiqu.util.StringUtils;
import com.pili.pldroid.player.widget.PLVideoView;

/**
 * Created by henryrhe on 2016/8/20.
 * 播放器控制器
 */
public class PlayerController {
    private String mStreamUrl = "";
    private PLVideoView mVideoView;
    private boolean mHasInit = false;

    public PlayerController(String mStreamUrl, PLVideoView mVideoView) {
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
        if (mHasInit) {
            mVideoView.start();
        }
    }
}
