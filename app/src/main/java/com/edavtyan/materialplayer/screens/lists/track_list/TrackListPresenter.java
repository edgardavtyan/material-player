package com.edavtyan.materialplayer.screens.lists.track_list;

import com.edavtyan.materialplayer.screens.lists.lib.ListPresenter;
import com.edavtyan.materialplayer.db.Track;

public class TrackListPresenter extends ListPresenter<TrackListViewHolder> {

	private final TrackListView view;
	private final TrackListModel model;

	public TrackListPresenter(TrackListView view, TrackListModel model) {
		super(model, view);
		this.view = view;
		this.model = model;
	}

	@Override
	public void onBindViewHolder(TrackListViewHolder holder, int position) {
		Track track = model.getTrackAtIndex(position);

		if (track == null) return;

		holder.setTitle(track.getTitle());
		holder.setInfo(track.getDuration(), track.getArtistTitle(), track.getAlbumTitle());
	}

	@Override
	public int getItemCount() {
		return model.getItemCount();
	}

	public void onHolderClick(int position) {
		model.playQueue(position);
		view.gotoNowPlaying();
	}

	public void onAddToPlaylist(int position) {
		model.addToQueue(position);
	}

	@Override
	public void onCreate() {
		model.bindService();
		model.update();
	}

	@Override
	public void onDestroy() {
		model.unbindService();
		model.close();
	}
}