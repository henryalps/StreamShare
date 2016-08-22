package com.tencent.streamshare.Activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.tencent.streamshare.Adapter.UserListAdapter;
import com.sdsmdg.tastytoast.TastyToast;
import com.tencent.streamshare.Model.BaseUser;
import com.tencent.streamshare.Model.StreamInfo;
import com.tencent.streamshare.Model.User;
import com.tencent.streamshare.MyApplication;
import com.tencent.streamshare.Network.GlobalNetworkHelper;
import com.tencent.streamshare.Network.Listener.ResultListener;
import com.tencent.streamshare.Network.RequestBuilder.ExitStreamRequestBuilder;
import com.tencent.streamshare.Network.RequestBuilder.GetBarCodeBuilder;
import com.tencent.streamshare.Network.ResultAnalyser.ExitStreamAnalyser;
import com.tencent.streamshare.Network.ResultAnalyser.GetBarCodeAnalyser;
import com.tencent.streamshare.R;
import com.tencent.streamshare.Utils.Constants;
import com.tencent.streamshare.View.CustomDialog;


import java.util.ArrayList;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.VideoView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class PlayerActivity extends AppCompatActivity {

    private Button Share;
    private DrawerLayout mDrawerLayout ;
    private ListView userList;
    private UserListAdapter mUserListAdapter;
    public static final String STREAM_URL_TAG = "STREAM_URL_TAG";
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mVideoView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    private VideoView mVideoView;
    private View mControlsView;
    private Button mBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        initView();
        startPlay();
    }

    private void initView() {
        initPlayerView();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mBackBtn = (ImageView) findViewById(R.id.stop_stream_btn);
//        mBackBtn.setScaleX(-1);
//        mBackBtn.setScaleY(1);
        mBackBtn = (Button) findViewById(R.id.return_btn);
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitPage();
            }
        });
        Share = (Button)findViewById(R.id.user_share);
        Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doDialog();
            }
        });
        userList =(ListView)this.findViewById(R.id.right_drawer);

        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mVisible = true;

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
        initUserAdaper();
    }

    private void initPlayerView() {
        if (io.vov.vitamio.LibsChecker.checkVitamioLibs(this)){
            mVideoView = (VideoView) findViewById(R.id.surface_view);
            mVideoView.requestFocus();
            mVideoView.setVideoChroma(MediaPlayer.VIDEOCHROMA_RGB565);
            mVideoView.setVideoPath(User.getInstance().getmCurrentStream().getmUrl());
        }
    }

    private void startPlay()  {
        if (mVideoView != null) {
            mVideoView.start();
        }
    }

    private void stopPlay() {
        if (mVideoView != null) {
            mVideoView.pause();
        }
        new GlobalNetworkHelper(this, Constants.URL_EXIT_STREAM)
                .addRequest(new ExitStreamRequestBuilder().build())
                .addAnalyser(new ExitStreamAnalyser(new ResultListener() {
                    @Override
                    public void onSuccess(Object data) {
                        User.getInstance().setmCurrentStream(new StreamInfo()); // 退出播放流时必须清空当前播放流信息
                    }

                    @Override
                    public void onFail(int Code, String Msg) {
                        User.getInstance().setmCurrentStream(new StreamInfo()); // 退出播放流时必须清空当前播放流信息
                    }
                })).start();
    }

    private void exitPage() {
        stopPlay();
        finish();
    }
    private void doDialog(){


        new GlobalNetworkHelper(this, Constants.URL_ATTAIN_BAR_CODE, Constants.REQ_TYPE_GET)
                .addRequest(new GetBarCodeBuilder(User.getInstance().getmCurrentStream()).build())
                .addAnalyser(new GetBarCodeAnalyser(new ResultListener() {
                    @Override
                    public void onSuccess(Object data) {
                        //返回验证码
                        final CustomDialog.Builder builder = new CustomDialog.Builder(PlayerActivity.this);
                        builder.setMessage((String) data);
                        builder.setTitle("二维码分享");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                //设置你的操作事项
                            }
                        });
                        builder.setNegativeButton("取消",
                                new android.content.DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });

                        builder.create().show();
                    }

                    @Override
                    public void onFail(int Code, String Msg) {
                        TastyToast.makeText(PlayerActivity.this, com.ihongqiqu.util.StringUtils.isEmpty(Msg) ?
                                "错误码：" + Code : Msg, TastyToast.LENGTH_LONG, TastyToast.ERROR);
                    }
                }, (MyApplication) getApplication()))
        .start();

    }
    private void initUserAdaper(){
        BaseUser user1 = new BaseUser();
        user1.setmNickName("ceshi1");
        user1.setmHeadImagUrl("data:image/jpeg;base64,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");
        BaseUser user2 = new BaseUser();
        user2.setmHeadImagUrl("data:image/jpeg;base64,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");
        user2.setmNickName("ceshi2");
        ArrayList<BaseUser> mData =new ArrayList<BaseUser>();
        mData.add(user1);
        mData.add(user2);
        mUserListAdapter =new UserListAdapter(this,mData);
        userList.setAdapter(mUserListAdapter);
        mUserListAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        //delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mVideoView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitPage();
            return true;
        } else  {
            return super.onKeyDown(keyCode, event);
        }
    }
}
