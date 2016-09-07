package com.edavtyan.materialplayer.components.album_all;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;

public class AlbumListAdapter
		extends TestableRecyclerAdapter<AlbumListViewHolder>
		implements AlbumListViewHolder.OnHolderClickListener,
				   AlbumListViewHolder.OnHolderMenuItemClickListener {

	private final Context context;
	private final AlbumListMvp.Presenter presenter;

	public AlbumListAdapter(Context context, AlbumListMvp.Presenter presenter) {
		this.context = context;
		this.presenter = presenter;
	}

	@Override
	public AlbumListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.listitem_album, parent, false);
		return new AlbumListViewHolder(context, view);
	}

	@Override
	public void onBindViewHolder(AlbumListViewHolder holder, int position) {
		presenter.onBindViewHolder(holder, position);
		holder.setOnHolderClickListener(this);
		holder.setOnHolderMenuItemClickListener(this);
	}

	@Override
	public int getItemCount() {
		return presenter.getItemCount();
	}

	@Override
	public void onHolderClick(AlbumListViewHolder holder) {
		presenter.onHolderClick(holder.getAdapterPositionNonFinal());
	}

	@Override
	public void onMenuAddToPlaylistClick(AlbumListViewHolder holder) {
		presenter.onAddToPlaylist(holder.getAdapterPositionNonFinal());
	}
}
