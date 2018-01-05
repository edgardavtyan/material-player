package com.edavtyan.materialplayer.components.now_playing;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingArt;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingControls;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingInfo;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingSeekbar;
import com.edavtyan.materialplayer.lib.base.BaseToolbarActivity;

import lombok.Getter;

public class NowPlayingActivity extends BaseToolbarActivity {
	private NowPlayingPresenter presenter;
	private Navigator navigator;

	private @Getter NowPlayingControls controls;
	private @Getter NowPlayingInfo info;
	private @Getter NowPlayingArt art;
	private @Getter NowPlayingSeekbar seekbar;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		NowPlayingFactory factory = getFactory();
		presenter = factory.getPresenter();
		controls = factory.getControls();
		info = factory.getInfo();
		art = factory.getArt();
		seekbar = factory.getSeekbar();
		navigator = factory.getNavigator();
		factory.getFab();

		presenter.bind();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		presenter.unbind();
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_nowplaying;
	}

	@Override
	protected int getToolbarTitleStringId() {
		return R.string.nowplaying_toolbar_title;
	}

	public void gotoPlaylistScreen() {
		navigator.gotoNowPlayingQueue(this);
	}

	protected NowPlayingFactory getFactory() {
		App app = (App) getApplicationContext();
		return app.getNowPlayingFactory(this);
	}
}
