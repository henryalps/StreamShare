package com.tencent.streamshare.Utils;

import android.net.Uri;

/**
 * Created by zhaoyongfei on 2016/8/20.
 */
public class Constants {
    public final static String URL_USER_LOG_IN =
            "http://1.tv.ptyg.gitv.tv/i-tvbin/qtv_video/omgtrain/user_login";
    public final static String URL_LIVE_STREAM_INFO =
            "http://1.tv.ptyg.gitv.tv/i-tvbin/qtv_video/omgtrain/live_stream_info";
    public final static String URL_USER_CHECK_STREAM =
            "http://1.tv.ptyg.gitv.tv/i-tvbin/qtv_video/omgtrain/check_stream";
    public final static String URL_ATTAIN_BAR_CODE =
            "http://1.tv.ptyg.gitv.tv/i-tvbin/qtv_video/omgtrain/get_twocode_info";
    public final static String URL_ATTAIN_STREAM_ADDRESS =
            "http://1.tv.ptyg.gitv.tv/i-tvbin/qtv_video/omgtrain/get_stream_addr";
    public final static String URL_GET_PUSH_COMMAND =
            "http://1.tv.ptyg.gitv.tv/i-tvbin/qtv_video/omgtrain/get_push_command";
    public final static String URL_GET_USER_LIST =
            "http://1.tv.ptyg.gitv.tv/i-tvbin/qtv_video/omgtrain/get_user_list";
    public final static String URL_EXIT_STREAM =
            "http://1.tv.ptyg.gitv.tv/i-tvbin/qtv_video/omgtrain/user_exit_stream";

    public final static String URL_DEFAULT_IMAGE = ""; // 统一的头像url

    public final static int CODE_RESULT_RET_SUCCESS = 0;
//    public final static int CODE_RESULT_RET_FAIL =1;
    public final static int CODE_RESULT_STATE_SUCCESS = 0;


    public final static int CODE_RESULT_IS_VIP = 1;

    public final static int REQ_TYPE_GET = 0x00;
    public final static int REQ_TYPE_POST = 0x01;

    public final static int CODE_RESULT_JSON_FAIL = 0x10; // 不会和后台的网络错误码冲突
    public final static int CODE_RESULT_ANALYSIS_FAIL = 0x11;

    public final static int CODE_LOGIN_SUCCESS = 0;
    public final static int CODE_LOGIN_IS_VIP = 1;

    public final static String STR_PROTOCOL_HEADER = "OMG_SIX";

    public final static int PUSH_PERIOD = 2000; // 轮询周期2s

    public final static String STR_CONNECTOR = "+"; //用这个符号连接用户id和流id

    public final static Uri URI_IMG_VIP = Uri.parse("http://img1.114pifa.com/2045/t7TH9GDYG_1400207302.jpg");
    public final static Uri URI_IMG_ORDINARY = Uri.parse("http://i.gtimg.cn/qqlive/images/20150210/defult_user.png");

}
