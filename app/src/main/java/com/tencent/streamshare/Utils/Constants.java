package com.tencent.streamshare.Utils;

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
    public final static String URL_EXIT_STREAM =
            "http://1.tv.ptyg.gitv.tv/i-tvbin/qtv_video/omgtrain/user_exit_stream";

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
}
