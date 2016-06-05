package com.edavtyan.materialplayer.app.views.trackslist;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.views.lib.adapters.RecyclerServiceCursorAdapter;
import com.edavtyan.materialplayer.app.utils.DurationUtils;
import com.edavtyan.materialplayer.app.models.track.TracksProvider;
import com.edavtyan.materialplayer.app.views.nowplaying.NowPlayingActivity;

public class TracksListAdapter
		extends RecyclerServiceCursorAdapter<TracksListAdapter.TrackViewHolder> {
	private final TracksProvider tracksProvider;

	public TracksListAdapter(Context context, Cursor cursor) {
		super(context, cursor);
		tracksProvider = new TracksProvider(context);
	}

	/*
	 * ViewHolder
	 */

	public class TrackViewHolder
			extends RecyclerView.ViewHolder
			implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
		private final TextView titleTextView;
		private final TextView infoTextView;
		private final ImageButton menuButton;

		public TrackViewHolder(View itemView) {
			super(itemView);
			itemView.setOnClickListener(this);
			titleTextView = (TextView) itemView.findViewById(R.id.title);
			infoTextView = (TextView) itemView.findViewById(R.id.info);
			menuButton = (ImageButton) itemView.findViewById(R.id.menu);

			PopupMenu popupMenu = new PopupMenu(context, menuButton);
			popupMenu.inflate(R.menu.menu_track);
			popupMenu.setOnMenuItemClickListener(this);
			menuButton.setOnClickListener(view -> popupMenu.show());
		}

		@Override
		public void onClick(View view) {
			Intent i = new Intent(context, NowPlayingActivity.class);
			context.startActivity(i);

			service.getPlayer().setTracks(tracksProvider.getAllTracks(cursor), getAdapterPosition());
			service.getPlayer().prepare();
		}

		@Override
		public boolean onMenuItemClick(MenuItem menuItem) {
			switch (menuItem.getItemId()) {
			case R.id.menu_addToPlaylist:
				cursor.moveToPosition(getAdapterPosition());
				int trackId = tracksProvider.getId(cursor);
				service.getPlayer().getQueue().add(tracksProvider.withId(trackId));
				return true;

			default:
				return false;
			}
		}
	}

	/*
	 * RecyclerViewCursorAdapter<TracksListAdapter.TrackViewHolder>
	 */

	@Override
	public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.listitem_track, parent, false);
		return new TrackViewHolder(view);
	}

	@Override
	public void onBindViewHolder(TrackViewHolder holder, int position) {
		super.onBindViewHolder(holder, position);
		holder.titleTextView.setText(tracksProvider.getTitle(cursor));
		holder.infoTextView.setText(getTrackInfo());
	}

	/*
	 * Protected methods
	 */

	protected String getTrackInfo() {
		long duration  = tracksProvider.getDuration(cursor);
		String artist = tracksProvider.getArtist(cursor);
		String album = tracksProvider.getAlbum(cursor);

		return context.getResources().getString(
				R.string.pattern_track_info,
				DurationUtils.toStringUntilHours(duration),
				artist,
				album);
	}
}
