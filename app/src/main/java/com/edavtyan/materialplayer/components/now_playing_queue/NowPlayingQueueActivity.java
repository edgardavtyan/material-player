package com.edavtyan.materialplayer.components.now_playing_queue;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.base.BaseToolbarActivity;

public class NowPlayingQueueActivity
		extends BaseToolbarActivity
		implements NowPlayingQueueMvp.View {

	private NowPlayingQueueMvp.Presenter presenter;
	private NowPlayingQueueAdapter adapter;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		NowPlayingQueueFactory factory = getFactory();
		presenter = factory.providePresenter();
		adapter = factory.provideAdapter();

		RecyclerView list = findView(R.id.list);
		list.setLayoutManager(new LinearLayoutManager(this));
		list.setAdapter(adapter);

		presenter.onCreate();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		presenter.onDestroy();
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_playlist;
	}

	@Override
	public void notifyDataSetChanged() {
		adapter.notifyDataSetChangedNonFinal();
	}

	protected NowPlayingQueueFactory getFactory() {
		return ((App) getApplicationContext()).getPlaylistFactory(this, this);
	}
}
