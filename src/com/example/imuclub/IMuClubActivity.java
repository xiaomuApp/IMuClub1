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

	// 主界面控件
	private Button btn_menu; // 菜单按钮
	private Button btn_add; // 新建按钮
	private TextView tv_title; // 标题
	private ViewPager vp_myproject; // 我的项目滑动页
	private ViewPager vp_mytask; // 我的任务滑动页
	private ViewPager vp_peoplelist; // 人员列表滑动页

	// 主界面tab控件
	private TextView tv_allproject; // 全部项目
	private TextView tv_takepartByme; // 我参加的
	private TextView tv_buildByme; // 我创建的
	private ImageView iv_tabline; // tab下滑块

	// tab界面
	Fragment tab01;
	Fragment tab02;
	Fragment tab03;

	// 左侧菜单控件
	private Button ll_myproject; // 我的项目
	private Button ll_mytask; // 我的任务
	private Button ll_peoplelist; // 人员列表

	// 程序变量
	private SlidingMenu slidingmenu; // 滑动菜单
	private FragmentPagerAdapter mAdapter; // FragmentPager适配器
	private List<Fragment> mDatas; // 三个Fragment
	private int mScreen1_3; // 记录屏幕的三分之一长度
	private int mCurrentPageIndex; // 记录当前页面下标

	// 操作标志
	private int selectItem = 0; // 0代表选择“我的项目”，1代表“我的任务”，2代表“人员列表”

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imuclub);

		initMenu(); // 初始化Menu
		initView(); // 初始化View
		initTabline(); // 初始化tabline

	}

	// 初始化Menu
	private void initMenu() {
		slidingmenu = new SlidingMenu(this);
		slidingmenu.setMode(SlidingMenu.LEFT); // 左边模式
		slidingmenu.setBehindOffsetRes(R.dimen.sliding_menu_offset);
		slidingmenu.setTouchModeAbove(SlidingMenu.LEFT); // 左边可触摸
		slidingmenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		slidingmenu.setMenu(R.layout.slidingmenu);
	}

	// 初始化tabline
	private void initTabline() {
		iv_tabline = (ImageView) findViewById(R.id.iv_tabline); // tabline图形控件

		Display display = getWindow().getWindowManager().getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics();
		display.getMetrics(outMetrics);
		mScreen1_3 = outMetrics.widthPixels / 3;
		LayoutParams lp = iv_tabline.getLayoutParams();
		lp.width = mScreen1_3;
		iv_tabline.setLayoutParams(lp);
	}

	// 初始化View
	private void initView() {
		// 主界面控件
		btn_menu = (Button) findViewById(R.id.btn_menu); // 菜单按钮
		btn_add = (Button) findViewById(R.id.btn_add); // 添加按钮
		tv_title = (TextView) findViewById(R.id.tv_title); // 标题
		vp_myproject = (ViewPager) findViewById(R.id.vp_myproject); // 我的项目滑动页面
		vp_mytask = (ViewPager) findViewById(R.id.vp_mytask); // 我的任务滑动页
		vp_peoplelist = (ViewPager) findViewById(R.id.vp_peoplelist); // 人员列表滑动页

		// 主界面tab控件
		tv_allproject = (TextView) findViewById(R.id.tv_allproject); // 全部项目
		tv_takepartByme = (TextView) findViewById(R.id.tv_takepartByme); // 我参加的
		tv_buildByme = (TextView) findViewById(R.id.tv_buildByme); // 我创建的
		iv_tabline = (ImageView) findViewById(R.id.iv_tabline); // tab下滑块

		// 左侧菜单控件
		ll_myproject = (Button) findViewById(R.id.ll_myproject); // 我的项目
		ll_mytask = (Button) findViewById(R.id.ll_mytask); // 我的任务
		ll_peoplelist = (Button) findViewById(R.id.ll_peoplelist); // 人员列表

		// 程序变量

		initFragment(); // 初始化Fragment

		// 添加页面滚动监听器
		vp_myproject.setOnPageChangeListener(this);
		vp_mytask.setOnPageChangeListener(this);
		vp_peoplelist.setOnPageChangeListener(this);

		// 设置按下监听器
		btn_menu.setOnTouchListener(this);
		btn_add.setOnTouchListener(this);
		ll_myproject.setOnTouchListener(this);
		ll_mytask.setOnTouchListener(this);
		ll_peoplelist.setOnTouchListener(this);

		// 设置点击监听器
		btn_menu.setOnClickListener(this);
		ll_myproject.setOnClickListener(this);
		ll_mytask.setOnClickListener(this);
		ll_peoplelist.setOnClickListener(this);
	}

	// 根据不同选择初始化Fragment
	private void initFragment() {
		switch (selectItem) {
		case 0: // 我的项目
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

	// 让字体颜色恢复初始状态
	private void resetTextView() {
		tv_allproject.setTextColor(Color.parseColor("#000000"));
		tv_takepartByme.setTextColor(Color.parseColor("#000000"));
		tv_buildByme.setTextColor(Color.parseColor("#000000"));
	}

	// 接触事件响应
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

	// 点击事件响应
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_menu:
			slidingmenu.toggle();
			break;
		case R.id.ll_myproject:
			slidingmenu.toggle();
			// 设置标题文字
			tv_title.setText(R.string.menu_userselect1);
			// 设置tab文字
			tv_allproject.setText(R.string.myproject_tab1);
			tv_takepartByme.setText(R.string.myproject_tab2);
			tv_buildByme.setText(R.string.myproject_tab3);
			// 显示添加按钮
			btn_add.setVisibility(View.VISIBLE);
			// 设置操作标志
			selectItem = 0;
			// 设置滑动页的可视性
			vp_myproject.setVisibility(View.VISIBLE);
			vp_mytask.setVisibility(View.GONE);
			vp_peoplelist.setVisibility(View.GONE);
			initFragment();
			break;
		case R.id.ll_mytask:
			slidingmenu.toggle();
			// 设置标题文字
			tv_title.setText(R.string.menu_userselect2);
			// 设置tab文字
			tv_allproject.setText(R.string.mytask_tab1);
			tv_takepartByme.setText(R.string.mytask_tab2);
			tv_buildByme.setText(R.string.mytask_tab3);
			// 隐藏添加按钮
			btn_add.setVisibility(View.INVISIBLE);
			// 设置操作标志
			selectItem = 1;
			// 设置滑动页的可视性
			vp_myproject.setVisibility(View.GONE);
			vp_mytask.setVisibility(View.VISIBLE);
			vp_peoplelist.setVisibility(View.GONE);
			initFragment();
			break;
		case R.id.ll_peoplelist:
			slidingmenu.toggle();
			// 设置标题文字
			tv_title.setText(R.string.menu_userselect3);
			// 设置tab文字
			tv_allproject.setText(R.string.peoplelist_tab1);
			tv_takepartByme.setText(R.string.peoplelist_tab2);
			tv_buildByme.setText(R.string.peoplelist_tab3);
			// 显示添加按钮
			btn_add.setVisibility(View.VISIBLE);
			// 设置操作标志
			selectItem = 2;
			// 设置滑动页的可视性
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
		// 设置tabline移动效果
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
		resetTextView(); // 将TextView颜色置为初始状态
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
