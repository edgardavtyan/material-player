package com.edavtyan.materialplayer.components.lists.track_list;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.components.lists.lib.ListPresenter;

public class TrackListPresenter
		extends ListPresenter<TrackListViewHolder>
		implements TrackListMvp.Presenter {

	private final TrackListMvp.View view;
	private final TrackListMvp.Model model;

	public TrackListPresenter(TrackListMvp.View view, TrackListMvp.Model model) {
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

	@Override
	public void onHolderClick(int position) {
		model.playQueue(position);
		view.gotoNowPlaying();
	}

	@Override
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