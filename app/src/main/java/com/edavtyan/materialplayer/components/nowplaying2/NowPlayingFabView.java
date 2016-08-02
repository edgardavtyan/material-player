package com.edavtyan.materialplayer.components.nowplaying2;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.edavtyan.materialplayer.R;

public class NowPlayingFabView implements View.OnClickListener {
	private final FloatingActionButton fab;
	private final NowPlayingPresenter presenter;

	public NowPlayingFabView(Activity activity, NowPlayingPresenter presenter) {
		this.presenter = presenter;
		fab = (FloatingActionButton) activity.findViewById(R.id.fab);
		fab.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		presenter.openPlaylist();
	}
}
