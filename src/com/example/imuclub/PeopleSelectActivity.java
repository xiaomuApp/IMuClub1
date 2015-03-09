package com.example.imuclub;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.PeopleItemAdapter;
import com.example.model.PeopleModel;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class PeopleSelectActivity extends FragmentActivity {

	private Button btn_select_return;
	private PullToRefreshListView lv_select_list; // ����ˢ���б�

	private PeopleItemAdapter adapter; // �б���������
	private List<PeopleModel> list = new ArrayList<PeopleModel>(); // ����б�������

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_people_select);

		btn_select_return = (Button) findViewById(R.id.btn_select_return);
		lv_select_list = (PullToRefreshListView) findViewById(R.id.lv_select_list);

		btn_select_return.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		// ģ������
		PeopleModel peopleModel01 = new PeopleModel();
		peopleModel01.setName("��ѧ��");
		peopleModel01.setNickname("��׿���鳤");
		peopleModel01.setPosition("����");
		list.add(peopleModel01);

		PeopleModel peopleModel02 = new PeopleModel();
		peopleModel02.setName("������");
		peopleModel02.setNickname("��׿����Ա");
		peopleModel02.setPosition("����");
		list.add(peopleModel02);

		PeopleModel peopleModel03 = new PeopleModel();
		peopleModel03.setName("����־");
		peopleModel03.setNickname("��׿����Ա");
		peopleModel03.setPosition("����");
		list.add(peopleModel03);
		
		PeopleModel peopleModel04 = new PeopleModel();
		peopleModel04.setName("��ΰ��");
		peopleModel04.setNickname("��׿����Ա");
		peopleModel04.setPosition("����");
		list.add(peopleModel04);
		
		PeopleModel peopleModel05 = new PeopleModel();
		peopleModel05.setName("����");
		peopleModel05.setNickname("��׿����Ա");
		peopleModel05.setPosition("����");
		list.add(peopleModel05);
		
		PeopleModel peopleModel06 = new PeopleModel();
		peopleModel06.setName("�ܳ���");
		peopleModel06.setNickname("UI�鳤");
		peopleModel06.setPosition("����");
		list.add(peopleModel06);

		adapter = new PeopleItemAdapter(this, list);
		lv_select_list.setAdapter(adapter);

		// �б�������Ӧ�¼�
		lv_select_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				finish();
			}
		});

		// ������ListView������
		lv_select_list.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				new AsyncTask<Void, Void, Void>() {
					// ģ���������ȡʱ��
					@Override
					protected Void doInBackground(Void... params) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						return null;
					}

					// ִ���¼�
					protected void onPostExecute(Void result) {
						// ģ�����ݶ�ȡ
						PeopleModel peopleModel01 = new PeopleModel();
						peopleModel01.setName("�ܳ���");
						peopleModel01.setNickname("�����Č�˿");
						peopleModel01.setPosition("����");
						list.add(peopleModel01);

						PeopleModel peopleModel02 = new PeopleModel();
						peopleModel02.setName("��ٻ");
						peopleModel02.setNickname("��һվ���ټ�");
						peopleModel02.setPosition("����");
						list.add(peopleModel02);

						adapter = new PeopleItemAdapter(
								PeopleSelectActivity.this, list); // ��������������Ը���
						lv_select_list.setAdapter(adapter);

						lv_select_list.onRefreshComplete();
					};
				}.execute();
			}
		});
	}
}
