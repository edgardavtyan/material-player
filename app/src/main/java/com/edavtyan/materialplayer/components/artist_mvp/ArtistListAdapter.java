package com.edavtyan.materialplayer.components.artist_mvp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.R;

public class ArtistListAdapter
		extends RecyclerView.Adapter<ArtistsListViewHolder>
		implements ArtistsListViewHolder.OnHolderClickListener {

	private final ArtistListMvp.Presenter presenter;
	private final Context context;

	public ArtistListAdapter(Context context, ArtistListMvp.Presenter presenter) {
		this.context = context;
		this.presenter = presenter;
	}

	@Override
	public ArtistsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = View.inflate(context, R.layout.listitem_artist, parent);
		return new ArtistsListViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ArtistsListViewHolder holder, int position) {
		presenter.bindViewHolder(holder, position);
		holder.setOnHolderClickListener(this);
	}

	@Override
	public int getItemCount() {
		return presenter.getItemCount();
	}

	@Override
	public void onHolderClick(int position) {
		presenter.onHolderClicked(position);
	}
}
