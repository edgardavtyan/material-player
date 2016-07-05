package com.edavtyan.materialplayer.components.tracks.list;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.nowplaying.NowPlayingActivity;
import com.edavtyan.materialplayer.components.tracks.Track;
import com.edavtyan.materialplayer.components.tracks.TrackDB;
import com.edavtyan.materialplayer.lib.adapters.RecyclerServiceCursorAdapter;

public abstract class TracksAdapter<THolder extends TracksViewHolder>
		extends RecyclerServiceCursorAdapter<THolder> {
	protected final TrackDB trackDB;

	//---

	public TracksAdapter(Context context, TrackDB trackDB) {
		super(context, null);
		this.trackDB = trackDB;
	}

	//---

	public abstract THolder createViewHolder(View view);

	//---

	@Override
	public THolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.listitem_track, parent, false);
		THolder holder = createViewHolder(view);

		holder.setOnClickListener(itemView -> {
			NowPlayingActivity.startActivity(context);
			service.getQueue().setTracks(trackDB.getAllTracks(cursor), holder.getAdapterPosition());
			service.getPlayer().prepare();
		});

		holder.setOnMenuItemClickListener(item -> {
			switch (item.getItemId()) {
			case R.id.menu_addToPlaylist:
				Track track = trackDB.getTrack(holder.getAdapterPosition());
				service.getQueue().add(trackDB.getSingleTrackWithId(track.getId()));
				return true;

			default:
				return false;
			}
		});

		return holder;
	}

	@Override
	public void onBindViewHolder(THolder holder, int position) {
		super.onBindViewHolder(holder, position);

		Track track = trackDB.getTrack(position);
		holder.setTitle(track.getTitle());
		holder.setInfo(track.getDuration(), track.getArtistTitle(), track.getAlbumTitle());
	}

	@Override
	public void swapCursor(Cursor newCursor) {
		super.swapCursor(newCursor);
		trackDB.swapCursor(newCursor);
	}
}
