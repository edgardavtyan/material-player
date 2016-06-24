package com.edavtyan.materialplayer.app.views.playlist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.app.MusicPlayerService;
import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.views.lib.adapters.RecyclerViewServiceAdapter;

public class PlaylistAdapter extends RecyclerViewServiceAdapter<PlaylistTrackViewHolder> {
	private final Context context;

	public PlaylistAdapter(Context context) {
		super(context);
		this.context = context;
	}

	/*
	 * BroadcastReceivers
	 */

	private BroadcastReceiver newTrackReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			notifyItemChanged(service.getQueue().getCurrentTrackIndex());
			notifyItemChanged(service.getQueue().getCurrentTrackIndex() - 1);
		}
	};

	@Override
	public PlaylistTrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.listitem_track, parent, false);
		PlaylistTrackViewHolder holder = new PlaylistTrackViewHolder(context, view);

		holder.setOnClickListener(itemView -> {
			if (!isBound) return;
			int oldPosition = service.getQueue().getCurrentTrackIndex();
			service.getQueue().setCurrentTrackIndex(holder.getAdapterPosition());
			service.getPlayer().prepare();
			notifyItemChanged(oldPosition);
		});

		holder.setOnMenuItemClickListener(item -> {
			switch (item.getItemId()) {
			case R.id.menu_remove:
				service.getQueue().remove(holder.getAdapterPosition());
				notifyItemRemoved(holder.getAdapterPosition());
				return true;

			default:
				return false;
			}
		});

		return holder;
	}

	@Override
	public void onBindViewHolder(PlaylistTrackViewHolder holder, int position) {
		if (!isBound) return;
		holder.setTitle(service.getQueue().get(position).getTrackTitle());
		holder.setInfo(service.getQueue().get(position).getAlbumTitle());
		holder.setNowPlaying(service.getQueue().getCurrentTrackIndex() == holder.getLayoutPosition());
	}

	@Override
	public int getItemCount() {
		if (!isBound) return 0;
		return service.getQueue().size();
	}

	/*
	 * Public methods
	 */

	public void registerReceivers() {
		context.registerReceiver(
				newTrackReceiver,
				new IntentFilter(MusicPlayerService.SEND_NEW_TRACK));
	}

	public void unregisterReceivers() {
		context.unregisterReceiver(newTrackReceiver);
	}
}
