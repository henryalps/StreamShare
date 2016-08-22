package com.tencent.streamshare.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sdsmdg.tastytoast.TastyToast;
import com.tencent.streamshare.MainActivity;
import com.tencent.streamshare.Model.User;
import com.tencent.streamshare.Network.GlobalNetworkHelper;
import com.tencent.streamshare.Network.Listener.ResultListener;
import com.tencent.streamshare.Network.RequestBuilder.UserLoginRequestBuilder;
import com.tencent.streamshare.Network.ResultAnalyser.UserLoginAnalyser;
import com.tencent.streamshare.R;
import com.tencent.streamshare.Utils.Constants;
import com.tencent.streamshare.View.MaskLoadingView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/8/21.
 */
public class LoginActivity extends Activity implements ResultListener{
	private final static String  TAG ="LoginActivity";
	private EditText mUserText,mPassText;
	private String mUserid,mPasswd;
	private Button mLoginBtn;
	public static void start(Activity activity) {
		LoginActivity loginActivity = new LoginActivity();
		Intent intent = new Intent();
		intent.setClass(activity, LoginActivity.class);
		activity.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mUserText = (EditText)this.findViewById(R.id.QQ_number);
		mUserText.setText("afeizaizhao"); // afeizaizhao
		mPassText = (EditText)this.findViewById(R.id.QQ_password);
		mPassText.setText("123456");
		mLoginBtn = (Button) this.findViewById(R.id.btn_qqlogin);
//		mLoginBtn.setFocusable(true);
//		mLoginBtn.setFocusableInTouchMode(true);
//		mLoginBtn.requestFocus();
		mLoginBtn.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v){
				mUserid = mUserText.getText().toString();
				mPasswd = mPassText.getText().toString();
				User.getInstance().setmId(mUserid);
				User.getInstance().setmPasswd(mPasswd);
				doLogin();
			}

		});
	}
	private void doLogin(){
		showLoading();
		new GlobalNetworkHelper(this, Constants.URL_USER_LOG_IN, Constants.REQ_TYPE_GET)
			.addRequest(new UserLoginRequestBuilder().build())
			.addAnalyser(new UserLoginAnalyser(this))
			.start();
	}

	@Override
	public void onSuccess(Object data) {
		hideLoading();
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void onFail(int Code, String Msg) {
		hideLoading();
//		TastyToast.makeText(this, com.ihongqiqu.util.StringUtils.isEmpty(Msg) ?
//				"错误码：" + Code : Msg, TastyToast.LENGTH_LONG, TastyToast.ERROR);
	}

	private void showLoading() {
		((MaskLoadingView)findViewById(R.id.loading)).showLoading();
	}

	private void hideLoading() {
		((MaskLoadingView)findViewById(R.id.loading)).dismissLoading();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			if (findViewById(R.id.loading).getVisibility()==View.VISIBLE) {
				GlobalNetworkHelper.stopAll();
				hideLoading();
			} else {
				for2Click();        //Double click the exit function call
			}
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
