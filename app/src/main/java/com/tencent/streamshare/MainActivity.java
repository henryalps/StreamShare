package com.tencent.streamshare;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.ihongqiqu.util.StringUtils;
import com.tencent.streamshare.Activity.LoginActivity;
import com.tencent.streamshare.Activity.PlayerActivity;
import com.tencent.streamshare.Utils.QRCodeUtil;
import com.tencent.streamshare.View.StreamUrlDialog;

public class MainActivity extends AppCompatActivity implements StreamUrlDialog.PositiveBtnListener{

    private TextView mUserName;
    private SimpleDraweeView mUserProfile,mIsVip;
    private Button Qrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        mUserName =(TextView) this.findViewById(R.id.user_name);
        mUserProfile =(SimpleDraweeView) this.findViewById(R.id.user_photo);
        mIsVip =(SimpleDraweeView) this.findViewById(R.id.user_isvip);
        Qrcode = (Button)this.findViewById(R.id.titlebar_camera) ;
        Qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
		integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
		integrator.initiateScan();
            }
            });

        inittab();

        ImageView ivQR= (ImageView)this.findViewById(R.id.QrcodeTest);

        BitmapDrawable bd = (BitmapDrawable) getResources().getDrawable(R.drawable.littleyello);

        ivQR.setImageBitmap(QRCodeUtil.createImage("CESH1I123123",600,600,null));

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
    private void inittab(){
        //设置相关信息

        Uri uri = Uri.parse("http://img4.imgtn.bdimg.com/it/u=1745615891,1988783183&fm=21&gp=0.jpg");
        mUserProfile.setImageURI(uri);
        if(true)//是不是ｖｉｐ
         {
             uri = Uri.parse("http://img1.114pifa.com/2045/t7TH9GDYG_1400207302.jpg");
             mIsVip.setImageURI(uri);
         }else{
            uri = Uri.parse("http://i.gtimg.cn/qqlive/images/20150210/defult_user.png");
            mIsVip.setImageURI(uri);
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            Toast.makeText(getApplicationContext(),scanResult.getContents(),
                            Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "解析失败",
                            Toast.LENGTH_SHORT).show();
        }

    }
}
