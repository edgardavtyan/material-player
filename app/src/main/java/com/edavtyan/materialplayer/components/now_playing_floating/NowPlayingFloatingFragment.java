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

import com.ed.libsutils.utils.BitmapResizer;
import com.ed.libsutils.utils.DpConverter;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.lib.theme.ThemeDaggerModule;
import com.edavtyan.materialplayer.modular.fragment.ModularFragment;

import javax.inject.Inject;

import butterknife.BindView;

public class NowPlayingFloatingFragment extends ModularFragment implements View.OnClickListener {

	public static final int SCALED_ART_SIZE_DP = 44;

	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.info) TextView infoView;
	@BindView(R.id.art) ImageView artView;
	@BindView(R.id.play_pause) ImageButton playPauseButton;
	@BindView(R.id.container) LinearLayout mainWrapper;
	@BindView(R.id.info_container) LinearLayout infoWrapper;

	@Inject ScreenThemeModule themeModule;
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
	protected int getLayoutId() {
		return R.layout.fragment_nowplaying_floating;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getComponent().inject(this);
		addModule(themeModule);
	}

	@Nullable
	@Override
	public View onCreateView(
			LayoutInflater inflater,
			@Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		View view = super.onCreateView(inflater, container, savedInstanceState);
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

	@Override
	public void onThemeChanged(ThemeColors colors) {
		mainWrapper.setBackgroundColor(colors.getColorPrimary());
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
			int scaledArtSize = DpConverter.dpToPixel(SCALED_ART_SIZE_DP);
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
				.themeDaggerModule(new ThemeDaggerModule(this))
				.build();
	}
}
