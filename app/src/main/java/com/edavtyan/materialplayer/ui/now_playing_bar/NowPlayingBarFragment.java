package com.edavtyan.materialplayer.ui.now_playing_bar;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ed.libsutils.utils.BitmapResizer;
import com.ed.libsutils.utils.DpConverter;
import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.lib.transition.SharedTransitionsManager;
import com.edavtyan.materialplayer.lib.transition.SourceSharedViews;
import com.edavtyan.materialplayer.modular.fragment.ModularFragment;
import com.edavtyan.materialplayer.ui.Navigator;

import javax.inject.Inject;

import butterknife.BindView;

public class NowPlayingBarFragment extends ModularFragment implements View.OnClickListener {

	public static final int SCALED_ART_SIZE_DP = 44;

	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.info) TextView infoView;
	@BindView(R.id.art) ImageView artView;
	@BindView(R.id.play_pause) ImageButton playPauseButton;
	@BindView(R.id.container) LinearLayout mainWrapper;
	@BindView(R.id.info_wrapper) LinearLayout infoWrapper;

	@Inject ScreenThemeModule themeModule;
	@Inject NowPlayingBarPresenter presenter;
	@Inject Navigator navigator;
	@Inject SharedTransitionsManager transitionsManager;
	@Inject ThemeColors theme;

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.play_pause:
			presenter.onPlayPauseClick();
			break;
		case R.id.art:
		case R.id.info_wrapper:
			presenter.onViewClick();
			break;
		}
	}

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_nowplaying_bar;
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

		mainWrapper.setBackgroundColor(theme.getNowplayingBarBackground());
		titleView.setTextColor(theme.getTextContrastPrimary());
		infoView.setTextColor(theme.getTextContrastSecondary());
		playPauseButton.setColorFilter(theme.getTextContrastPrimary());

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
		titleView.setTextColor(colors.getTextContrastPrimary());
		infoView.setTextColor(colors.getTextContrastSecondary());
		playPauseButton.setColorFilter(colors.getTextContrastPrimary());
	}

	public void setTrackTitle(String title) {
		titleView.setText(title);
	}

	public void setTrackInfo(String artistTitle, String albumTitle) {
		Resources res = getContext().getResources();
		String info = res.getString(R.string.nowplaying_info_pattern, artistTitle, albumTitle);
		infoView.setText(info);
	}

	public void setArt(@Nullable Bitmap art) {
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
		SourceSharedViews sharedViews = new SourceSharedViews(Pair.create(artView, "art"));
		transitionsManager.pushSourceViews(sharedViews);
		navigator.gotoNowPlaying(getActivity(), sharedViews.build());
	}

	protected NowPlayingBarDIComponent getComponent() {
		return DaggerNowPlayingBarDIComponent
				.builder()
				.appDIComponent(((App) getContext().getApplicationContext()).getAppComponent())
				.nowPlayingBarDIModule(new NowPlayingBarDIModule(getActivity(), this))
				.build();
	}
}
