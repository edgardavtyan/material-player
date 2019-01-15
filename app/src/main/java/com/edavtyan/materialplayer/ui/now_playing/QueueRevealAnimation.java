package com.edavtyan.materialplayer.ui.now_playing;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.ed.libsutils.utils.WindowUtils;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.animation.CircularRevealAnimation;
import com.edavtyan.materialplayer.ui.now_playing_queue.NowPlayingQueueFragment;

public class QueueRevealAnimation {
	private final NowPlayingActivity activity;
	private final FloatingActionButton fab;
	private final RecyclerView queueList;
	private final FrameLayout queueRoot;

	private CircularRevealAnimation circularRevealAnimation;

	public QueueRevealAnimation(NowPlayingActivity activity, NowPlayingQueueFragment queueFragment) {
		assert queueFragment.getView() != null;

		this.activity = activity;

		fab = (FloatingActionButton) activity.findViewById(R.id.fab);
		queueList = (RecyclerView) queueFragment.getView().findViewById(R.id.list);
		queueRoot = (FrameLayout) queueFragment.getView();
	}

	public void show() {
		fab.setVisibility(View.VISIBLE);
		if (CircularRevealAnimation.isSupported()) {
			if (circularRevealAnimation == null) {
				int x = (int) fab.getX() + fab.getWidth() / 2;
				int y = (int) fab.getY() - fab.getWidth() / 2;
				int startRadius = fab.getWidth() / 2;
				int endRadius = Math.max(WindowUtils.getScreenHeight(activity), WindowUtils.getScreenWidth(activity));
				circularRevealAnimation = new CircularRevealAnimation(queueRoot, x, y, startRadius, endRadius);
			}

			fab.animate().alpha(0).setStartDelay(0).setDuration(200)
			   .withEndAction(() -> fab.setVisibility(View.INVISIBLE));
			queueList.animate().alpha(1);
			circularRevealAnimation.setDuration(300);
			circularRevealAnimation.show();
		} else {
			fab.animate().scaleX(0).scaleY(0)
			   .withEndAction(() -> fab.setVisibility(View.INVISIBLE));
			queueRoot.setAlpha(0);
			queueRoot.setVisibility(View.VISIBLE);
			queueRoot.animate().alpha(1);
		}
	}

	public void hide() {
		fab.setVisibility(View.VISIBLE);
		if (CircularRevealAnimation.isSupported()) {
			fab.animate().alpha(1).setStartDelay(100).setDuration(200);
			queueList.animate().alpha(0);
			circularRevealAnimation.setDuration(300);
			circularRevealAnimation.hide();
		} else {
			fab.animate().scaleX(1).scaleY(1);
			queueRoot.animate().alpha(0).withEndAction(() -> queueRoot.setVisibility(View.INVISIBLE));
		}
	}
}
