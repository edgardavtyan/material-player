package com.edavtyan.materialplayer.components.nowplaying.views;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.nowplaying.NowPlayingPresenter;

public class NowPlayingFab implements View.OnClickListener {
	private final FloatingActionButton fab;
	private final NowPlayingPresenter presenter;

	public NowPlayingFab(Activity activity, NowPlayingPresenter presenter) {
		this.presenter = presenter;
		fab = (FloatingActionButton) activity.findViewById(R.id.fab);
		fab.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		presenter.openPlaylist();
	}
}
