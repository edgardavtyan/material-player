package com.edavtyan.materialplayer.components.search.artist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchArtistAdapter extends TestableRecyclerAdapter<SearchArtistViewHolder> {
	private final Context context;
	private final List<Artist> artists;

	public SearchArtistAdapter(Context context) {
		this.context = context;
		this.artists = new ArrayList<>();
	}

	@Override
	public SearchArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View itemView = inflater.inflate(R.layout.listitem_track_compact, parent, false);
		return new SearchArtistViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(SearchArtistViewHolder holder, int position) {
		holder.setTitle(artists.get(position).getTitle());
	}

	@Override
	public int getItemCount() {
		return artists.size();
	}

	public void updateSearchResults(List<Artist> artists) {
		this.artists.clear();
		this.artists.addAll(artists);
		notifyDataSetChanged();
	}
}
