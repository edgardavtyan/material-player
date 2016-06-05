package com.edavtyan.materialplayer.app.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.lib.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.app.models.providers.ArtistsProvider;
import com.edavtyan.materialplayer.app.views.activities.ArtistActivity;

public class ArtistsAdapter extends RecyclerViewCursorAdapter<ArtistsAdapter.ArtistViewHolder> {
	private final ArtistsProvider artistsProvider;

	public ArtistsAdapter(Context context, Cursor cursor) {
		super(context, cursor);
		artistsProvider = new ArtistsProvider(context);
	}

	/*
	 * ViewHolder
	 */

	public class ArtistViewHolder
			extends RecyclerView.ViewHolder
			implements View.OnClickListener {
		private final TextView titleTextView;
		private final TextView countsTextView;

		public ArtistViewHolder(View itemView) {
			super(itemView);
			itemView.setOnClickListener(this);

			titleTextView = (TextView) itemView.findViewById(R.id.title);
			countsTextView = (TextView) itemView.findViewById(R.id.info);
		}

		@Override
		public void onClick(View view) {
			cursor.moveToPosition(getAdapterPosition());
			String artistTitle = artistsProvider.getTitle(cursor);

			Intent i = new Intent(itemView.getContext(), ArtistActivity.class);
			i.putExtra(ArtistActivity.EXTRA_ARTIST_NAME, artistTitle);

			itemView.getContext().startActivity(i);
		}
	}


	/*
	 * RecyclerViewCursorAdapter
	 */

	@Override
	public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.listitem_artist, parent, false);
		return new ArtistViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ArtistViewHolder holder, int position) {
		super.onBindViewHolder(holder, position);
		holder.titleTextView.setText(artistsProvider.getTitle(cursor));
		holder.countsTextView.setText(getArtistInfo(cursor));
	}

	/*
	 * Private methods
	 */

	private String getArtistInfo(Cursor cursor) {
		Resources res = context.getResources();
		int albumsCount = artistsProvider.getAlbumsCount(cursor);
		int tracksCount = artistsProvider.getTracksCount(cursor);

		String albumsCountStr = res.getQuantityString(R.plurals.albums, albumsCount, albumsCount);
		String tracksCountStr = res.getQuantityString(R.plurals.tracks, tracksCount, tracksCount);

		return res.getString(R.string.pattern_artist_info, albumsCountStr, tracksCountStr);
	}
}
