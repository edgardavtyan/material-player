package com.edavtyan.materialplayer.components.track_mvp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.R;

public class TrackListAdapter
		extends RecyclerView.Adapter<TrackListViewHolder>
		implements TrackListViewHolder.OnHolderMenuItemClickListener,
				   TrackListViewHolder.OnHolderClickListener {

	private final Context context;
	private final TrackListMvp.Presenter presenter;

	public TrackListAdapter(Context context, TrackListMvp.Presenter presenter) {
		this.context = context;
		this.presenter = presenter;
	}

	@Override
	public TrackListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater
				= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.listitem_track, parent, false);
		return new TrackListViewHolder(context, view);
	}

	@Override
	public void onBindViewHolder(TrackListViewHolder holder, int position) {
		presenter.bindViewHolder(holder, position);
		holder.setOnHolderClickListener(this);
		holder.setOnHolderMenuItemClickListener(this);
	}

	@Override
	public int getItemCount() {
		return presenter.getItemCount();
	}

	@Override
	public void onHolderClick(TrackListViewHolder holder) {
		presenter.onHolderClick(holder.getAdapterPosition());
	}

	@Override
	public void onAddToPlaylistMenuItemClick(TrackListViewHolder holder) {
		presenter.onAddToPlaylist(holder.getAdapterPosition());
	}
}
