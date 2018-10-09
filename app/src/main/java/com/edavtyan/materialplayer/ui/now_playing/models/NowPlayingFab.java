package com.edavtyan.materialplayer.ui.now_playing.models;

import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.animation.CircularRevealAnimation;
import com.edavtyan.materialplayer.ui.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.ui.now_playing.NowPlayingPresenter;

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

	public FloatingActionButton getView() {
		return fab;
	}

	public void show() {
		if (CircularRevealAnimation.isSupported()) {
			fab.animate().alpha(1).setStartDelay(400).setDuration(100);
		} else {
			fab.animate().scaleX(1).scaleY(1);
		}
	}

	public void hide() {
		if (CircularRevealAnimation.isSupported()) {
			fab.animate().alpha(0).setStartDelay(0).setDuration(200);
		} else {
			fab.animate().scaleX(0).scaleY(0);
		}
	}

	public int getCenterX() {
		return (int) fab.getX() + getRadius();
	}

	public int getCenterY() {
		return (int) fab.getY() - getRadius();
	}

	public int getRadius() {
		return fab.getWidth() / 2;
	}
}
