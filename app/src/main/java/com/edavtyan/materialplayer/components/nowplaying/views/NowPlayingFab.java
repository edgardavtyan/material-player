package com.edavtyan.materialplayer.components.nowplaying.views;

import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.nowplaying.NowPlayingPresenter;
import com.edavtyan.materialplayer.lib.activities.BaseActivity;

public class NowPlayingFab implements View.OnClickListener {
	private final FloatingActionButton fab;
	private final NowPlayingPresenter presenter;

	public NowPlayingFab(BaseActivity activity, NowPlayingPresenter presenter) {
		this.presenter = presenter;
		fab = activity.findView(R.id.fab);
		fab.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		presenter.openPlaylist();
	}
}
