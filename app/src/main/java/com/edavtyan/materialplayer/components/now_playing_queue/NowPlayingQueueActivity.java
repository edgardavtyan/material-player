package com.edavtyan.materialplayer.components.now_playing_queue;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.lists.lib.ListView;
import com.edavtyan.materialplayer.lib.AnimatingLinearLayoutManager;
import com.edavtyan.materialplayer.lib.base.BaseToolbarActivity;

import javax.inject.Inject;

import butterknife.BindView;

public class NowPlayingQueueActivity extends BaseToolbarActivity implements ListView {

	@BindView(R.id.list) RecyclerView list;

	@Inject NowPlayingQueuePresenter presenter;
	@Inject NowPlayingQueueAdapter adapter;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getComponent().inject(this);

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

	public void removeItem(int position) {
		adapter.notifyItemRemoved(position);
	}

	protected NowPlayingQueueComponent getComponent() {
		return DaggerNowPlayingQueueComponent
				.builder()
				.nowPlayingQueueModule(new NowPlayingQueueModule(this))
				.build();
	}
}
