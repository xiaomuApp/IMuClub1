package com.example.adapter;

import java.util.List;

import com.example.imuclub.R;
import com.example.model.TaskModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

@SuppressLint("ResourceAsColor")
public class ItemAdapter extends BaseAdapter {

	private List<TaskModel> mData;
	private Context context;

	public ItemAdapter(FragmentActivity context, List<TaskModel> mData) {
		this.context = context;
		this.mData = mData;
	}

	// 决定列表Item显示的个数
	@Override
	public int getCount() {
		return mData.size();
	}

	// 根据position获得对应的Item数据
	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	// 根据position获得对应的ItemID
	@Override
	public long getItemId(int position) {
		return position;
	}

	// 创建列表item视图
	@SuppressLint({ "ViewHolder", "ResourceAsColor" })
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = View.inflate(context, R.layout.list_item, null);
		TaskModel[] friend = new TaskModel[mData.size()];

		TextView tv_theme = (TextView) view.findViewById(R.id.tv_theme);
		for (int i = 0; i <= position; i++) {
			friend[position] = mData.get(position); // 依次获得列表项
			tv_theme.setText(friend[position].getTheme());
		}
		return view;
	}

}
