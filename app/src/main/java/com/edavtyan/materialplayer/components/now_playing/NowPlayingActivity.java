package com.edavtyan.materialplayer.components.now_playing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingArt;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingControls;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingFab;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingInfo;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingSeekbar;
import com.edavtyan.materialplayer.lib.base.BaseActivity;
import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityToolbarModule;

import javax.inject.Inject;

import butterknife.BindView;
import lombok.Getter;

public class NowPlayingActivity extends BaseActivity {
	@Inject NowPlayingPresenter presenter;
	@Inject Navigator navigator;

	@Inject @Getter NowPlayingControls controls;
	@Inject @Getter NowPlayingInfo info;
	@Inject @Getter NowPlayingArt art;
	@Inject @Getter NowPlayingSeekbar seekbar;
	@Inject @Getter NowPlayingFab fab;

	@BindView(R.id.inner_container) LinearLayout innerContainerView;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addModule(new ActivityToolbarModule(this, R.string.nowplaying_toolbar_title));
		getComponent().inject(this);
		presenter.bind();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		presenter.unbind();
	}

	@Override
	public void onThemeChanged(ThemeColors colors) {
		super.onThemeChanged(colors);
		innerContainerView.setBackgroundColor(colors.getColorPrimary());
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_nowplaying;
	}

	public void gotoPlaylistScreen() {
		navigator.gotoNowPlayingQueue(this);
	}

	protected NowPlayingComponent getComponent() {
		return DaggerNowPlayingComponent
				.builder()
				.nowPlayingModule(new NowPlayingModule(this))
				.build();
	}
}
