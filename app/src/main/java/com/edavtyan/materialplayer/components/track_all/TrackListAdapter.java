package com.edavtyan.materialplayer.components.track_all;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;

public class TrackListAdapter
		extends TestableRecyclerAdapter<TrackListViewHolder>
		implements TrackListViewHolder.OnHolderClickListener,
				   TrackListViewHolder.OnHolderMenuItemClickListener {

	private final Context context;
	private final TrackListMvp.Presenter presenter;

	public TrackListAdapter(Context context, TrackListMvp.Presenter presenter) {
		this.context = context;
		this.presenter = presenter;
	}

	@Override
	public TrackListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.listitem_track, parent, false);
		TrackListViewHolder holder = new TrackListViewHolder(context, view);
		holder.setOnHolderClickListener(this);
		holder.setOnHolderMenuItemClickListener(this);
		return holder;
	}

	@Override
	public void onBindViewHolder(TrackListViewHolder holder, int position) {
		presenter.onBindViewHolder(holder, position);
	}

	@Override
	public int getItemCount() {
		return presenter.getItemCount();
	}

	@Override
	public void onHolderClick(TrackListViewHolder holder) {
		presenter.onHolderClick(holder.getAdapterPositionNonFinal());
	}

	@Override
	public void onMenuAddToPlaylistClick(TrackListViewHolder holder) {
		presenter.onAddToPlaylist(holder.getAdapterPositionNonFinal());
	}
}
