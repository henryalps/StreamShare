package com.tencent.streamshare;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.ihongqiqu.util.LogUtils;
import com.ihongqiqu.util.StringUtils;
import com.sdsmdg.tastytoast.TastyToast;
import com.tencent.streamshare.Activity.LoginActivity;
import com.tencent.streamshare.Activity.PlayerActivity;
import com.tencent.streamshare.Adapter.SteamListAdapter;
import com.tencent.streamshare.Model.StreamInfo;
import com.tencent.streamshare.Model.User;
import com.tencent.streamshare.Network.GlobalNetworkHelper;
import com.tencent.streamshare.Network.Listener.ResultListener;
import com.tencent.streamshare.Network.RequestBuilder.GetStreamAddressRequestBuilder;
import com.tencent.streamshare.Network.RequestBuilder.StreamListRequestBuilder;
import com.tencent.streamshare.Network.ResultAnalyser.GetStreamAddressAnalyser;
import com.tencent.streamshare.Network.ResultAnalyser.StreamListResultAnalyser;
import com.tencent.streamshare.Utils.Constants;
import com.tencent.streamshare.View.StreamUrlDialog;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements StreamUrlDialog.PositiveBtnListener, ResultListener{

    private TextView mUserName;
    private SimpleDraweeView mUserProfile,mIsVip;
    private Button Qrcode;
    private ListView mSteamList;
    private SteamListAdapter mSteamListAdapter;
    private Button Exit;
    private Timer mTimer;
    private TimerTask mTimerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        mUserName =(TextView) this.findViewById(R.id.user_name);
        mUserProfile =(SimpleDraweeView) this.findViewById(R.id.user_photo);
        mIsVip =(SimpleDraweeView) this.findViewById(R.id.user_isvip);
        Qrcode = (Button)this.findViewById(R.id.titlebar_camera) ;
        mSteamList = (ListView)this.findViewById(R.id.steam_list);
        Exit = (Button)this.findViewById(R.id.titlebar_exit);
       Qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
		integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
		integrator.initiateScan();
            }
            });
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.start(MainActivity.this);
                finish();
            }
        });

        initView();

        ImageView ivQR= (ImageView)this.findViewById(R.id.QrcodeTest);

        BitmapDrawable bd = (BitmapDrawable) getResources().getDrawable(R.drawable.littleyello);

      //  ivQR.setImageBitmap(QRCodeUtil.createImage("CESH1I123123",600,600,null));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StreamUrlDialog.show(MainActivity.this, MainActivity.this);
            }
        });
    }




    @Override
    public void onBtnClicked(String streamAddress) {
        if (!StringUtils.isEmpty(streamAddress)) {
            Intent intent = new Intent();
            intent.setClass(this, PlayerActivity.class);
            intent.putExtra(PlayerActivity.STREAM_URL_TAG, streamAddress);
            startActivity(intent);
        }
    }
    private void initView(){
        //设置相关信息

        mUserName.setText(User.getInstance().getmNickName());
        Uri uri = Uri.parse(User.getInstance().getmHeadImagUrl());
        mUserProfile.setImageURI(uri);
        if(User.getInstance().ismIsVip())//是不是ｖｉｐ
         {
             mIsVip.setImageURI(Constants.URI_IMG_VIP);
         }else{
            mIsVip.setImageURI(Constants.URI_IMG_ORDINARY);
        }
    }

    private void startQueryList() {
        if (mTimer == null) {
            mTimer = new Timer();
        }
        if (mTimerTask == null) {
            final GlobalNetworkHelper helper = new GlobalNetworkHelper(this, Constants.URL_LIVE_STREAM_INFO, Constants.REQ_TYPE_GET)
                    .addRequest(new StreamListRequestBuilder().build())
                    .addAnalyser(new StreamListResultAnalyser(this));
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    helper.start();
                }
            };
        }
        mTimer.schedule(mTimerTask, 0, 1000);
    }

    private void stopQueryList() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            mTimerTask = null;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            String barcodeStr = scanResult.getContents();
            if (!StringUtils.isEmpty(barcodeStr) && barcodeStr.startsWith(Constants.STR_PROTOCOL_HEADER)) {
                final String token =  barcodeStr.substring(Constants.STR_PROTOCOL_HEADER.length(), barcodeStr.length());
                new GlobalNetworkHelper(this, Constants.URL_ATTAIN_STREAM_ADDRESS)
                        .addRequest(new GetStreamAddressRequestBuilder(token).build())
                        .addAnalyser(new GetStreamAddressAnalyser(new ResultListener() {
                            @Override
                            public void onSuccess(Object data) {
                                onBtnClicked(User.getInstance().getmCurrentStream().getmUrl());
                            }

                            @Override
                            public void onFail(int Code, String Msg) {

                            }
                        })).start();
            } else {
                TastyToast.makeText(this,  "二维码不合法", TastyToast.LENGTH_LONG, TastyToast.ERROR);
            }
        }else{
            Toast.makeText(getApplicationContext(), "解析失败",
                            Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSuccess(Object data) {
        LogUtils.d("henryrhe", "******A stream list query has been successfully performed and sent to front");
        if (mSteamListAdapter == null) {
            mSteamListAdapter = new SteamListAdapter(this, (ArrayList<StreamInfo>) data);
            mSteamList.setAdapter(mSteamListAdapter);
            mSteamListAdapter.notifyDataSetChanged();
        } else {
            mSteamListAdapter.updateList((ArrayList<StreamInfo>) data);
            mSteamListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFail(int Code, String Msg) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        stopQueryList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startQueryList();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            for2Click();        //Double click the exit function call
        }
        return false;
    }
    private static Boolean isExit = false;

    private void for2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // Ready to quit
            TastyToast.makeText(this, "再点返回键退出", TastyToast.LENGTH_LONG, TastyToast.INFO);
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // Cancel to exit
                }
            }, 2000); // 2 seconds

        } else {
            finish();
            System.exit(0);//finish();
            int pid = android.os.Process.myPid();
            android.os.Process.killProcess(pid);
        }
    }

}
