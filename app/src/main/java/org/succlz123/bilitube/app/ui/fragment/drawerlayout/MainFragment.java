package org.succlz123.bilitube.app.ui.fragment.drawerlayout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.succlz123.bilitube.app.R;
import org.succlz123.bilitube.app.support.ScrollViewCallBack;
import org.succlz123.bilitube.app.support.com.example.android.common.slidingtab.SlidingTabLayout;
import org.succlz123.bilitube.app.ui.fragment.main.BangumiFragment;
import org.succlz123.bilitube.app.ui.fragment.main.NavigationFragment;
import org.succlz123.bilitube.app.ui.fragment.main.RecommendFragment;
import org.succlz123.bilitube.app.ui.fragment.main.TimeFragment;

/**
 * Created by fashi on 2015/5/1.
 */
public class MainFragment extends Fragment implements ScrollViewCallBack {
	private View mView;
	private ViewPager mMainViewPager;
	private MainFragmentAdapte mMainFragmentAdapte;
	private SlidingTabLayout mSlidingTabLayout;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_main, container, false);
		initViews();
		setMainViewPager();
		setmSlidingTabLayout();
		return mView;
	}

	private void initViews() {
		mMainViewPager = (ViewPager) mView.findViewById(R.id.main_viewpager);
		mSlidingTabLayout = (SlidingTabLayout) mView.findViewById(R.id.main_slidingtablayout);
	}

	private void setMainViewPager() {
		mMainFragmentAdapte = new MainFragmentAdapte(getChildFragmentManager(), getActivity());
		mMainViewPager.setAdapter(mMainFragmentAdapte);
		mMainViewPager.setOffscreenPageLimit(4);
		mMainViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
	}

	private void setmSlidingTabLayout() {
		mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
			@Override
			public int getIndicatorColor(int position) {
				return getResources().getColor(R.color.white);
			}
		});
		mSlidingTabLayout.setBackgroundResource(R.color.moebase);
		mSlidingTabLayout.setDistributeEvenly(true);
		mSlidingTabLayout.setViewPager(mMainViewPager);
	}

	public class MainFragmentAdapte extends FragmentPagerAdapter {
		private Context context;

		public MainFragmentAdapte(android.support.v4.app.FragmentManager fm, Context context) {
			super(fm);
			this.context = context;
		}

		@Override
		public Fragment getItem(int i) {
			switch (i) {
				case 0:
					return new RecommendFragment();
				case 1:
					return new NavigationFragment();
				case 2:
					return new BangumiFragment();
				case 3:
					return new TimeFragment();
			}
			return null;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
				case 0:
					return "首页推荐";
				case 1:
					return "分区导航";
				case 2:
					return "新番放送";
				case 3:
					return "放送时间表";
			}
			return super.getPageTitle(position);
		}

		@Override
		public int getCount() {
			return 4;
		}
	}

	@Override
	public void onScroll(int t, int oldt) {
		int density = (int) getResources().getDisplayMetrics().density;

//		int height = mSlidingTabLayout.getHeight()+56*3;
		int height = 56 * density;

		//0到-height之间
		float haha = Math.min(t, height);
		haha = Math.min(Math.max(-haha, -height), 0);

		//大于0是向上移动
		mSlidingTabLayout.setTranslationY(haha);



//		int height = mSlidingTabLayout.getHeight()+56*3;
//
//		//0到-height之间
//		float haha = Math.min(y, height);
//		haha = Math.min(Math.max(-haha, -height), 0);
//
//		float d = 0;
//		//大于0是向上移动
//		mSlidingTabLayout.scrollTo(0,(int)-haha);
//		mSlidingTabLayout.invalidate();
	}
}
