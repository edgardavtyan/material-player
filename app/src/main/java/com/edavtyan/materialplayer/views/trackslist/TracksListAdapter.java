package com.edavtyan.materialplayer.views.trackslist;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.models.track.TracksProvider;
import com.edavtyan.materialplayer.views.nowplaying.NowPlayingActivity;

public class TracksListAdapter extends TracksAdapter<TracksListViewHolder> {
	protected final TracksProvider tracksProvider;

	//---

	public TracksListAdapter(Context context, Cursor cursor) {
		super(context, cursor);
		tracksProvider = new TracksProvider(context);
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
		holder.setTitle(tracksProvider.getTitle(cursor));
		holder.setInfo(tracksProvider.getDuration(cursor), tracksProvider.getArtist(cursor),
				tracksProvider.getAlbum(cursor));

		holder.setOnClickListener(view -> {
			Intent i = new Intent(context, NowPlayingActivity.class);
			context.startActivity(i);
			service.getQueue().setTracks(
					tracksProvider.getAllTracks(cursor),
					holder.getAdapterPosition());
			service.getPlayer().prepare();
		});

		holder.setOnMenuItemClickListener(item -> {
			switch (item.getItemId()) {
			case R.id.menu_addToPlaylist:
				cursor.moveToPosition(holder.getAdapterPosition());
				int trackId = tracksProvider.getId(cursor);
				service.getQueue().add(tracksProvider.getSingleTrackWithId(trackId));
				return true;

			default:
				return false;
			}
		});
	}
}
