package com.example.adapter;

import java.util.List;

import com.example.imuclub.R;
import com.example.model.PeopleModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

@SuppressLint("ResourceAsColor")
public class PeopleItemAdapter extends BaseAdapter{

	private List<PeopleModel> mData;
	private Context context;
	
	public PeopleItemAdapter(FragmentActivity context, List<PeopleModel> mData) {
		this.context=context;
		this.mData=mData;
	}

	//�����б�Item��ʾ�ĸ���
	@Override
	public int getCount() {
		return mData.size();
	}

	//����position��ö�Ӧ��Item����
	@Override
	public Object getItem(int position) {
		return mData.get(position);
		}
	
	//����position��ö�Ӧ��ItemID
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	//�����б�item��ͼ
	@SuppressLint({ "ViewHolder", "ResourceAsColor" })
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view=View.inflate(context, R.layout.list_people_item, null);
		PeopleModel []friend=new PeopleModel[mData.size()];
		
		TextView tv_people_name = (TextView) view.findViewById(R.id.tv_people_name);
		TextView tv_user_name = (TextView) view.findViewById(R.id.tv_user_name);
		TextView tv_people_position = (TextView) view.findViewById(R.id.tv_people_position);
		for (int i=0;i<=position;i++){
			friend[position]=mData.get(position);      //���λ���б���
			tv_people_name.setText(friend[position].getName());
			tv_user_name.setText(friend[position].getNickname());
			tv_people_position.setText(friend[position].getPosition());
		}
		return view;
	}

}
