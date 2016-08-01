package com.edavtyan.materialplayer.components.tracks;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.nowplaying.NowPlayingActivity;

public class TracksListAdapter extends TracksAdapter<TracksListViewHolder> {
	public TracksListAdapter(Context context, TrackDB trackDB) {
		super(context, trackDB);
	}

	//---

	public TracksListViewHolder createViewHolder(View view) {
		return new TracksListViewHolder(context, view);
	}

	//---

	@Override
	public TracksListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.listitem_track, parent, false);
		return createViewHolder(view);
	}

	@Override
	public void onBindViewHolder(TracksListViewHolder holder, int position) {
		super.onBindViewHolder(holder, position);

		Track track = trackDB.getTrack(position);
		holder.setTitle(track.getTitle());
		holder.setInfo(track.getDuration(), track.getArtistTitle(), track.getAlbumTitle());

		holder.setOnClickListener(view -> {
			Intent i = new Intent(context, NowPlayingActivity.class);
			context.startActivity(i);
			service.getQueue().setTracks(
					trackDB.getAllTracks(),
					holder.getAdapterPosition());
			service.getPlayer().prepare();
		});

		holder.setOnMenuItemClickListener(item -> {
			switch (item.getItemId()) {
			case R.id.menu_addToPlaylist:
				Track selectedTrack = trackDB.getTrack(holder.getAdapterPosition());
				int trackId = selectedTrack.getId();
				service.getQueue().add(trackDB.getSingleTrackWithId(trackId));
				return true;

			default:
				return false;
			}
		});
	}

	@Override
	public void swapCursor(Cursor newCursor) {
		super.swapCursor(newCursor);
		trackDB.swapCursor(newCursor);
	}
}
