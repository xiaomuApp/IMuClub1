package com.example.imuclub;

import java.util.ArrayList;
import java.util.List;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("ResourceAsColor")
public class IMuClubActivity extends FragmentActivity implements
		OnTouchListener, OnClickListener, OnPageChangeListener {

	// ������ؼ�
	private Button btn_menu; // �˵���ť
	private Button btn_add; // �½���ť
	private TextView tv_title; // ����
	private ViewPager vp_myproject; // �ҵ���Ŀ����ҳ
	private ViewPager vp_mytask; // �ҵ����񻬶�ҳ
	private ViewPager vp_peoplelist; // ��Ա�б���ҳ

	// ������tab�ؼ�
	private TextView tv_allproject; // ȫ����Ŀ
	private TextView tv_takepartByme; // �Ҳμӵ�
	private TextView tv_buildByme; // �Ҵ�����
	private ImageView iv_tabline; // tab�»���

	// tab����
	Fragment tab01;
	Fragment tab02;
	Fragment tab03;

	// ���˵��ؼ�
	private Button ll_myproject; // �ҵ���Ŀ
	private Button ll_mytask; // �ҵ�����
	private Button ll_peoplelist; // ��Ա�б�

	// �������
	private SlidingMenu slidingmenu; // �����˵�
	private FragmentPagerAdapter mAdapter; // FragmentPager������
	private List<Fragment> mDatas; // ����Fragment
	private int mScreen1_3; // ��¼��Ļ������֮һ����
	private int mCurrentPageIndex; // ��¼��ǰҳ���±�

	// ������־
	private int selectItem = 0; // 0����ѡ���ҵ���Ŀ����1�����ҵ����񡱣�2������Ա�б�

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imuclub);

		initMenu(); // ��ʼ��Menu
		initView(); // ��ʼ��View
		initTabline(); // ��ʼ��tabline

	}

	// ��ʼ��Menu
	private void initMenu() {
		slidingmenu = new SlidingMenu(this);
		slidingmenu.setMode(SlidingMenu.LEFT); // ���ģʽ
		slidingmenu.setBehindOffsetRes(R.dimen.sliding_menu_offset);
		slidingmenu.setTouchModeAbove(SlidingMenu.LEFT); // ��߿ɴ���
		slidingmenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		slidingmenu.setMenu(R.layout.slidingmenu);
	}

	// ��ʼ��tabline
	private void initTabline() {
		iv_tabline = (ImageView) findViewById(R.id.iv_tabline); // tablineͼ�οؼ�

		Display display = getWindow().getWindowManager().getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics();
		display.getMetrics(outMetrics);
		mScreen1_3 = outMetrics.widthPixels / 3;
		LayoutParams lp = iv_tabline.getLayoutParams();
		lp.width = mScreen1_3;
		iv_tabline.setLayoutParams(lp);
	}

	// ��ʼ��View
	private void initView() {
		// ������ؼ�
		btn_menu = (Button) findViewById(R.id.btn_menu); // �˵���ť
		btn_add = (Button) findViewById(R.id.btn_add); // ��Ӱ�ť
		tv_title = (TextView) findViewById(R.id.tv_title); // ����
		vp_myproject = (ViewPager) findViewById(R.id.vp_myproject); // �ҵ���Ŀ����ҳ��
		vp_mytask = (ViewPager) findViewById(R.id.vp_mytask); // �ҵ����񻬶�ҳ
		vp_peoplelist = (ViewPager) findViewById(R.id.vp_peoplelist); // ��Ա�б���ҳ

		// ������tab�ؼ�
		tv_allproject = (TextView) findViewById(R.id.tv_allproject); // ȫ����Ŀ
		tv_takepartByme = (TextView) findViewById(R.id.tv_takepartByme); // �Ҳμӵ�
		tv_buildByme = (TextView) findViewById(R.id.tv_buildByme); // �Ҵ�����
		iv_tabline = (ImageView) findViewById(R.id.iv_tabline); // tab�»���

		// ���˵��ؼ�
		ll_myproject = (Button) findViewById(R.id.ll_myproject); // �ҵ���Ŀ
		ll_mytask = (Button) findViewById(R.id.ll_mytask); // �ҵ�����
		ll_peoplelist = (Button) findViewById(R.id.ll_peoplelist); // ��Ա�б�

		// �������

		initFragment(); // ��ʼ��Fragment

		// ���ҳ�����������
		vp_myproject.setOnPageChangeListener(this);
		vp_mytask.setOnPageChangeListener(this);
		vp_peoplelist.setOnPageChangeListener(this);

		// ���ð��¼�����
		btn_menu.setOnTouchListener(this);
		btn_add.setOnTouchListener(this);
		ll_myproject.setOnTouchListener(this);
		ll_mytask.setOnTouchListener(this);
		ll_peoplelist.setOnTouchListener(this);

		// ���õ��������
		btn_menu.setOnClickListener(this);
		ll_myproject.setOnClickListener(this);
		ll_mytask.setOnClickListener(this);
		ll_peoplelist.setOnClickListener(this);
	}

	// ���ݲ�ͬѡ���ʼ��Fragment
	private void initFragment() {
		switch (selectItem) {
		case 0: // �ҵ���Ŀ
			mDatas = new ArrayList<Fragment>();
			tab01 = new AllProjectFragment();
			tab02 = new TakePartByMeFragment();
			tab03 = new BuildByMeFragment();
			mDatas.add(tab01);
			mDatas.add(tab02);
			mDatas.add(tab03);
			mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
				@Override
				public int getCount() {
					return mDatas.size();
				}

				@Override
				public Fragment getItem(int position) {
					return mDatas.get(position);
				}
			};
			vp_myproject.setAdapter(mAdapter);
			break;

		case 1:
			mDatas = new ArrayList<Fragment>();
			tab01 = new AllTaskFragment();
			tab02 = new CompletedTaskFragment();
			tab03 = new NotCompletedTaskFragment();
			mDatas.add(tab01);
			mDatas.add(tab02);
			mDatas.add(tab03);
			mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
				@Override
				public int getCount() {
					return mDatas.size();
				}

				@Override
				public Fragment getItem(int position) {
					return mDatas.get(position);
				}
			};
			vp_mytask.setAdapter(mAdapter);
			break;
		case 2:
			mDatas = new ArrayList<Fragment>();
			tab01 = new AllPeopleFragment();
			tab02 = new MinisterFragment();
			tab03 = new FreshManFragment();
			mDatas.add(tab01);
			mDatas.add(tab02);
			mDatas.add(tab03);
			mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
				@Override
				public int getCount() {
					return mDatas.size();
				}

				@Override
				public Fragment getItem(int position) {
					return mDatas.get(position);
				}
			};
			vp_peoplelist.setAdapter(mAdapter);
			break;
		}
	}

	// ��������ɫ�ָ���ʼ״̬
	private void resetTextView() {
		tv_allproject.setTextColor(Color.parseColor("#000000"));
		tv_takepartByme.setTextColor(Color.parseColor("#000000"));
		tv_buildByme.setTextColor(Color.parseColor("#000000"));
	}

	// �Ӵ��¼���Ӧ
	@SuppressLint({ "ResourceAsColor", "ClickableViewAccessibility" })
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getAction();
		if (action == MotionEvent.ACTION_DOWN) {
			switch (v.getId()) {
			case R.id.btn_menu:
				btn_menu.setBackgroundResource(R.drawable.menu_down);
				break;
			case R.id.btn_add:
				btn_add.setBackgroundResource(R.drawable.title_add_down);
				break;
			case R.id.ll_myproject:
				ll_myproject.setBackgroundColor(Color.parseColor("#888888"));
				break;
			case R.id.ll_mytask:
				ll_mytask.setBackgroundColor(Color.parseColor("#888888"));
				break;
			case R.id.ll_peoplelist:
				ll_peoplelist.setBackgroundColor(Color.parseColor("#888888"));
				break;
			}
		} else if (action == MotionEvent.ACTION_UP) {
			switch (v.getId()) {
			case R.id.btn_menu:
				btn_menu.setBackgroundResource(R.drawable.menu_up);
				break;
			case R.id.btn_add:
				btn_add.setBackgroundResource(R.drawable.title_add);
				break;
			case R.id.ll_myproject:
				ll_myproject.setBackgroundColor(Color.parseColor("#555555"));
				break;
			case R.id.ll_mytask:
				ll_mytask.setBackgroundColor(Color.parseColor("#555555"));
				break;
			case R.id.ll_peoplelist:
				ll_peoplelist.setBackgroundColor(Color.parseColor("#555555"));
				break;
			}
		}
		return false;
	}

	// ����¼���Ӧ
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_menu:
			slidingmenu.toggle();
			break;
		case R.id.ll_myproject:
			slidingmenu.toggle();
			// ���ñ�������
			tv_title.setText(R.string.menu_userselect1);
			// ����tab����
			tv_allproject.setText(R.string.myproject_tab1);
			tv_takepartByme.setText(R.string.myproject_tab2);
			tv_buildByme.setText(R.string.myproject_tab3);
			// ��ʾ��Ӱ�ť
			btn_add.setVisibility(View.VISIBLE);
			// ���ò�����־
			selectItem = 0;
			// ���û���ҳ�Ŀ�����
			vp_myproject.setVisibility(View.VISIBLE);
			vp_mytask.setVisibility(View.GONE);
			vp_peoplelist.setVisibility(View.GONE);
			initFragment();
			break;
		case R.id.ll_mytask:
			slidingmenu.toggle();
			// ���ñ�������
			tv_title.setText(R.string.menu_userselect2);
			// ����tab����
			tv_allproject.setText(R.string.mytask_tab1);
			tv_takepartByme.setText(R.string.mytask_tab2);
			tv_buildByme.setText(R.string.mytask_tab3);
			// ������Ӱ�ť
			btn_add.setVisibility(View.INVISIBLE);
			// ���ò�����־
			selectItem = 1;
			// ���û���ҳ�Ŀ�����
			vp_myproject.setVisibility(View.GONE);
			vp_mytask.setVisibility(View.VISIBLE);
			vp_peoplelist.setVisibility(View.GONE);
			initFragment();
			break;
		case R.id.ll_peoplelist:
			slidingmenu.toggle();
			// ���ñ�������
			tv_title.setText(R.string.menu_userselect3);
			// ����tab����
			tv_allproject.setText(R.string.peoplelist_tab1);
			tv_takepartByme.setText(R.string.peoplelist_tab2);
			tv_buildByme.setText(R.string.peoplelist_tab3);
			// ��ʾ��Ӱ�ť
			btn_add.setVisibility(View.VISIBLE);
			// ���ò�����־
			selectItem = 2;
			// ���û���ҳ�Ŀ�����
			vp_myproject.setVisibility(View.GONE);
			vp_mytask.setVisibility(View.GONE);
			vp_peoplelist.setVisibility(View.VISIBLE);
			initFragment();
			break;
		default:
			break;
		}
	}

	@Override
	public void onPageScrollStateChanged(int position) {

	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPx) {
		// ����tabline�ƶ�Ч��
		LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) iv_tabline
				.getLayoutParams();
		if (mCurrentPageIndex == 0 && position == 0) { // 0->1
			lp.leftMargin = (int) (positionOffset * mScreen1_3 + mCurrentPageIndex
					* mScreen1_3);
		} else if (mCurrentPageIndex == 1 && position == 0) { // 1->0
			lp.leftMargin = (int) ((positionOffset - 1) * mScreen1_3 + mCurrentPageIndex
					* mScreen1_3);
		} else if (mCurrentPageIndex == 1 && position == 1) { // 1->2
			lp.leftMargin = (int) (positionOffset * mScreen1_3 + mCurrentPageIndex
					* mScreen1_3);
		} else if (mCurrentPageIndex == 2 && position == 1) { // 2->1
			lp.leftMargin = (int) ((positionOffset - 1) * mScreen1_3 + mCurrentPageIndex
					* mScreen1_3);
		}

		iv_tabline.setLayoutParams(lp);
	}

	@Override
	public void onPageSelected(int position) {
		resetTextView(); // ��TextView��ɫ��Ϊ��ʼ״̬
		switch (position) {
		case 0:
			tv_allproject.setTextColor(Color.parseColor("#2680ef"));
			break;
		case 1:
			tv_takepartByme.setTextColor(Color.parseColor("#2680ef"));
			break;
		case 2:
			tv_buildByme.setTextColor(Color.parseColor("#2680ef"));
			break;
		}

		mCurrentPageIndex = position;

	}
}
