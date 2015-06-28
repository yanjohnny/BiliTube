package org.succlz123.bilitube.app.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.*;
import org.succlz123.bilitube.app.R;
import org.succlz123.bilitube.app.support.ScrollViewCallBack;
import org.succlz123.bilitube.app.ui.fragment.drawerlayout.CenterFragment;
import org.succlz123.bilitube.app.ui.fragment.drawerlayout.LiveFragment;
import org.succlz123.bilitube.app.ui.fragment.drawerlayout.MainFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements ScrollViewCallBack {
	public Toolbar mToolbar;
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private ListView mDrawerLayoutListView;
	private MyAdapter myAdapter;
	private List<Fragment> mFragmentArraryList;
	private MainFragment mainFragment;
	private LiveFragment liveFragment;
	private CenterFragment centerFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initViews();
		setmToolbar();
		setmDrawerToggle();
		setmDrawerLayout();
		mFragmentArraryListAdd();
		setmListView();
	}

	private void initViews() {
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		mDrawerLayoutListView = (ListView) findViewById(R.id.drawerLayout_listView);
	}

	private void setmToolbar() {
		mToolbar.setTitle("Bili Tube");
		mToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
		setSupportActionBar(mToolbar);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	private void setmDrawerToggle() {
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close) {
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
			}
		};
		mDrawerToggle.syncState();
	}

	private void setmDrawerLayout() {
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		mDrawerLayout.setScrimColor(getResources().getColor(R.color.shadow));
	}

	private void mFragmentArraryListAdd() {
		mFragmentArraryList = new ArrayList<Fragment>();
		mainFragment = new MainFragment();
		liveFragment = new LiveFragment();
		centerFragment = new CenterFragment();
		mFragmentArraryList.add(mainFragment);
		mFragmentArraryList.add(liveFragment);
		mFragmentArraryList.add(centerFragment);
	}

	private void setmListView() {
		myAdapter = new MyAdapter();
		mDrawerLayoutListView.setAdapter(myAdapter);
		mDrawerLayoutListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
				mDrawerLayout.closeDrawer(Gravity.LEFT);//选择完fragment 关闭drawerlayout
				new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
					@Override
					public void run() {
						FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
						for (int i = 0; i < mFragmentArraryList.size(); i++) {
							Fragment mFragment = mFragmentArraryList.get(i);
							if (i == position) {
								if (getSupportFragmentManager().findFragmentByTag(mFragment.getClass().getSimpleName()) == null) {
									mFragmentTransaction.add(R.id.activity_content, mFragment, mFragment.getClass().getSimpleName());
								}//找到同名fragment显示
								mFragmentTransaction.show(mFragment);
							} else {
								if (getSupportFragmentManager().findFragmentByTag(mFragment.getClass().getSimpleName()) != null) {
									mFragmentTransaction.hide(mFragment);
								}//其他不是需要的fragment隐藏
							}
						}
						mFragmentTransaction.commitAllowingStateLoss();
					}
				}, 200);
			}
		});
		mDrawerLayoutListView.performItemClick(null, 0, 0);
	}

	private class MyAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return 7;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			RecyclerView.ViewHolder viewHolder = null;
			convertView = getLayoutInflater().inflate(R.layout.drawerlayout_listview_item, parent, false);
			TextView mText = (TextView) convertView.findViewById(R.id.drawerLayout_listView_text);
			TextView mNum = (TextView) convertView.findViewById(R.id.drawerLayout_listView_num);
			ImageView mImage = (ImageView) convertView.findViewById(R.id.drawerLayout_listView_img);
			View mView = (View) convertView.findViewById(R.id.drawerLayout_listView_view);

			if (position == 0) {
				mText.setText("主站");
				mImage.setBackgroundResource(R.drawable.haha);
			} else if (position == 1) {
				mText.setText("画友");
				mImage.setBackgroundResource(R.drawable.haha);
			} else if (position == 2) {
				mText.setText("直播 (Beta)");
				mView.setVisibility(View.VISIBLE);
				mImage.setBackgroundResource(R.drawable.haha);
			} else if (position == 3) {
				mText.setText("会员中心");
				mNum.setText("22");
				mImage.setBackgroundResource(R.drawable.haha);
			} else if (position == 4) {
				mText.setText("收藏");
				mNum.setText("2");
				mImage.setBackgroundResource(R.drawable.haha);
			} else if (position == 5) {
				mText.setText("信息");
				mImage.setBackgroundResource(R.drawable.haha);
			} else if (position == 6) {
				mText.setText("历史记录");
				mImage.setBackgroundResource(R.drawable.haha);
			} else if (position == 7) {
				mText.setText("离线管理");
				mNum.setText("11");
				mImage.setBackgroundResource(R.drawable.haha);
			}
			return convertView;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onScroll(int t, int oldt) {
		//获取屏幕密度
		int density = (int) getResources().getDisplayMetrics().density;
		//toolbar默认 56dp 56*屏幕密度
		int height = 56 * density;
		//toolbar的移动距离
//		float move = 0;
		float move = Math.min(t, height);
//		if (t > height) {//手指移动的距离t大于toolbar的高度 那就只移动toolbar的高度 然后就停止移动
//			move = height;
//		} else if (t < height) {//手指移动的距离t小于toolbar的高度 那就移动手指移动的距离 直到 t=toolbar的高度
//			move = t;
//		}

		move = Math.min(Math.max(-move, -height), 0);


			mToolbar.offsetTopAndBottom((int) (move - mToolbar.getTop() + 25 * density));

			if (mToolbar.getTranslationY() == -height) {
				mToolbar.setVisibility(View.INVISIBLE);
			} else {
				mToolbar.setVisibility(View.VISIBLE);
			}

//		mToolbar.setTranslationY(haha);
	}
}
