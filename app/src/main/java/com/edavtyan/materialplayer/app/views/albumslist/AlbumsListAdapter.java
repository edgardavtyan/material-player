package com.edavtyan.materialplayer.app.views.albumslist;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.models.album.AlbumsProvider;
import com.edavtyan.materialplayer.app.models.track.Track;
import com.edavtyan.materialplayer.app.models.track.TracksProvider;
import com.edavtyan.materialplayer.app.views.albumdetail.AlbumDetailActivity;
import com.edavtyan.materialplayer.app.views.lib.adapters.RecyclerServiceCursorAdapter;

import java.util.List;

public class AlbumsListAdapter extends RecyclerServiceCursorAdapter<AlbumsListViewHolder> {
	private final AlbumsProvider albumsProvider;
	private final TracksProvider tracksProvider;

	//---

	public AlbumsListAdapter(Context context, Cursor cursor) {
		super(context, cursor);
		albumsProvider = new AlbumsProvider(context);
		tracksProvider = new TracksProvider(context);
	}

	//---

	@Override
	public AlbumsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.listitem_album, parent, false);
		AlbumsListViewHolder holder = new AlbumsListViewHolder(context, view);

		holder.setOnClickListener(itemView -> {
			cursor.moveToPosition(holder.getAdapterPosition());
			AlbumDetailActivity.startActivity(context, albumsProvider.getId(cursor));
		});

		holder.setOnMenuItemClickListener(item -> {
			switch (item.getItemId()) {
			case R.id.menu_addToPlaylist:
				cursor.moveToPosition(holder.getAdapterPosition());
				int albumId = albumsProvider.getId(cursor);
				List<Track> tracks = tracksProvider.getAllTracksWithAlbumId(albumId);
				service.getQueue().addAll(tracks);

			default:
				return false;
			}
		});

		return holder;
	}

	@Override
	public void onBindViewHolder(AlbumsListViewHolder holder, int position) {
		super.onBindViewHolder(holder, position);
		holder.setTitle(albumsProvider.getTitle(cursor));
		holder.setInfo(albumsProvider.getTracksCount(cursor), albumsProvider.getArtist(cursor));
		holder.setArt(albumsProvider.getArtPath(cursor));
	}
}
