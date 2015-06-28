package org.succlz123.bilitube.app.ui.fragment.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.succlz123.bilitube.app.R;
import org.succlz123.bilitube.app.support.ScrollListenerScrollView;
import org.succlz123.bilitube.app.support.ScrollViewCallBack;

import java.util.ArrayList;

/**
 * Created by fashi on 2015/5/2.
 */
public class RecommendFragment extends Fragment implements ScrollViewCallBack{
	private View mView;
	private ScrollListenerScrollView mScrollView;
	private ArrayList<ScrollViewCallBack> scrollViewCallBackArrayList;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.recommend, container, false);
		initViews();
		scrollViewCallBack();

		return mView;
	}

	private void initViews() {
		mScrollView = (ScrollListenerScrollView) mView.findViewById(R.id.recommend_scrollview);
	}

	private void scrollViewCallBack() {
		Activity mActvity = getActivity();
		Fragment mFragment = getParentFragment();
		NavigationFragment navigationFragment=new NavigationFragment();

		scrollViewCallBackArrayList = new ArrayList<ScrollViewCallBack>();
		if (mActvity instanceof ScrollViewCallBack) {
			scrollViewCallBackArrayList.add((ScrollViewCallBack) mActvity);
		}

		if (mFragment instanceof ScrollViewCallBack) {
			scrollViewCallBackArrayList.add((ScrollViewCallBack) mFragment);
 		}

//		if (navigationFragment instanceof ScrollViewCallBack) {
// 			scrollViewCallBackArrayList.add((ScrollViewCallBack) navigationFragment);
//		}

		mScrollView.setOnScrollChangedListner(new ScrollListenerScrollView.OnScrollChangedListner() {
			@Override
			public void onScroll(int l, int t, int oldl, int oldt) {
				for (ScrollViewCallBack scrollViewCallBack : scrollViewCallBackArrayList) {
					scrollViewCallBack.onScroll(t, oldt);
				}
			}
		});
	}

	@Override
	public void onScroll(int t, int oldt) {
//		int density = (int) getResources().getDisplayMetrics().density;
//
////		int height = mSlidingTabLayout.getHeight()+56*3;
//		int height = 56 * density;
//
//		//0到-height之间
//		float haha = Math.min(t, height);
//		haha = Math.min(Math.max(-haha, -height), 0);
//
//		//大于0是向上移动
//		mSlidingTabLayout.setTranslationY(haha);

	}

	//	private class MyAdapter extends PagerAdapter {
//
//
//		@Override
//		public Object instantiateItem(ViewGroup container, int position) {
//			return super.instantiateItem(container, position);
//		}
//
//		@Override
//		public void destroyItem(ViewGroup container, int position, Object object) {
//			super.destroyItem(container, position, object);
//		}
//
//		@Override
//		public int getCount() {
//			return 6;
//		}
//
//		@Override
//		public boolean isViewFromObject(View view, Object object) {
//			return view == object;
//		}
//	}

}
