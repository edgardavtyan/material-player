package com.edavtyan.materialplayer.app.views.artistslist;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.models.artist.ArtistsProvider;
import com.edavtyan.materialplayer.app.views.artistdetail.ArtistDetailActivity;
import com.edavtyan.materialplayer.app.views.lib.adapters.RecyclerViewCursorAdapter;

public class ArtistsListAdapter extends RecyclerViewCursorAdapter<ArtistsListViewHolder> {
	private final ArtistsProvider artistsProvider;
	private final Context context;

	public ArtistsListAdapter(Context context, Cursor cursor) {
		super(context, cursor);
		artistsProvider = new ArtistsProvider(context);
		this.context = context;
	}

	//---

	@Override
	public ArtistsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.listitem_artist, parent, false);
		return new ArtistsListViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ArtistsListViewHolder holder, int position) {
		super.onBindViewHolder(holder, position);
		holder.setTitle(artistsProvider.getTitle(cursor));
		holder.setInfo(artistsProvider.getAlbumsCount(cursor), artistsProvider.getTracksCount(cursor));
		holder.setOnClickListener(view -> {
			cursor.moveToPosition(holder.getAdapterPosition());
			String artistTitle = artistsProvider.getTitle(cursor);
			Intent i = new Intent(context, ArtistDetailActivity.class);
			i.putExtra(ArtistDetailActivity.EXTRA_ARTIST_NAME, artistTitle);
			context.startActivity(i);
		});
	}
}
