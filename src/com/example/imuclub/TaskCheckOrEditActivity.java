package com.example.imuclub;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TaskCheckOrEditActivity extends Activity implements
		OnClickListener, OnTouchListener {

	// 标题栏控件
	private Button btn_return; // 返回按钮
	private Button btn_edit; // 编辑按钮

	// 内容控件
	private TextView tv_check_title; // 标题
	private TextView tv_general_theme; // 全概栏主题
	private EditText et_item_theme; // 主题
	private EditText et_item_deadline; // 截止时间
	private EditText et_item_context; // 任务内容
	private ImageView iv_iscompleted; // 是否完成标志
	private Button btn_declare; // 发布按钮

	// 程序变量
	private boolean IsEdit = false; // 标志是否处于编辑状态

	// 人员控件
	private ImageView iv_people01;
	private ImageView iv_people02;
	private ImageView iv_people03;
	private ImageView iv_people04;
	private ImageView iv_people05;
	private ImageView iv_people06;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_check_or_edit);

		// 初始化View
		btn_return = (Button) findViewById(R.id.btn_return); // 返回按钮
		btn_edit = (Button) findViewById(R.id.btn_edit); // 编辑按钮
		tv_check_title = (TextView) findViewById(R.id.tv_check_title); // 标题
		tv_general_theme = (TextView) findViewById(R.id.tv_general_theme); // 全概栏主题
		et_item_theme = (EditText) findViewById(R.id.et_item_theme); // 主题
		et_item_deadline = (EditText) findViewById(R.id.et_item_deadline); // 截止时间
		et_item_context = (EditText) findViewById(R.id.et_item_context); // 活动内容
		iv_iscompleted = (ImageView) findViewById(R.id.iv_iscompleted); // 是否完成标志
		btn_declare = (Button) findViewById(R.id.btn_declare); // 发布按钮

		// 初始化人员控件
		iv_people01 = (ImageView) findViewById(R.id.iv_people01);
		iv_people02 = (ImageView) findViewById(R.id.iv_people02);
		iv_people03 = (ImageView) findViewById(R.id.iv_people03);
		iv_people04 = (ImageView) findViewById(R.id.iv_people04);
		iv_people05 = (ImageView) findViewById(R.id.iv_people05);
		iv_people06 = (ImageView) findViewById(R.id.iv_people06);

		// 获得并设置Activity内容
		Bundle bundle = getIntent().getExtras();
		tv_check_title.setText(bundle.getString("project_theme")); // 设置标题
		tv_general_theme.setText(bundle.getString("project_theme")); // 设置标题
		et_item_theme.setText(bundle.getString("project_theme")); // 设置标题
		et_item_deadline.setText(bundle.getString("project_deadline")); // 设置截止时间

		// 设置任务是否完成标志
		if (bundle.getBoolean("project_iscompleted"))
			iv_iscompleted.setBackgroundResource(R.drawable.item_completed);
		else
			iv_iscompleted.setBackgroundResource(R.drawable.item_notcompleted);

		// 设置点击监听事件
		btn_return.setOnClickListener(this);
		btn_edit.setOnClickListener(this);
		iv_people01.setOnClickListener(this);
		iv_people02.setOnClickListener(this);
		iv_people03.setOnClickListener(this);
		iv_people04.setOnClickListener(this);
		iv_people05.setOnClickListener(this);
		iv_people06.setOnClickListener(this);
		btn_declare.setOnClickListener(this);

		// 设置接触事件
		btn_return.setOnTouchListener(this);
		btn_edit.setOnTouchListener(this);
		btn_declare.setOnTouchListener(this);
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getAction();
		if (action == MotionEvent.ACTION_DOWN) {
			switch (v.getId()) {
			case R.id.btn_return:
				btn_return.setBackgroundResource(R.drawable.title_return_down);
			case R.id.btn_edit:
				btn_edit.setBackgroundResource(R.drawable.title_edit_down);
			case R.id.btn_declare:
				btn_declare.setBackgroundResource(R.drawable.btn_declare_down);
			}
		} else if (action == MotionEvent.ACTION_UP) {
			switch (v.getId()) {
			case R.id.btn_return:
				btn_return.setBackgroundResource(R.drawable.title_return);
			case R.id.btn_edit:
				btn_edit.setBackgroundResource(R.drawable.title_edit);
			case R.id.btn_declare:
				btn_declare.setBackgroundResource(R.drawable.btn_declare);
			}
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_return:
			finish();
			break;
		case R.id.btn_edit:
			// 设置可编辑性
			if (IsEdit == false) {
				et_item_theme.setEnabled(true);
				et_item_deadline.setEnabled(true);
				et_item_context.setEnabled(true);
				btn_edit.setBackgroundResource(R.drawable.title_save);
				IsEdit = true;
			} else {
				et_item_theme.setEnabled(false);
				et_item_deadline.setEnabled(false);
				et_item_context.setEnabled(false);
				btn_edit.setBackgroundResource(R.drawable.title_edit);
				IsEdit = false;
			}
			break;
		case R.id.iv_people01:
		case R.id.iv_people02:
		case R.id.iv_people03:
		case R.id.iv_people04:
		case R.id.iv_people05:
		case R.id.iv_people06:
			Intent intent = new Intent();
			intent.setClassName("com.example.imuclub",
					"com.example.imuclub.PeopleSelectActivity");
			startActivity(intent);
			break;
		case R.id.btn_declare:
			String text = "项目\"" + tv_check_title.getText() + "\"发布成功";
			Toast.makeText(this, text, Toast.LENGTH_LONG).show();
			finish();
			break;
		}
	}

}
