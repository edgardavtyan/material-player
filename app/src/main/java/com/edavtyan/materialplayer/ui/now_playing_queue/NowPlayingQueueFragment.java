package com.edavtyan.materialplayer.ui.now_playing_queue;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.ed.libsutils.utils.WindowUtils;
import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.animation.CircularRevealAnimation;
import com.edavtyan.materialplayer.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer.ui.lists.lib.ListFragment;
import com.edavtyan.materialplayer.ui.lists.lib.ListView;

import javax.inject.Inject;

import butterknife.BindView;

public class NowPlayingQueueFragment extends ListFragment implements ListView {

	@BindView(R.id.list) RecyclerView list;
	@BindView(R.id.root) FrameLayout rootView;

	@Inject ScreenThemeModule themeModule;
	@Inject NowPlayingQueuePresenter presenter;
	@Inject NowPlayingQueueAdapter adapter;

	private CircularRevealAnimation circularRevealAnimation;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getComponent().inject(this);
		addModule(themeModule);

		initListView(presenter, adapter);

		adapter.setHasStableIds(true);
		presenter.onCreate();
	}

	@Override
	public boolean isBackgroundEnabled() {
		return true;
	}

	@Override
	public void onCreateView(View view) {
		super.onCreateView(view);
		rootView.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		presenter.onDestroy();
	}

	public void removeItem(int position) {
		adapter.notifyItemRemoved(position);
	}

	public void show(int x, int y, int radius) {
		if (CircularRevealAnimation.isSupported()) {
			if (circularRevealAnimation == null) {
				int endRadius = WindowUtils.getScreenHeight(getActivity());
				circularRevealAnimation = new CircularRevealAnimation(rootView, x, y, radius, endRadius);
				circularRevealAnimation.setDuration(500);
			}

			list.animate().alpha(1);
			circularRevealAnimation.show();
		} else {
			rootView.setAlpha(0);
			rootView.setVisibility(View.VISIBLE);
			rootView.animate().alpha(1);
		}
	}

	public void hide() {
		if (CircularRevealAnimation.isSupported()) {
			circularRevealAnimation.hide();
			list.animate().alpha(0);
		} else {
			rootView.animate().alpha(0).withEndAction(() -> rootView.setVisibility(View.INVISIBLE));
		}
	}

	protected NowPlayingQueueDIComponent getComponent() {
		return DaggerNowPlayingQueueDIComponent
				.builder()
				.appDIComponent(((App) getActivity().getApplication()).getAppComponent())
				.nowPlayingQueueDIModule(new NowPlayingQueueDIModule(this))
				.build();
	}
}
