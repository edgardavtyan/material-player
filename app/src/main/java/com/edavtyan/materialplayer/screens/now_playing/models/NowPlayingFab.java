package com.edavtyan.materialplayer.screens.now_playing.models;

import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.screens.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.screens.now_playing.NowPlayingPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NowPlayingFab implements View.OnClickListener {
	@BindView(R.id.fab) FloatingActionButton fab;

	private final NowPlayingPresenter presenter;

	public NowPlayingFab(NowPlayingActivity activity, NowPlayingPresenter presenter) {
		this.presenter = presenter;
		ButterKnife.bind(this, activity);
		fab.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		presenter.onFabClick();
	}
}