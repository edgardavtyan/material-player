package com.edavtyan.materialplayer.ui.now_playing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.base.BaseActivityTransparent;
import com.edavtyan.materialplayer.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer.lib.transition.OutputSharedViews;
import com.edavtyan.materialplayer.lib.transition.SharedTransitionsManager;
import com.edavtyan.materialplayer.lib.transition.SharedViewSet;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesDIModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityToolbarModule;
import com.edavtyan.materialplayer.ui.Navigator;
import com.edavtyan.materialplayer.ui.now_playing.models.NowPlayingArt;
import com.edavtyan.materialplayer.ui.now_playing.models.NowPlayingControls;
import com.edavtyan.materialplayer.ui.now_playing.models.NowPlayingFab;
import com.edavtyan.materialplayer.ui.now_playing.models.NowPlayingInfo;
import com.edavtyan.materialplayer.ui.now_playing.models.NowPlayingLyrics;
import com.edavtyan.materialplayer.ui.now_playing.models.NowPlayingSeekbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;

public class NowPlayingActivity extends BaseActivityTransparent {
	@Inject ActivityBaseMenuModule baseMenuModule;
	@Inject ActivityToolbarModule toolbarModule;
	@Inject ScreenThemeModule themeModule;

	@Inject NowPlayingPresenter presenter;
	@Inject Navigator navigator;
	@Inject SharedTransitionsManager transitionManager;
	@Inject QueueRevealAnimation queueRevealAnimation;

	@Inject @Getter NowPlayingControls controls;
	@Inject @Getter NowPlayingInfo info;
	@Inject @Getter NowPlayingArt art;
	@Inject @Getter NowPlayingSeekbar seekbar;
	@Inject @Getter NowPlayingFab fab;
	@Inject @Getter NowPlayingLyrics lyrics;

	@BindView(R.id.inner_container) ConstraintLayout innerContainerView;
	@BindView(R.id.appbar) AppBarLayout appbar;
	@BindView(R.id.background) View background;

	private boolean isQueueShown;

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
		outputViewsBuilder.sharedViewSets(
				SharedViewSet.translating("art", art.getArtView(), art.getSharedArtView()));
		outputViewsBuilder
				.enterFadingViews(
						controls.getFastForwardButton(),
						controls.getPlayPauseButton(),
						controls.getRepeatButton(),
						controls.getRewindButton(),
						controls.getShuffleButton(),
						art.getArtView(),
						art.getShadowView(),
						lyrics.getWrapperView(),
						seekbar.getCurrentTimeView(),
						seekbar.getTotalTimeView(),
						seekbar.getSeekbar(),
						info.getInfoView(),
						info.getTitleView(),
						fab.getView(),
						appbar,
						background)
				.exitPortraitFadingViews(
						controls.getFastForwardButton(),
						controls.getPlayPauseButton(),
						controls.getRepeatButton(),
						controls.getRewindButton(),
						controls.getShuffleButton(),
						art.getArtView(),
						art.getShadowView(),
						lyrics.getWrapperView(),
						seekbar.getCurrentTimeView(),
						seekbar.getTotalTimeView(),
						seekbar.getSeekbar(),
						info.getInfoView(),
						info.getTitleView(),
						fab.getView(),
						appbar,
						background)
				.exitLandscapeFadingViews(innerContainerView, fab.getView());
		transitionManager.createSharedTransition(outputViewsBuilder.build());
		transitionManager.beginEnterTransition(this, savedInstanceState);
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
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		if (isQueueShown) {
			queueRevealAnimation.hide();
			isQueueShown = false;
		} else {
			transitionManager.beginExitTransition(this);
		}
	}

	public void showQueue() {
		queueRevealAnimation.show();
		isQueueShown = true;
	}

	protected NowPlayingDIComponent getComponent() {
		return DaggerNowPlayingDIComponent
				.builder()
				.appDIComponent(((App) getApplication()).getAppComponent())
				.nowPlayingDIModule(new NowPlayingDIModule(this))
				.activityModulesDIModule(new ActivityModulesDIModule(R.string.nowplaying_toolbar_title))
				.build();
	}
}
