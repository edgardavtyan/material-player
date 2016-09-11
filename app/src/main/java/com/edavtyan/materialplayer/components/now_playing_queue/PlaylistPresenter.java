package com.edavtyan.materialplayer.components.now_playing_queue;

import com.edavtyan.materialplayer.db.Track;

public class PlaylistPresenter implements PlaylistMvp.Presenter {
	private final PlaylistMvp.Model model;
	private final PlaylistMvp.View view;

	public PlaylistPresenter(PlaylistMvp.Model model, PlaylistMvp.View view) {
		this.model = model;
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
	public void onBindViewHolder(PlaylistViewHolder holder, int position) {
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
