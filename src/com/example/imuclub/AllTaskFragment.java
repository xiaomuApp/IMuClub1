package com.example.imuclub;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.ItemAdapter;
import com.example.model.TaskModel;
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

public class AllTaskFragment extends Fragment {

	private PullToRefreshListView lv_alltask; // ����ˢ���б�
	private ItemAdapter adapter; // �б���������
	private Button btn_add; // �����Ŀ��ť

	private List<TaskModel> list = new ArrayList<TaskModel>(); // ����б�������

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.tab_alltask, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		lv_alltask = (PullToRefreshListView) getActivity().findViewById(
				R.id.lv_alltask);
		btn_add = (Button) getActivity().findViewById(R.id.btn_add);

		adapter = new ItemAdapter(getActivity(), list);
		lv_alltask.setAdapter(adapter);

		btn_add.setOnClickListener(new OnClickListener() { // �����Ӱ�ť����µ�δ�༭���б���
			@Override
			public void onClick(View v) {
				TaskModel taskModel = new TaskModel();
				taskModel.setTheme("δ�༭");
				taskModel.setDeadline("δ�༭");
				taskModel.setIsdeclare(false);
				taskModel.setIscomplete(false);
				list.add(taskModel);
				adapter = new ItemAdapter(getActivity(), list); // ��������������Ը���
				lv_alltask.setAdapter(adapter);
			}
		});

		// �б�������Ӧ�¼�
		lv_alltask.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

			}
		});

		// ������ListView������
		lv_alltask.setOnRefreshListener(new OnRefreshListener<ListView>() {
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
						TaskModel taskModel01 = new TaskModel();
						taskModel01.setTheme("Ů����");
						taskModel01.setDeadline("2014/3/2");
						taskModel01.setIsdeclare(true);
						taskModel01.setIscomplete(true);
						list.add(taskModel01);

						TaskModel taskModel02 = new TaskModel();
						taskModel02.setTheme("��ѧ");
						taskModel02.setDeadline("2015/3/1");
						taskModel02.setIsdeclare(true);
						taskModel02.setIscomplete(false);
						list.add(taskModel02);

						adapter = new ItemAdapter(getActivity(), list); // ��������������Ը���
						lv_alltask.setAdapter(adapter);

						lv_alltask.onRefreshComplete();
					};
				}.execute();
			}
		});
	}
}
