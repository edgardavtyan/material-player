package com.edavtyan.materialplayer.components.now_playing_queue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;

public class PlaylistAdapter extends TestableRecyclerAdapter<PlaylistViewHolder>
		implements PlaylistViewHolder.OnHolderClickListener,
				   PlaylistViewHolder.OnMenuClickListener {

	private final Context context;
	private final PlaylistMvp.Presenter presenter;

	public PlaylistAdapter(Context context, PlaylistMvp.Presenter presenter) {
		this.context = context;
		this.presenter = presenter;
	}

	@Override
	public PlaylistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.listitem_track, parent, false);

		PlaylistViewHolder holder = new PlaylistViewHolder(context, view);
		holder.setOnHolderClickListener(this);
		holder.setOnMenuClickListener(this);
		return holder;
	}

	@Override
	public void onBindViewHolder(PlaylistViewHolder holder, int position) {
		presenter.onBindViewHolder(holder, position);
	}

	@Override
	public int getItemCount() {
		return presenter.getItemCount();
	}

	@Override
	public void onHolderClick(PlaylistViewHolder holder) {
		presenter.onItemClick(holder.getAdapterPositionNonFinal());
	}

	@Override
	public void onRemoveFromQueueClick(PlaylistViewHolder holder) {
		presenter.onRemoveItemClick(holder.getAdapterPositionNonFinal());
		notifyItemRemovedNonFinal(holder.getAdapterPositionNonFinal());
	}
}
