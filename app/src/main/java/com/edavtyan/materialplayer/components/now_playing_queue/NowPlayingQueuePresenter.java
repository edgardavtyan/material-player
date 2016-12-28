package com.edavtyan.materialplayer.components.now_playing_queue;

import com.edavtyan.materialplayer.db.Track;

public class NowPlayingQueuePresenter implements NowPlayingQueueMvp.Presenter {
	private final NowPlayingQueueMvp.Model model;
	private final NowPlayingQueueMvp.View view;

	public NowPlayingQueuePresenter(NowPlayingQueueMvp.Model model, NowPlayingQueueMvp.View view) {
		this.model = model;
		this.model.setOnServiceConnectedListener(this);
		this.view = view;
	}

	@Override
	public void onCreate() {
		model.bind();
	}

	@Override
	public void onDestroy() {
		model.unbind();
	}

	@Override
	public void onItemClick(int position) {
		model.playItemAtPosition(position);
	}

	@Override
	public void onRemoveItemClick(int position) {
		model.removeItemAtPosition(position);
	}

	@Override
	public void onBindViewHolder(NowPlayingQueueViewHolder holder, int position) {
		Track track = model.getTrackAtPosition(position);
		holder.setTitle(track.getTitle());
		holder.setInfo(track.getDuration(), track.getArtistTitle(), track.getAlbumTitle());
	}

	@Override
	public int getItemCount() {
		return model.getTrackCount();
	}

	@Override
	public void onServiceConnected() {
		view.update();
	}
}
