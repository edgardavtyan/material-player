package com.edavtyan.materialplayer.ui.now_playing;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ed.libsutils.utils.WindowUtils;
import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesDIModule;
import com.edavtyan.materialplayer.modular.activity.ModularActivity;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityToolbarModule;
import com.edavtyan.materialplayer.transition.OutputSharedViews;
import com.edavtyan.materialplayer.transition.SharedTransitionsManager;
import com.edavtyan.materialplayer.transition.SharedViewSet;
import com.edavtyan.materialplayer.ui.Navigator;
import com.edavtyan.materialplayer.ui.now_playing.models.NowPlayingArt;
import com.edavtyan.materialplayer.ui.now_playing.models.NowPlayingControls;
import com.edavtyan.materialplayer.ui.now_playing.models.NowPlayingFab;
import com.edavtyan.materialplayer.ui.now_playing.models.NowPlayingInfo;
import com.edavtyan.materialplayer.ui.now_playing.models.NowPlayingSeekbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;

public class NowPlayingActivity extends ModularActivity {
	public static Bitmap listBitmap;

	@Inject ActivityBaseMenuModule baseMenuModule;
	@Inject ActivityToolbarModule toolbarModule;
	@Inject ScreenThemeModule themeModule;

	@Inject NowPlayingPresenter presenter;
	@Inject Navigator navigator;
	@Inject SharedTransitionsManager transitionManager;

	@Inject @Getter NowPlayingControls controls;
	@Inject @Getter NowPlayingInfo info;
	@Inject @Getter NowPlayingArt art;
	@Inject @Getter NowPlayingSeekbar seekbar;
	@Inject @Getter NowPlayingFab fab;

	@BindView(R.id.inner_container) LinearLayout innerContainerView;
	@BindView(R.id.appbar) AppBarLayout appbar;
	@BindView(R.id.shared_list) @Nullable ImageView listView;
	@BindView(R.id.shared_list_background) @Nullable View listBackground;
	@BindView(R.id.lyricsbox) TextView lyricsView;
	@BindView(R.id.lyricsScroller) ScrollView lyricsScrollerView;

	private boolean isLyricsEnabled = true;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nowplaying);
		ButterKnife.bind(this);
		getComponent().inject(this);
		addModule(baseMenuModule);
		addModule(toolbarModule);
		addModule(themeModule);
		presenter.bind();

		OutputSharedViews.Builder outputViewsBuilder = OutputSharedViews.builder();
		if (WindowUtils.isPortrait(this)) {
			assert listView != null;
			assert listBackground != null;

			listView.setImageBitmap(listBitmap);
			outputViewsBuilder.sharedViewSets(
					SharedViewSet.translating("art", art.getArtView(), art.getSharedArtView()),
					SharedViewSet.fading("list", listView)
								 .enterDuration(200)
								 .exitDuration(200)
								 .exitDelay(300),
					SharedViewSet.fading("listBackground", listBackground)
								 .enterDuration(200)
								 .exitDuration(200)
								 .exitDelay(300));
		} else {
			outputViewsBuilder.sharedViewSets(
					SharedViewSet.translating("art", art.getArtView(), art.getSharedArtView()));
		}

		outputViewsBuilder
				.enterFadingViews(innerContainerView, appbar, fab.getView())
				.exitPortraitFadingViews(innerContainerView, appbar, fab.getView())
				.exitLandscapeFadingViews(innerContainerView, fab.getView());
		transitionManager.createSharedTransition(outputViewsBuilder.build());
		transitionManager.beginEnterTransition(this, savedInstanceState);

		lyricsScrollerView.setOnTouchListener((v, e) -> !isLyricsEnabled);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		presenter.unbind();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.menu_nowplaying, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_toggleLyrics:
			presenter.onToggleLyricsClicked();
			if (isLyricsEnabled) {
				lyricsScrollerView.animate().alpha(0f);
				isLyricsEnabled = false;
			} else {
				lyricsScrollerView.animate().alpha(1f);
				isLyricsEnabled = true;
			}
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		transitionManager.beginExitTransition(this);
	}

	@Override
	public void onThemeChanged(ThemeColors colors) {
		super.onThemeChanged(colors);
		innerContainerView.setBackgroundColor(colors.getColorPrimary());
	}

	public void gotoPlaylistScreen() {
		navigator.gotoNowPlayingQueue(this);
	}

	public void setLyrics(String lyrics) {
		lyricsView.setText(lyrics);
	}

	protected NowPlayingDIComponent getComponent() {
		return DaggerNowPlayingDIComponent
				.builder()
				.appDIComponent(((App) getApplication()).getAppComponent())
				.nowPlayingDIModule(new NowPlayingDIModule(this))
				.activityModulesDIModule(new ActivityModulesDIModule(R.string.nowplaying_toolbar_title))
				.build();
	}

	public void hideLyrics() {
		lyricsScrollerView.animate().alpha(0f);
		isLyricsEnabled = false;
	}

	public void showLyrics() {
		lyricsScrollerView.animate().alpha(1f);
		isLyricsEnabled = true;
	}
}
