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

	private PullToRefreshListView lv_alltask; // 下拉刷新列表
	private ItemAdapter adapter; // 列表现适配器
	private Button btn_add; // 添加项目按钮

	private List<TaskModel> list = new ArrayList<TaskModel>(); // 存放列表项内容

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

		btn_add.setOnClickListener(new OnClickListener() { // 点击添加按钮添加新的未编辑的列表项
			@Override
			public void onClick(View v) {
				TaskModel taskModel = new TaskModel();
				taskModel.setTheme("未编辑");
				taskModel.setDeadline("未编辑");
				taskModel.setIsdeclare(false);
				taskModel.setIscomplete(false);
				list.add(taskModel);
				adapter = new ItemAdapter(getActivity(), list); // 重新添加适配器以更新
				lv_alltask.setAdapter(adapter);
			}
		});

		// 列表项点击响应事件
		lv_alltask.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

			}
		});

		// 给更新ListView监听器
		lv_alltask.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				new AsyncTask<Void, Void, Void>() {
					// 模拟服务器读取时间
					@Override
					protected Void doInBackground(Void... params) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						return null;
					}

					// 执行事件
					protected void onPostExecute(Void result) {

						// 模仿数据读取
						TaskModel taskModel01 = new TaskModel();
						taskModel01.setTheme("女生节");
						taskModel01.setDeadline("2014/3/2");
						taskModel01.setIsdeclare(true);
						taskModel01.setIscomplete(true);
						list.add(taskModel01);

						TaskModel taskModel02 = new TaskModel();
						taskModel02.setTheme("开学");
						taskModel02.setDeadline("2015/3/1");
						taskModel02.setIsdeclare(true);
						taskModel02.setIscomplete(false);
						list.add(taskModel02);

						adapter = new ItemAdapter(getActivity(), list); // 重新添加适配器以更新
						lv_alltask.setAdapter(adapter);

						lv_alltask.onRefreshComplete();
					};
				}.execute();
			}
		});
	}
}
