package com.edavtyan.materialplayer.ui.now_playing_queue;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer.ui.lists.lib.ListFragment;
import com.edavtyan.materialplayer.ui.lists.lib.ListView;

import javax.inject.Inject;

import butterknife.BindView;

public class NowPlayingQueueFragment extends ListFragment implements ListView {

	@BindView(R.id.list) RecyclerView list;
	@BindView(R.id.root) FrameLayout rootView;
	@BindView(R.id.background) View backgroundView;

	@Inject NowPlayingQueuePresenter presenter;
	@Inject NowPlayingQueueAdapter adapter;
	@Inject ScreenThemeModule themeModule;

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

	protected NowPlayingQueueDIComponent getComponent() {
		return DaggerNowPlayingQueueDIComponent
				.builder()
				.appDIComponent(((App) getActivity().getApplication()).getAppComponent())
				.nowPlayingQueueDIModule(new NowPlayingQueueDIModule(this))
				.build();
	}
}
