package com.edavtyan.materialplayer.components.now_playing_queue;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.AnimatingLinearLayoutManager;
import com.edavtyan.materialplayer.lib.base.BaseToolbarActivity;

import butterknife.BindView;

public class NowPlayingQueueActivity
		extends BaseToolbarActivity
		implements NowPlayingQueueMvp.View {

	@BindView(R.id.list) RecyclerView list;

	private NowPlayingQueueMvp.Presenter presenter;
	private NowPlayingQueueAdapter adapter;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		App app = ((App) getApplicationContext());
		NowPlayingQueueFactory factory = app.getPlaylistFactory(this, this);
		presenter = factory.getPresenter();
		adapter = factory.getAdapter();
		adapter.setHasStableIds(true);

		list.setLayoutManager(new AnimatingLinearLayoutManager(this));
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

	@Override
	public void removeItem(int position) {
		adapter.notifyItemRemoved(position);
	}
}
