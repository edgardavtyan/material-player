package com.edavtyan.materialplayer.components.now_playing_floating;

import android.content.Context;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NowPlayingFloatingFragment
		extends BaseFragment
		implements NowPlayingFloatingMvp.View {

	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.info) TextView infoView;
	@BindView(R.id.art) ImageView artView;
	@BindView(R.id.playPause) ImageButton playPauseButton;
	@BindView(R.id.container) LinearLayout mainWrapper;

	private NowPlayingFloatingMvp.Presenter presenter;
	private Navigator navigator;

	@OnClick(R.id.playPause)
	public void onPlayPauseClick() {
		presenter.onPlayPauseClick();
	}

	@OnClick({R.id.art, R.id.infoWrapper})
	public void onArtOrInfoWrapperClick() {
		presenter.onViewClick();
	}

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
		inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.fragment_nowplaying_floating, container, false);
		ButterKnife.bind(this, view);
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
		playPauseButton.setImageResource(isPlaying ? R.drawable.ic_pause : R.drawable.ic_play);
	}

	@Override
	public void setIsVisible(boolean visibility) {
		mainWrapper.setVisibility(visibility ? View.VISIBLE : View.GONE);
	}

	@Override
	public void gotoNowPlaying() {
		navigator.gotoNowPlaying();
	}
}
