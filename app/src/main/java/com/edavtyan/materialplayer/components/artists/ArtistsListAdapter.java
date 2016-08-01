package com.edavtyan.materialplayer.components.artists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.lib.models.CursorDB;

public class ArtistsListAdapter extends RecyclerViewCursorAdapter<ArtistsListViewHolder> {
	private final ArtistDB artistDB;
	private final Context context;

	public ArtistsListAdapter(Context context, ArtistDB artistDB) {
		super(context);
		this.artistDB = artistDB;
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
		Artist artist = artistDB.getArtist(position);
		holder.setTitle(artist.getTitle());
		holder.setInfo(artist.getAlbumsCount(), artist.getTracksCount());
		holder.setOnClickListener(view -> {
			ArtistDetailActivity.startActivity(context, artist.getTitle());
		});
	}

	//---

	@Override
	public CursorDB getDB() {
		return artistDB;
	}
}
