package com.tencent.streamshare.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by Administrator on 2016/8/21.
 */
public class LoginActivity extends Activity implements ResultListener{
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
		mUserText.setText("afeizaizhao");
		mPassText = (EditText)this.findViewById(R.id.QQ_password);
		mPassText.setText("123456");
		mLoginBtn = (Button) this.findViewById(R.id.btn_qqlogin);
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
	}

	@Override
	public void onFail(int Code, String Msg) {
		hideLoading();
		TastyToast.makeText(this, com.ihongqiqu.util.StringUtils.isEmpty(Msg) ?
				"错误码：" + Code : Msg, TastyToast.LENGTH_LONG, TastyToast.ERROR);
	}

	private void showLoading() {
		((MaskLoadingView)findViewById(R.id.loading)).showLoading();
	}

	private void hideLoading() {
		((MaskLoadingView)findViewById(R.id.loading)).dismissLoading();
	}
}
