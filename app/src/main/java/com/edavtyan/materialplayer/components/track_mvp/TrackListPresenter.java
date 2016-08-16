package com.edavtyan.materialplayer.components.track_mvp;

import com.edavtyan.materialplayer.components.tracks.Track;

public class TrackListPresenter implements TrackListMvp.Presenter,
										   TrackListMvp.Model.OnServiceConnectedListener {
	private final TrackListMvp.View view;
	private final TrackListMvp.Model model;

	public TrackListPresenter(TrackListMvp.View view, TrackListMvp.Model model) {
		this.view = view;
		this.model = model;
		this.model.setOnServiceConnectedListener(this);
	}

	@Override
	public void bindViewHolder(TrackListViewHolder holder, int position) {
		Track track = model.getTrackAtIndex(position);

		if (track == null) return;

		holder.setTitle(track.getTitle());
		holder.setInfo(track.getDuration(), track.getArtistTitle(), track.getAlbumTitle());
	}

	@Override
	public int getItemCount() {
		return model.getItemCount();
	}

	@Override
	public void onHolderClick(int position) {
		model.playQueue(position);
		view.goToNowPlaying();
	}

	@Override
	public void onAddToPlaylist(int position) {
		model.addToQueue(position);
	}

	@Override
	public void onCreate() {
		model.bindService();
		model.update();
		view.notifyDataChanged();
	}

	@Override
	public void onDestroy() {
		model.unbindService();
		model.close();
	}

	@Override
	public void onServiceConnected() {
		view.notifyDataChanged();
	}
}
