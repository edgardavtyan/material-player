package com.edavtyan.materialplayer.ui.lists.track_list;

import com.edavtyan.materialplayer.db.types.Track;
import com.edavtyan.materialplayer.ui.lists.lib.ListPresenter;

public class TrackListPresenter implements ListPresenter<TrackListViewHolder> {

	private final TrackListView view;
	private final TrackListModel model;

	public TrackListPresenter(TrackListView view, TrackListModel model) {
		super();
		this.view = view;
		this.model = model;
	}

	@Override
	public void onBindViewHolder(TrackListViewHolder holder, int position) {
		Track track = model.getTrackAtIndex(position);
		holder.setTitle(track.getTitle());
		holder.setInfo(track.getDuration(), track.getArtistTitle(), track.getAlbumTitle());
	}

	@Override
	public int getItemCount() {
		return model.getItemCount();
	}

	public void onHolderClick(int position) {
		model.playQueue(position);
	}

	public void onAddToQueue(int position) {
		model.addToQueue(position);
	}

	public void onAddToPlaylistClick(int position) {
		view.showPlaylistSelectionDialog(model.getTrackAtIndex(position));
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
