package com.edavtyan.materialplayer.components.artist_mvp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.R;

public class ArtistListAdapter
		extends RecyclerView.Adapter<ArtistListViewHolder>
		implements ArtistListViewHolder.OnHolderClickListener {

	private final ArtistListMvp.Presenter presenter;
	private final Context context;

	public ArtistListAdapter(Context context, ArtistListMvp.Presenter presenter) {
		this.context = context;
		this.presenter = presenter;
	}

	@Override
	public ArtistListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.listitem_artist, parent, false);
		ArtistListViewHolder holder = new ArtistListViewHolder(view);
		holder.setOnHolderClickListener(this);
		return holder;
	}

	@Override
	public void onBindViewHolder(ArtistListViewHolder holder, int position) {
		presenter.bindViewHolder(holder, position);
	}

	@Override
	public int getItemCount() {
		return presenter.getItemCount();
	}

	@Override
	public void onHolderClick(ArtistListViewHolder holder, int position) {
		presenter.onHolderClicked(holder, position);
	}
}
