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

import com.ed.libsutils.BitmapResizer;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.CompactPrefsModule;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.UtilsModule;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtModule;
import com.edavtyan.materialplayer.lib.base.BaseFragment;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsModule;
import com.edavtyan.materialplayer.utils.DpConverter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NowPlayingFloatingFragment extends BaseFragment implements View.OnClickListener {

	public static final int SCALED_ART_SIZE_DP = 44;

	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.info) TextView infoView;
	@BindView(R.id.art) ImageView artView;
	@BindView(R.id.play_pause) ImageButton playPauseButton;
	@BindView(R.id.container) LinearLayout mainWrapper;
	@BindView(R.id.info_container) LinearLayout infoWrapper;

	@Inject NowPlayingFloatingPresenter presenter;
	@Inject Navigator navigator;

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.play_pause:
			presenter.onPlayPauseClick();
			break;
		case R.id.art:
		case R.id.info_container:
			presenter.onViewClick();
			break;
		}
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getComponent().inject(this);
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

		playPauseButton.setOnClickListener(this);
		artView.setOnClickListener(this);
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

	public void setTrackTitle(String title) {
		titleView.setText(title);
	}

	public void setTrackInfo(String artistTitle, String albumTitle) {
		Resources res = getContext().getResources();
		String info = res.getString(R.string.nowplaying_info_pattern, artistTitle, albumTitle);
		infoView.setText(info);
	}

	public void setArt(Bitmap art) {
		if (art != null) {
			int scaledArtSize = DpConverter.convertDpToPixel(SCALED_ART_SIZE_DP);
			Bitmap scaledArt = BitmapResizer.resize(art, scaledArtSize);
			artView.setImageBitmap(scaledArt);
		} else {
			artView.setImageResource(R.drawable.fallback_cover);
		}
	}

	public void setIsPlaying(boolean isPlaying) {
		playPauseButton.setImageResource(isPlaying ? R.drawable.ic_pause : R.drawable.ic_play);
	}

	public void setIsVisible(boolean visibility) {
		mainWrapper.setVisibility(visibility ? View.VISIBLE : View.GONE);
	}

	public void gotoNowPlaying() {
		navigator.gotoNowPlaying();
	}

	protected NowPlayingFloatingComponent getComponent() {
		return DaggerNowPlayingFloatingComponent
				.builder()
				.nowPlayingFloatingModule(new NowPlayingFloatingModule(getActivity(), this))
				.albumArtModule(new AlbumArtModule())
				.utilsModule(new UtilsModule())
				.compactPrefsModule(new CompactPrefsModule())
				.advancedSharedPrefsModule(new AdvancedSharedPrefsModule())
				.build();
	}
}
