package com.edavtyan.materialplayer.components.now_playing.models;

import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingMvp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NowPlayingFab implements NowPlayingMvp.View.Fab, View.OnClickListener {
	@BindView(R.id.fab) FloatingActionButton fab;

	private final NowPlayingMvp.Presenter presenter;

	public NowPlayingFab(NowPlayingActivity activity, NowPlayingMvp.Presenter presenter) {
		this.presenter = presenter;
		ButterKnife.bind(this, activity);
		fab.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		presenter.onFabClick();
	}
}
