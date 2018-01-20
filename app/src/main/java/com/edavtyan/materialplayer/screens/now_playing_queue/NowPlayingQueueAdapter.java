package com.edavtyan.materialplayer.screens.now_playing_queue;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.screens.lists.lib.ListAdapter;

public class NowPlayingQueueAdapter extends ListAdapter<NowPlayingQueueViewHolder> {

	private final NowPlayingQueuePresenter presenter;

	public NowPlayingQueueAdapter(Context context, NowPlayingQueuePresenter presenter) {
		super(context, presenter);
		this.presenter = presenter;
	}

	@Override
	public int getNormalLayoutId() {
		return R.layout.listitem_track;
	}

	@Override
	public int getCompactLayoutId() {
		return R.layout.listitem_track_compact;
	}

	@Override
	public long getItemId(int position) {
		return presenter.getItemId(position);
	}

	@Override
	public NowPlayingQueueViewHolder onCreateViewHolder(Context context, View view) {
		return new NowPlayingQueueViewHolder(context, view, presenter);
	}
}
