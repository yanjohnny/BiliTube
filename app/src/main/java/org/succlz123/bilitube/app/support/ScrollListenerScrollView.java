package org.succlz123.bilitube.app.support;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;

/**
 * Created by fashi on 2015/5/3.
 */
public class ScrollListenerScrollView extends ScrollView {

	public static interface OnScrollChangedListner {
		public void onScroll(int l, int t, int oldl, int oldt);
	}

	private OnScrollChangedListner onScrollChangedListner;

	public ScrollListenerScrollView(Context context) {
		super(context);
	}

	public ScrollListenerScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ScrollListenerScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public ScrollListenerScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public void setOnScrollChangedListner(OnScrollChangedListner onScrollChangedListner) {
		this.onScrollChangedListner = onScrollChangedListner;
		Log.e("ff","ff");
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if (this.onScrollChangedListner != null) {
			this.onScrollChangedListner.onScroll(l, t, oldl, oldt);
		}
	}
}
