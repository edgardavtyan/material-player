package com.edavtyan.materialplayer.app.views.trackslist;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.models.track.TracksProvider;
import com.edavtyan.materialplayer.app.views.lib.adapters.RecyclerServiceCursorAdapter;
import com.edavtyan.materialplayer.app.views.nowplaying.NowPlayingActivity;

public abstract class TracksAdapter<THolder extends TracksViewHolder>
		extends RecyclerServiceCursorAdapter<THolder> {
	protected final TracksProvider tracksProvider;

	//---

	public TracksAdapter(Context context, Cursor cursor) {
		super(context, cursor);
		tracksProvider = new TracksProvider(context);
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
			service.getPlayer().setTracks(
					tracksProvider.getAllTracks(cursor),
					holder.getAdapterPosition());
			service.getPlayer().prepare();
		});

		holder.setOnMenuItemClickListener(item -> {
			switch (item.getItemId()) {
			case R.id.menu_addToPlaylist:
				cursor.moveToPosition(holder.getAdapterPosition());
				int trackId = tracksProvider.getId(cursor);
				service.getPlayer().getQueue().add(tracksProvider.getSingleTrackWithId(trackId));
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
		holder.setTitle(tracksProvider.getTitle(cursor));
		holder.setInfo(tracksProvider.getDuration(cursor), tracksProvider.getArtist(cursor),
				tracksProvider.getAlbum(cursor));
	}
}
