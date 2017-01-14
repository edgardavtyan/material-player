package com.edavtyan.materialplayer.components.now_playing_floating;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.lib.base.BaseFragment;

public class NowPlayingFloatingFragment
		extends BaseFragment
		implements View.OnClickListener, NowPlayingFloatingMvp.View {

	private NowPlayingFloatingMvp.Presenter presenter;
	private Navigator navigator;
	private TextView titleView;
	private TextView infoView;
	private ImageView artView;
	private ImageButton playPauseView;
	private LinearLayout mainWrapper;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		NowPlayingFloatingFactory factory = app.getNowPlayingFloatingFactory(getContext(), this);
		navigator = factory.getNavigator();
		presenter = factory.getPresenter();
	}

	@Nullable
	@Override
	public View onCreateView(
			LayoutInflater inflater,
			@Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_nowplaying_floating, container, false);

		titleView = findView(view, R.id.title);
		infoView = findView(view, R.id.info);
		playPauseView = findView(view, R.id.playPause);
		playPauseView.setOnClickListener(this);
		artView = findView(view, R.id.art);
		artView.setOnClickListener(this);
		mainWrapper = findView(view, R.id.container);

		LinearLayout infoWrapper = findView(view, R.id.infoWrapper);
		infoWrapper.setOnClickListener(this);

		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		presenter.onCreate();
	}

	@Override
	public void onStop() {
		super.onStop();
		presenter.onDestroy();
	}

	@Override
	public void setTrackTitle(String title) {
		titleView.setText(title);
	}

	@Override
	public void setTrackInfo(String artistTitle, String albumTitle) {
		Resources res = getContext().getResources();
		String info = res.getString(R.string.nowplaying_info_pattern, artistTitle, albumTitle);
		infoView.setText(info);
	}

	@Override
	public void setArt(Bitmap art) {
		if (art != null) {
			artView.setImageBitmap(art);
		} else {
			artView.setImageResource(R.drawable.fallback_cover);
		}
	}

	@Override
	public void setIsPlaying(boolean isPlaying) {
		playPauseView.setImageResource(isPlaying ? R.drawable.ic_pause : R.drawable.ic_play);
	}

	@Override
	public void setIsVisible(boolean visibility) {
		mainWrapper.setVisibility(visibility ? View.VISIBLE : View.GONE);
	}

	@Override
	public void gotoNowPlaying() {
		navigator.gotoNowPlaying();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.art:
		case R.id.infoWrapper:
			presenter.onViewClick();
			break;
		case R.id.playPause:
			presenter.onPlayPauseClick();
			break;
		}
	}
}
