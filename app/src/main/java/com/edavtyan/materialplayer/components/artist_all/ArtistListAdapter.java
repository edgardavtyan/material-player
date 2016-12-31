package com.edavtyan.materialplayer.components.artist_all;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.mvp.list.ListAdapter;

public class ArtistListAdapter
		extends ListAdapter<ArtistListViewHolder>
		implements ArtistListViewHolder.OnHolderClickListener {

	private final ArtistListMvp.Presenter presenter;

	public ArtistListAdapter(Context context, ArtistListMvp.Presenter presenter) {
		super(context, presenter);
		this.presenter = presenter;
	}

	@Override
	public int getNormalLayoutId() {
		return R.layout.listitem_artist;
	}

	@Override
	public int getCompactLayoutId() {
		return R.layout.listitem_artist_compact;
	}

	@Override
	public ArtistListViewHolder onCreateViewHolder(Context context, View view) {
		ArtistListViewHolder holder = new ArtistListViewHolder(context, view);
		holder.setOnHolderClickListener(this);
		return holder;
	}

	@Override
	public void onHolderClick(ArtistListViewHolder holder) {
		presenter.onHolderClick(holder.getAdapterPositionNonFinal());
	}
}
