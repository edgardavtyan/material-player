package com.edavtyan.materialplayer.screens.now_playing;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ed.libsutils.utils.WindowUtils;
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
import com.edavtyan.materialplayer.transition.SharedTransitionsManager;
import com.edavtyan.materialplayer.transition.SharedViewSet;

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
	@Inject SharedTransitionsManager transition;

	@Inject @Getter NowPlayingControls controls;
	@Inject @Getter NowPlayingInfo info;
	@Inject @Getter NowPlayingArt art;
	@Inject @Getter NowPlayingSeekbar seekbar;
	@Inject @Getter NowPlayingFab fab;

	@BindView(R.id.inner_container) LinearLayout innerContainerView;
	@BindView(R.id.appbar) AppBarLayout appbar;
	@BindView(R.id.shared_list) @Nullable ImageView listView;
	@BindView(R.id.shared_list_background) @Nullable View listBackground;

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

		if (WindowUtils.isPortrait(this)) {
			listView.setImageBitmap(listBitmap);
			transition.setSharedViewSets(
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
			transition.setSharedViewSets(
					SharedViewSet.translating("art", art.getArtView(), art.getSharedArtView()));
		}

		transition.setEnterFadingViews(innerContainerView, appbar, fab.getView());
		transition.setExitPortraitFadingViews(innerContainerView, appbar, fab.getView());
		transition.setExitLandscapeFadingViews(innerContainerView, fab.getView());

		if (savedInstanceState == null) transition.beginEnterTransition(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		presenter.unbind();
	}

	@Override
	public void onBackPressed() {
		transition.beginExitTransition(this);
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
