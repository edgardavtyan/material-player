package com.edavtyan.materialplayer.components.now_playing;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingArt;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingControls;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingFab;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingInfo;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingSeekbar;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtModule;
import com.edavtyan.materialplayer.lib.base.BaseToolbarActivity;

import javax.inject.Inject;

import lombok.Getter;

public class NowPlayingActivity extends BaseToolbarActivity {
	@Inject NowPlayingPresenter presenter;
	@Inject Navigator navigator;

	@Inject @Getter NowPlayingControls controls;
	@Inject @Getter NowPlayingInfo info;
	@Inject @Getter NowPlayingArt art;
	@Inject @Getter NowPlayingSeekbar seekbar;
	@Inject @Getter NowPlayingFab fab;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getComponent().inject(this);

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

	protected NowPlayingComponent getComponent() {
		return DaggerNowPlayingComponent
				.builder()
				.nowPlayingModule(new NowPlayingModule(this))
				.build();
	}
}
