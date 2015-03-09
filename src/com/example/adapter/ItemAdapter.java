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

	// �����б�Item��ʾ�ĸ���
	@Override
	public int getCount() {
		return mData.size();
	}

	// ����position��ö�Ӧ��Item����
	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	// ����position��ö�Ӧ��ItemID
	@Override
	public long getItemId(int position) {
		return position;
	}

	// �����б�item��ͼ
	@SuppressLint({ "ViewHolder", "ResourceAsColor" })
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = View.inflate(context, R.layout.list_item, null);
		TaskModel[] friend = new TaskModel[mData.size()];

		TextView tv_theme = (TextView) view.findViewById(R.id.tv_theme);
		for (int i = 0; i <= position; i++) {
			friend[position] = mData.get(position); // ���λ���б���
			tv_theme.setText(friend[position].getTheme());
		}
		return view;
	}

}
