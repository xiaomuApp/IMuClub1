package com.example.imuclub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private LinearLayout ll_loading_logo;
	private LinearLayout ll_loginlayout;
	private Button btn_login;
	
	private TextView tv_tip_user;        //¡°ÕËºÅ¡±ÎÄ±¾
	private TextView tv_tip_pin;          //¡°ÃÜÂë¡±ÎÄ±¾
	private EditText et_login_user;        //¡°ÕËºÅ¡±±à¼­¿ò
	private EditText et_login_pin;          //¡°ÃÜÂë¡±±à¼­¿ò
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// È¥³ýtitle
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// È¥µô×´Ì¬À¸
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_main);

		//logo²¼¾Ö
		ll_loading_logo = (LinearLayout) findViewById(R.id.ll_loading_logo);
		
		//login²¼¾Ö
		ll_loginlayout = (LinearLayout) findViewById(R.id.ll_loginlayout);
		
		//µÇÂ¼°´Å¥
		btn_login = (Button) findViewById(R.id.btn_login);
		
		//»ñµÃÕËºÅ±à¼­¿òID
		tv_tip_user=(TextView) findViewById(R.id.tv_tip_user);
		et_login_user=(EditText) findViewById(R.id.et_login_user);
		
		//»ñµÃÃÜÂë±à¼­¿òID
		tv_tip_pin=(TextView) findViewById(R.id.tv_tip_pin);
		et_login_pin=(EditText) findViewById(R.id.et_login_pin);
				
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				ll_loading_logo.setVisibility(View.INVISIBLE);
				ll_loginlayout.setVisibility(View.VISIBLE);
			}
		}, 3000);
		
		btn_login.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, IMuClubActivity.class);
				startActivity(intent);
				MainActivity.this.finish();
			}
		});
		
		//ÉèÖÃ±à¼­¿òÎÄ±¾¼àÌýÆ÷
		et_login_user.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				tv_tip_user.setVisibility(View.VISIBLE);
				}
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				tv_tip_user.setVisibility(View.INVISIBLE);
				}
			@Override
			public void afterTextChanged(Editable s) {
				tv_tip_user.setVisibility(View.INVISIBLE);
				}
			});
		et_login_pin.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				tv_tip_pin.setVisibility(View.VISIBLE);
				}
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				tv_tip_pin.setVisibility(View.INVISIBLE);
				}
			
			@Override
			public void afterTextChanged(Editable s) {
				tv_tip_pin.setVisibility(View.INVISIBLE);
				}
			});
				
	}
}
