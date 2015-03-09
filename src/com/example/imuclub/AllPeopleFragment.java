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
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class AllPeopleFragment extends Fragment {

	private PullToRefreshListView lv_allpeople; // ����ˢ���б�
	private PeopleItemAdapter adapter; // �б���������
	private Button btn_add; // �����Ŀ��ť

	private List<PeopleModel> list = new ArrayList<PeopleModel>(); // ����б�������

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.tab_allpeople, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		lv_allpeople = (PullToRefreshListView) getActivity().findViewById(
				R.id.lv_allpeople);
		btn_add = (Button) getActivity().findViewById(R.id.btn_add);

		adapter = new PeopleItemAdapter(getActivity(), list);
		lv_allpeople.setAdapter(adapter);

		btn_add.setOnClickListener(new OnClickListener() { // �����Ӱ�ť����µ�δ�༭���б���
			@Override
			public void onClick(View v) {
				PeopleModel peopleModel = new PeopleModel();
				peopleModel.setName("δ�༭");
				peopleModel.setNickname("Сľ�û�");
				peopleModel.setPosition("δ�༭");
				list.add(peopleModel);
				adapter = new PeopleItemAdapter(getActivity(), list); // ��������������Ը���
				lv_allpeople.setAdapter(adapter);
			}
		});

		// �б�������Ӧ�¼�
		lv_allpeople.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

			}
		});

		// ������ListView������
		lv_allpeople.setOnRefreshListener(new OnRefreshListener<ListView>() {
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
						
						adapter = new PeopleItemAdapter(getActivity(), list); // ��������������Ը���
						lv_allpeople.setAdapter(adapter);

						lv_allpeople.onRefreshComplete();
					};
				}.execute();
			}
		});
	}
}
