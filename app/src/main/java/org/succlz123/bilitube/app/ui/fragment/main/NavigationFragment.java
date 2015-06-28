package org.succlz123.bilitube.app.ui.fragment.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import org.succlz123.bilitube.app.R;
import org.succlz123.bilitube.app.support.ScrollListenerScrollView;
import org.succlz123.bilitube.app.support.ScrollViewCallBack;

import java.util.ArrayList;

/**
 * Created by fashi on 2015/5/2.
 */
public class NavigationFragment extends Fragment {
	private View mView;
	private ScrollListenerScrollView mScrollView;
	private ArrayList<ScrollViewCallBack> scrollViewCallBackArrayList;
	private LinearLayout linearLayout;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.navigation, container, false);
		initViews();
		scrollViewCallBack();
		return mView;
	}

	private void initViews() {
		mScrollView = (ScrollListenerScrollView) mView.findViewById(R.id.navigation_scrollview);
		linearLayout = (LinearLayout) mView.findViewById(R.id.navigation_linear);
	}

	private void scrollViewCallBack() {
		Activity mActvity = getActivity();
		Fragment mFragment = getParentFragment();

		scrollViewCallBackArrayList = new ArrayList<ScrollViewCallBack>();
		if (mActvity instanceof ScrollViewCallBack) {
			scrollViewCallBackArrayList.add((ScrollViewCallBack) mActvity);
		}

		if (mFragment instanceof ScrollViewCallBack) {
			scrollViewCallBackArrayList.add((ScrollViewCallBack) mFragment);
		}

		mScrollView.setOnScrollChangedListner(new ScrollListenerScrollView.OnScrollChangedListner() {
			@Override
			public void onScroll(int l, int t, int oldl, int oldt) {
				for (ScrollViewCallBack scrollViewCallBack : scrollViewCallBackArrayList) {
					scrollViewCallBack.onScroll(t, oldt);
				}
			}
		});
	}

}
