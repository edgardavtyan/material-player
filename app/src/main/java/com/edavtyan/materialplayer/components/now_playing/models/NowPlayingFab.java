package com.edavtyan.materialplayer.components.now_playing.models;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingMvp;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class NowPlayingFab implements NowPlayingMvp.View.Fab {
	private final NowPlayingMvp.Presenter presenter;

	public NowPlayingFab(NowPlayingActivity activity, NowPlayingMvp.Presenter presenter) {
		this.presenter = presenter;
		ButterKnife.bind(this, activity);
	}

	@OnClick(R.id.fab)
	public void onFabClick() {
		presenter.onFabClick();
	}
}
