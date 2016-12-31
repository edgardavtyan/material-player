package com.edavtyan.materialplayer.components.now_playing_queue;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.mvp.list.ListAdapter;

public class NowPlayingQueueAdapter
		extends ListAdapter<NowPlayingQueueViewHolder>
		implements NowPlayingQueueViewHolder.OnHolderClickListener,
				   NowPlayingQueueViewHolder.OnMenuClickListener {

	private final NowPlayingQueueMvp.Presenter presenter;

	public NowPlayingQueueAdapter(Context context, NowPlayingQueueMvp.Presenter presenter) {
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
	public NowPlayingQueueViewHolder onCreateViewHolder(Context context, View view) {
		NowPlayingQueueViewHolder holder = new NowPlayingQueueViewHolder(context, view);
		holder.setOnHolderClickListener(this);
		holder.setOnMenuClickListener(this);
		return holder;
	}

	@Override
	public void onHolderClick(NowPlayingQueueViewHolder holder) {
		presenter.onItemClick(holder.getAdapterPositionNonFinal());
	}

	@Override
	public void onRemoveFromQueueClick(NowPlayingQueueViewHolder holder) {
		presenter.onRemoveItemClick(holder.getAdapterPositionNonFinal());
		notifyItemRemovedNonFinal(holder.getAdapterPositionNonFinal());
	}
}
