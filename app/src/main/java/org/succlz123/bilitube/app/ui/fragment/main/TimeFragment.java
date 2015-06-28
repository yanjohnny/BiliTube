package org.succlz123.bilitube.app.ui.fragment.main;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import org.succlz123.bilitube.app.R;
import org.succlz123.bilitube.app.bean.bangumi.BangumiArray;
import org.succlz123.bilitube.app.dao.Api.BangumiApi;
import org.succlz123.bilitube.app.support.ScrollListenerScrollView;
import org.succlz123.bilitube.app.support.ScrollViewCallBack;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by fashi on 2015/5/2.
 */
public class TimeFragment extends Fragment {
	private View mView;
	private ListView mListView;
	private TimeAdapetr mTimeAdapetr;
	private List<BangumiArray> mBangumiList;
	private ScrollListenerScrollView mScrollView;
	private ArrayList<ScrollViewCallBack> scrollViewCallBackArrayList;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.time, container, false);
		initViews();
//		scrollViewCallBack();
		new onGetBangumiAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		return mView;
	}

	private void initViews() {
		mListView = (ListView) mView.findViewById(R.id.time_listview);
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

		mListView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return false;
			}
		});

		mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				for (ScrollViewCallBack scrollViewCallBack : scrollViewCallBackArrayList) {
//					scrollViewCallBack.onScroll(totalItemCount, oldt);
				}
			}
		});
	}

	private List sortBangumiList() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date dateJson = null;
		Date dateNow = null;
		List<BangumiArray> today = new ArrayList<BangumiArray>();
		List<BangumiArray> today1 = new ArrayList<BangumiArray>();
		List<BangumiArray> today2 = new ArrayList<BangumiArray>();
		List<BangumiArray> today3 = new ArrayList<BangumiArray>();
		List<BangumiArray> today4 = new ArrayList<BangumiArray>();
		List<BangumiArray> today5 = new ArrayList<BangumiArray>();
		List<BangumiArray> today6 = new ArrayList<BangumiArray>();
		List<BangumiArray> other = new ArrayList<BangumiArray>();
		List<Object> DayList = new ArrayList<Object>();
		for (BangumiArray bangumi : mBangumiList) {
			try {
				dateJson = df.parse(bangumi.getLastupdate_at());
				dateNow = df.parse(df.format(new Date()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			long diff = dateNow.getTime() - dateJson.getTime();
			int days = (int) (diff / (1000 * 60 * 60 * 24));
			if (days == 0) {
				today.add(bangumi);
			} else if (days == 1) {
				today1.add(bangumi);
			} else if (days == 2) {
				today2.add(bangumi);
			} else if (days == 3) {
				today3.add(bangumi);
			} else if (days == 4) {
				today4.add(bangumi);
			} else if (days == 5) {
				today5.add(bangumi);
			} else if (days == 6) {
				today6.add(bangumi);
			} else {
				other.add(bangumi);
			}
		}
		DayList.add("0");
		for (BangumiArray bangumi : today) {
			DayList.add(bangumi);
		}
		DayList.add("1");
		for (BangumiArray bangumi : today1) {
			DayList.add(bangumi);
		}
		DayList.add("2");
		for (BangumiArray bangumi : today2) {
			DayList.add(bangumi);
		}
		DayList.add("3");
		for (BangumiArray bangumi : today3) {
			DayList.add(bangumi);
		}
		DayList.add("4");
		for (BangumiArray bangumi : today4) {
			DayList.add(bangumi);
		}
		DayList.add("5");
		for (BangumiArray bangumi : today5) {
			DayList.add(bangumi);
		}
		DayList.add("6");
		for (BangumiArray bangumi : today6) {
			DayList.add(bangumi);
		}
		DayList.add("7");
		for (BangumiArray bangumi : other) {
			DayList.add(bangumi);
		}

		return DayList;
	}


	private class TimeAdapetr extends BaseAdapter {
		final int TYPE_TIME = 0;
		final int TYPE_CONTENT = 1;
		List<Object> dayList;

		private TimeAdapetr(List<Object> DayList) {
			this.dayList = DayList;
		}

		@Override
		public int getCount() {
			if (dayList != null) {
				return dayList.size();
			}
			return 0;
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
		public int getItemViewType(int position) {
			Object item = dayList.get(position);
			if (item instanceof BangumiArray) {
				return TYPE_CONTENT;
			} else if (item instanceof String) {
				return TYPE_TIME;
			}
			return -1;
		}

		@Override
		public int getViewTypeCount() {
			return 2;
		}

		public class ViewHolder {
			private TextView mWeekText;
			private TextView mDayText;
			private TextView mTitleText;
			private TextView mBgmcountText;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			Object item = dayList.get(position);

			switch (getItemViewType(position)) {
				case TYPE_TIME:
					if (convertView == null) {
						convertView = getActivity().getLayoutInflater().inflate(R.layout.time_listview_item_time, parent, false);
						holder = new ViewHolder();
						holder.mWeekText = (TextView) convertView.findViewById(R.id.time_listview_time_week);
						holder.mDayText = (TextView) convertView.findViewById(R.id.time_listview_time_day);
						convertView.setTag(holder);
					} else {
						holder = (ViewHolder) convertView.getTag();
					}
					String day = (String) item;

					Calendar c = Calendar.getInstance();
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					Date d=new Date();
//					String time = df.format(new Date());
//					try {
//						Date dateNow = df.parse(df.format(new Date()));
//
//						c.setTime(df.parse(time));
//					} catch (ParseException e) {
//						e.printStackTrace();
//					}
//					c.add(Calendar.DATE, 1);
//
//					GregorianCalendar gc=new GregorianCalendar();
//					gc.setTime(new Date());
//					gc.add(Calendar.DAY_OF_MONTH, 4);
//
//					int month = c.get(Calendar.MONTH) + 1;
//					int date = c.get(Calendar.DATE);

					if (TextUtils.equals(day, "0")) {
						holder.mWeekText.setText(df.format(d));
					} else if (TextUtils.equals(day, "1")) {
						holder.mWeekText.setText(df.format(new Date(d.getTime() - 1 * 24 * 60 * 60 * 1000)));
					} else if (TextUtils.equals(day, "2")) {
						holder.mWeekText.setText(df.format(new Date(d.getTime() - 2 * 24 * 60 * 60 * 1000)));
					} else if (TextUtils.equals(day, "3")) {
 						holder.mWeekText.setText(df.format(new Date(d.getTime() - 3 * 24 * 60 * 60 * 1000)));
					} else if (TextUtils.equals(day, "4")) {
						holder.mWeekText.setText(df.format(new Date(d.getTime() - 4 * 24 * 60 * 60 * 1000)));
					} else if (TextUtils.equals(day, "5")) {
						holder.mWeekText.setText(df.format(new Date(d.getTime() - 5 * 24 * 60 * 60 * 1000)));
					} else if (TextUtils.equals(day, "6")) {
						holder.mWeekText.setText(df.format(new Date(d.getTime() - 6 * 24 * 60 * 60 * 1000)));
					} else {
						holder.mWeekText.setText("一周以前");
					}

					break;

				case TYPE_CONTENT:
					if (convertView == null) {
						convertView = getActivity().getLayoutInflater().inflate(R.layout.time_listview_item_content, parent, false);
						holder = new ViewHolder();
						holder.mTitleText = (TextView) convertView.findViewById(R.id.time_listview_content_title);
						holder.mBgmcountText = (TextView) convertView.findViewById(R.id.time_listview_content_bgmcount);
						convertView.setTag(holder);
					} else {
						holder = (ViewHolder) convertView.getTag();
					}

					BangumiArray bangumi = (BangumiArray) item;


					List<String> week = new ArrayList<String>();
					week.add("星期日");
					week.add("星期一");
					week.add("星期二");
					week.add("星期三");
					week.add("星期四");
					week.add("星期五");
					week.add("星期六");

					holder.mTitleText.setText(bangumi.getTitle());
					holder.mBgmcountText.setText("第" + bangumi.getBgmcount() + "话");

					break;
			}

			return convertView;
		}
	}

	private class onGetBangumiAsyncTask extends AsyncTask<Void, Void, List> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected List doInBackground(Void... params) {

			return BangumiApi.getBangumiList();
		}

		@Override
		protected void onPostExecute(List aVoid) {
			super.onPostExecute(aVoid);
			TimeFragment.this.mBangumiList = aVoid;
			mTimeAdapetr = new TimeAdapetr(sortBangumiList());
			mListView.setAdapter(mTimeAdapetr);
			mTimeAdapetr.notifyDataSetChanged();

		}
	}
}

