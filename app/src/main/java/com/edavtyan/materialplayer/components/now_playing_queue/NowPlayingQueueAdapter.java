package com.edavtyan.materialplayer.components.now_playing_queue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;

public class NowPlayingQueueAdapter extends TestableRecyclerAdapter<NowPlayingQueueViewHolder>
		implements NowPlayingQueueViewHolder.OnHolderClickListener,
				   NowPlayingQueueViewHolder.OnMenuClickListener {

	private final Context context;
	private final NowPlayingQueueMvp.Presenter presenter;

	public NowPlayingQueueAdapter(Context context, NowPlayingQueueMvp.Presenter presenter) {
		this.context = context;
		this.presenter = presenter;
	}

	@Override
	public NowPlayingQueueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(viewType, parent, false);

		NowPlayingQueueViewHolder holder = new NowPlayingQueueViewHolder(context, view);
		holder.setOnHolderClickListener(this);
		holder.setOnMenuClickListener(this);
		return holder;
	}

	@Override
	public void onBindViewHolder(NowPlayingQueueViewHolder holder, int position) {
		presenter.onBindViewHolder(holder, position);
	}

	@Override
	public int getItemCount() {
		return presenter.getItemCount();
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
