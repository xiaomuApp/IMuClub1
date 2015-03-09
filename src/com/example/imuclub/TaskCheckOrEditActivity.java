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

	// �������ؼ�
	private Button btn_return; // ���ذ�ť
	private Button btn_edit; // �༭��ť

	// ���ݿؼ�
	private TextView tv_check_title; // ����
	private TextView tv_general_theme; // ȫ��������
	private EditText et_item_theme; // ����
	private EditText et_item_deadline; // ��ֹʱ��
	private EditText et_item_context; // ��������
	private ImageView iv_iscompleted; // �Ƿ���ɱ�־
	private Button btn_declare; // ������ť

	// �������
	private boolean IsEdit = false; // ��־�Ƿ��ڱ༭״̬

	// ��Ա�ؼ�
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

		// ��ʼ��View
		btn_return = (Button) findViewById(R.id.btn_return); // ���ذ�ť
		btn_edit = (Button) findViewById(R.id.btn_edit); // �༭��ť
		tv_check_title = (TextView) findViewById(R.id.tv_check_title); // ����
		tv_general_theme = (TextView) findViewById(R.id.tv_general_theme); // ȫ��������
		et_item_theme = (EditText) findViewById(R.id.et_item_theme); // ����
		et_item_deadline = (EditText) findViewById(R.id.et_item_deadline); // ��ֹʱ��
		et_item_context = (EditText) findViewById(R.id.et_item_context); // �����
		iv_iscompleted = (ImageView) findViewById(R.id.iv_iscompleted); // �Ƿ���ɱ�־
		btn_declare = (Button) findViewById(R.id.btn_declare); // ������ť

		// ��ʼ����Ա�ؼ�
		iv_people01 = (ImageView) findViewById(R.id.iv_people01);
		iv_people02 = (ImageView) findViewById(R.id.iv_people02);
		iv_people03 = (ImageView) findViewById(R.id.iv_people03);
		iv_people04 = (ImageView) findViewById(R.id.iv_people04);
		iv_people05 = (ImageView) findViewById(R.id.iv_people05);
		iv_people06 = (ImageView) findViewById(R.id.iv_people06);

		// ��ò�����Activity����
		Bundle bundle = getIntent().getExtras();
		tv_check_title.setText(bundle.getString("project_theme")); // ���ñ���
		tv_general_theme.setText(bundle.getString("project_theme")); // ���ñ���
		et_item_theme.setText(bundle.getString("project_theme")); // ���ñ���
		et_item_deadline.setText(bundle.getString("project_deadline")); // ���ý�ֹʱ��

		// ���������Ƿ���ɱ�־
		if (bundle.getBoolean("project_iscompleted"))
			iv_iscompleted.setBackgroundResource(R.drawable.item_completed);
		else
			iv_iscompleted.setBackgroundResource(R.drawable.item_notcompleted);

		// ���õ�������¼�
		btn_return.setOnClickListener(this);
		btn_edit.setOnClickListener(this);
		iv_people01.setOnClickListener(this);
		iv_people02.setOnClickListener(this);
		iv_people03.setOnClickListener(this);
		iv_people04.setOnClickListener(this);
		iv_people05.setOnClickListener(this);
		iv_people06.setOnClickListener(this);
		btn_declare.setOnClickListener(this);

		// ���ýӴ��¼�
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
			// ���ÿɱ༭��
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
			String text = "��Ŀ\"" + tv_check_title.getText() + "\"�����ɹ�";
			Toast.makeText(this, text, Toast.LENGTH_LONG).show();
			finish();
			break;
		}
	}

}
