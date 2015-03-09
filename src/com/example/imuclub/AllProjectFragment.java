package com.example.imuclub;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.ItemAdapter;
import com.example.model.TaskModel;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

import android.content.Intent;
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

public class AllProjectFragment extends Fragment {

	private PullToRefreshListView lv_allproject; // ����ˢ���б�
	private ItemAdapter adapter; // �б���������
	private Button btn_add; // �����Ŀ��ť

	private List<TaskModel> list = new ArrayList<TaskModel>(); // ����б�������

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.tab_allproject, container, false);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		lv_allproject = (PullToRefreshListView) getActivity().findViewById(
				R.id.lv_allproject);
		btn_add = (Button) getActivity().findViewById(R.id.btn_add);

		adapter = new ItemAdapter(getActivity(), list);
		lv_allproject.setAdapter(adapter);

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
				lv_allproject.setAdapter(adapter);
			}
		});

		// �б�������Ӧ�¼�
		lv_allproject.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.setClassName("com.example.imuclub",
						"com.example.imuclub.TaskCheckOrEditActivity");
				intent.putExtra("project_theme", list.get(position - 1)
						.getTheme());
				intent.putExtra("project_deadline", list.get(position - 1)
						.getDeadline());
				intent.putExtra("project_iscompleted", list.get(position - 1)
						.isIscomplete());
				startActivity(intent);
			}
		});

		// ������ListView������
		lv_allproject.setOnRefreshListener(new OnRefreshListener<ListView>() {
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
						
						taskModel01.setTheme("ӭ�����");
						taskModel01.setDeadline("2014/10/17");
						taskModel01.setIsdeclare(true);
						taskModel01.setIscomplete(true);
						list.add(taskModel01);
						
						TaskModel taskModel02 = new TaskModel();
						taskModel02.setTheme("Ů����");
						taskModel02.setDeadline("2015/3/2");
						taskModel02.setIsdeclare(true);
						taskModel02.setIscomplete(true);
						list.add(taskModel02);

						TaskModel taskModel03 = new TaskModel();
						taskModel03.setTheme("��������");
						taskModel03.setDeadline("2015/4/15");
						taskModel03.setIsdeclare(true);
						taskModel03.setIscomplete(false);
						list.add(taskModel03);
						
						TaskModel taskModel04 = new TaskModel();
						taskModel04.setTheme("�������");
						taskModel04.setDeadline("2015/6/20");
						taskModel04.setIsdeclare(true);
						taskModel04.setIscomplete(false);
						list.add(taskModel04);

						adapter = new ItemAdapter(getActivity(), list); // ��������������Ը���
						lv_allproject.setAdapter(adapter);

						lv_allproject.onRefreshComplete();
					};
				}.execute();
			}
		});
	}
}
