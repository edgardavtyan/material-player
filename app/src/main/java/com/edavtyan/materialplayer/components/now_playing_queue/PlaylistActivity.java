package com.edavtyan.materialplayer.components.now_playing_queue;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.base.BaseToolbarActivity;

public class PlaylistActivity extends BaseToolbarActivity implements PlaylistMvp.View {
	private PlaylistMvp.Presenter presenter;
	private PlaylistAdapter adapter;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		PlaylistFactory factory = getFactory();
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
	public void update() {
		adapter.notifyDataSetChangedNonFinal();
	}

	protected PlaylistFactory getFactory() {
		return ((App) getApplicationContext()).getPlaylistFactory(this, this);
	}
}
