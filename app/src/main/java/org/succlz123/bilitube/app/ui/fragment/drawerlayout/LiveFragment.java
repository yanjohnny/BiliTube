package org.succlz123.bilitube.app.ui.fragment.drawerlayout;

import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import org.succlz123.bilitube.app.R;

import java.io.IOException;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;

/**
 * Created by fashi on 2015/5/2.
 */
public class LiveFragment extends Fragment implements MediaPlayer.OnBufferingUpdateListener,
		MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener,
		MediaPlayer.OnVideoSizeChangedListener, SurfaceHolder.Callback {

	private View mView;
	private MediaPlayer mMediaPlayer;
	private SurfaceHolder holder;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.play, container, false);
		if (!LibsChecker.checkVitamioLibs(getActivity())) {
			//return;//初始化库。若少了会报错！！
		}


		SurfaceView mPreview = (SurfaceView) mView.findViewById(R.id.surface);
		holder = mPreview.getHolder();
		holder.addCallback(this);

		holder.setFormat(PixelFormat.RGBA_8888);
//		extras = getArguments().getString();//获取数据
//
//		playVideo();

		return mView;
	}

	private void playVideo() {
		String xx = "/mnt/sdcard/0.MP4";
//		//创建一个MediaPlayer,并设置监听器
		mMediaPlayer = new MediaPlayer(getActivity());
		//设置资源路径
		try {
			mMediaPlayer.setDataSource(xx);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//设置SurfaceHolder
		mMediaPlayer.setDisplay(holder);
		mMediaPlayer.prepareAsync();
		mMediaPlayer.setOnBufferingUpdateListener(this);
		mMediaPlayer.setOnCompletionListener(this);
		mMediaPlayer.setOnPreparedListener(this);
		mMediaPlayer.setOnVideoSizeChangedListener(this);
		getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		playVideo();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {

	}

	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent) {
//缓冲监听的实现
//		Log.d(TAG, "onBufferingUpdate percent:" + percent);
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		//播放完毕监听的实现
//		Log.d(TAG, "onCompletion called");
	}

	@Override
	public void onPrepared(MediaPlayer mp) {

	}

	@Override
	public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
//显示大小改变监听的实现
//		Log.v(TAG, "onVideoSizeChanged called");
	}
}
