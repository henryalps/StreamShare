package com.tencent.streamshare.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.tencent.streamshare.MainActivity;
import com.tencent.streamshare.R;

/**
 * Created by Administrator on 2016/8/21.
 */
public class LoginActivity extends Activity {
	private final static String  TAG ="LoginActivity";
	private EditText mUserText,mPassText;
	private String mUserid,mPasswd;
	private Button mLoginBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mUserText = (EditText)this.findViewById(R.id.QQ_number);
		mPassText = (EditText)this.findViewById(R.id.QQ_password);
		mLoginBtn = (Button) this.findViewById(R.id.btn_qqlogin);
		mLoginBtn.setFocusable(true);
		mLoginBtn.setFocusableInTouchMode(true);
		mLoginBtn.requestFocus();
		mLoginBtn.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v){
				mUserid = mUserText.getText().toString();
				mPasswd = mPassText.getText().toString();
				doLogin(mUserid,mPasswd);
			}

		});
	}
	private void doLogin(String userid,String passwd){

		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
//		IntentIntegrator integrator = new IntentIntegrator(LoginActivity.this);
//		integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
//		integrator.setOrientationLocked(false);
//		integrator.initiateScan();

	}


}
