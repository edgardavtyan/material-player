package com.edavtyan.materialplayer.app.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.views.activities.AlbumActivity;
import com.edavtyan.materialplayer.app.lib.adapters.RecyclerServiceCursorAdapter;
import com.edavtyan.materialplayer.app.models.columns.AlbumColumns;
import com.edavtyan.materialplayer.app.models.data.Track;
import com.edavtyan.materialplayer.app.models.providers.ArtProvider;
import com.edavtyan.materialplayer.app.models.providers.TracksProvider;

import java.util.List;

public class AlbumsAdapter extends RecyclerServiceCursorAdapter<AlbumsAdapter.AlbumViewHolder> {
	public AlbumsAdapter(Context context, Cursor cursor) {
		super(context, cursor);
	}

	/*
	 * ViewHolder
	 */

	public class AlbumViewHolder
			extends RecyclerView.ViewHolder
			implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
		private final TextView titleTextView;
		private final TextView infoTextView;
		private final ImageView artImageView;
		private final ImageButton menuButton;

		public AlbumViewHolder(View itemView) {
			super(itemView);
			itemView.setOnClickListener(this);

			titleTextView = (TextView) itemView.findViewById(R.id.title);
			infoTextView = (TextView) itemView.findViewById(R.id.info);
			artImageView = (ImageView) itemView.findViewById(R.id.art);
			menuButton = (ImageButton) itemView.findViewById(R.id.menu);

			PopupMenu popupMenu = new PopupMenu(context, menuButton);
			popupMenu.inflate(R.menu.menu_track);
			popupMenu.setOnMenuItemClickListener(this);
			menuButton.setOnClickListener(view -> popupMenu.show());
		}

		@Override
		public void onClick(View view) {
			cursor.moveToPosition(getAdapterPosition());
			Intent i = new Intent(context, AlbumActivity.class);
			i.putExtra(AlbumActivity.EXTRA_ALBUM_ID, cursor.getInt(AlbumColumns.ID));
			context.startActivity(i);
		}

		@Override
		public boolean onMenuItemClick(MenuItem menuItem) {
			switch (menuItem.getItemId()) {
			case R.id.menu_addToPlaylist:
				cursor.moveToPosition(getAdapterPosition());
				int albumId = cursor.getInt(AlbumColumns.ID);
				List<Track> tracks = TracksProvider.allWithAlbumId(albumId, context);
				service.getPlayer().getQueue().addAll(tracks);

			default:
				return false;
			}
		}
	}

	/*
	 * RecyclerViewCursorAdapter
	 */

	@Override
	public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.listitem_album, parent, false);
		return new AlbumViewHolder(view);
	}

	@Override
	public void onBindViewHolder(AlbumViewHolder holder, int position) {
		super.onBindViewHolder(holder, position);
		holder.titleTextView.setText(cursor.getString(AlbumColumns.TITLE));
		holder.infoTextView.setText(getAlbumInfo(cursor));

		String artPath = cursor.getString(AlbumColumns.ART);
		Glide.with(context)
				.load(ArtProvider.fromPath(artPath))
				.error(R.drawable.fallback_cover_listitem)
				.into(holder.artImageView);
	}

	/*
	 * Private methods
	 */

	private String getAlbumInfo(Cursor cursor) {
		Resources res = context.getResources();

		String tracksCount = res.getQuantityString(
				R.plurals.tracks,
				cursor.getInt(AlbumColumns.ARTIST),
				cursor.getInt(AlbumColumns.SONGS_COUNT));

		return res.getString(
				R.string.pattern_album_info,
				cursor.getString(AlbumColumns.ARTIST),
				tracksCount);
	}
}
