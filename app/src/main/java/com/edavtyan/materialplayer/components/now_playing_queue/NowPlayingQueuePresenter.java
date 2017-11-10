package com.edavtyan.materialplayer.components.now_playing_queue;

import com.edavtyan.materialplayer.components.lists.lib.ListModel;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.components.lists.lib.ListPresenter;

public class NowPlayingQueuePresenter
		extends ListPresenter<NowPlayingQueueViewHolder>
		implements NowPlayingQueueMvp.Presenter {

	private final NowPlayingQueueMvp.Model model;
	private final NowPlayingQueueMvp.View view;

	@SuppressWarnings("FieldCanBeLocal")
	private final NowPlayingQueueMvp.Model.OnNewTrackListener onNewTrackListener
			= new NowPlayingQueueMvp.Model.OnNewTrackListener() {
		@Override
		public void onNewTrack() {
			view.notifyDataSetChanged();
		}
	};

	@SuppressWarnings("FieldCanBeLocal")
	private final ListModel.OnServiceConnectedListener onServiceConnectedListener
			= new ListModel.OnServiceConnectedListener() {
		@Override
		public void onServiceConnected() {
			view.notifyDataSetChanged();
		}
	};

	public NowPlayingQueuePresenter(NowPlayingQueueMvp.Model model, NowPlayingQueueMvp.View view) {
		super(model, view);
		this.model = model;
		this.model.setOnNewTrackListener(onNewTrackListener);
		this.model.setOnServiceConnectedListener(onServiceConnectedListener);
		this.view = view;
	}

	@Override
	public void onCreate() {
		model.bindService();
	}

	@Override
	public void onDestroy() {
		model.unbindService();
	}

	@Override
	public void onItemClick(int position) {
		model.playItemAtPosition(position);
		view.notifyDataSetChanged();
	}

	@Override
	public void onRemoveItemClick(int position) {
		model.removeItemAtPosition(position);
		view.removeItem(position);
	}

	@Override
	public void onBindViewHolder(NowPlayingQueueViewHolder holder, int position) {
		Track track = model.getTrackAtPosition(position);
		holder.setTitle(track.getTitle());
		holder.setInfo(track.getDuration(), track.getArtistTitle(), track.getAlbumTitle());
		holder.setIsPlaying(model.getNowPlayingTrack() == track);
	}

	@Override
	public int getItemCount() {
		return model.getTrackCount();
	}

	@Override
	public int getItemId(int position) {
		return model.getTrackAtPosition(position).getId();
	}
}
