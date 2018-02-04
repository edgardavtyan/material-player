package com.edavtyan.materialplayer.screens.now_playing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesFactory;
import com.edavtyan.materialplayer.modular.activity.ModularActivity;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityToolbarModule;
import com.edavtyan.materialplayer.screens.Navigator;
import com.edavtyan.materialplayer.screens.now_playing.models.NowPlayingArt;
import com.edavtyan.materialplayer.screens.now_playing.models.NowPlayingControls;
import com.edavtyan.materialplayer.screens.now_playing.models.NowPlayingFab;
import com.edavtyan.materialplayer.screens.now_playing.models.NowPlayingInfo;
import com.edavtyan.materialplayer.screens.now_playing.models.NowPlayingSeekbar;
import com.edavtyan.materialplayer.transition.CurrentSharedViews;
import com.edavtyan.materialplayer.transition.SharedViewSet;
import com.edavtyan.materialplayer.transition.SharedViewsTransition;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;

public class NowPlayingActivity extends ModularActivity {
	@Inject ActivityBaseMenuModule baseMenuModule;
	@Inject ActivityToolbarModule toolbarModule;
	@Inject ScreenThemeModule themeModule;

	@Inject NowPlayingPresenter presenter;
	@Inject Navigator navigator;
	@Inject CurrentSharedViews currentSharedViews;

	@Inject @Getter NowPlayingControls controls;
	@Inject @Getter NowPlayingInfo info;
	@Inject @Getter NowPlayingArt art;
	@Inject @Getter NowPlayingSeekbar seekbar;
	@Inject @Getter NowPlayingFab fab;

	@BindView(R.id.inner_container) LinearLayout innerContainerView;

	private SharedViewsTransition transition;

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

		SharedViewSet sharedViewArtSet = new SharedViewSet(art.getArtView(), art.getSharedArtView());
		transition = new SharedViewsTransition(this, currentSharedViews);
		transition.setSharedViewSets(sharedViewArtSet);
		transition.setEnterFadingViews(innerContainerView, fab.getView());
		transition.setExitPortraitFadingViews(innerContainerView, fab.getView());
		transition.setExitLandscapeFadingViews(innerContainerView, fab.getView());

		if (savedInstanceState == null) transition.beginEnterTransition();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		presenter.unbind();
	}

	@Override
	public void onBackPressed() {
		transition.beginExitTransition();
	}

	@Override
	public void onThemeChanged(ThemeColors colors) {
		super.onThemeChanged(colors);
		innerContainerView.setBackgroundColor(colors.getColorPrimary());
	}

	public void gotoPlaylistScreen() {
		navigator.gotoNowPlayingQueue(this);
	}

	protected NowPlayingComponent getComponent() {
		return DaggerNowPlayingComponent
				.builder()
				.appComponent(((App) getApplication()).getAppComponent())
				.nowPlayingModule(new NowPlayingModule(this))
				.activityModulesFactory(new ActivityModulesFactory(R.string.nowplaying_toolbar_title))
				.build();
	}
}
