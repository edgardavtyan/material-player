package com.edavtyan.materialplayer.components.now_playing.models;

import android.support.design.widget.FloatingActionButton;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingMvp;

public class NowPlayingFab implements NowPlayingMvp.View.Fab {
	public NowPlayingFab(NowPlayingActivity activity, NowPlayingMvp.Presenter presenter) {
		FloatingActionButton fab = activity.findView(R.id.fab);
		fab.setOnClickListener((view) -> presenter.onFabClick());
	}
}
