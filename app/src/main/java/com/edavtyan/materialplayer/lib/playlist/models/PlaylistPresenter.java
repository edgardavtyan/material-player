package com.edavtyan.materialplayer.lib.playlist.models;

import com.edavtyan.materialplayer.db.types.Track;
import com.edavtyan.materialplayer.lib.playlist.dialogs.PlaylistNewDialog;
import com.edavtyan.materialplayer.lib.playlist.dialogs.PlaylistSelectDialog;

import java.util.Collections;
import java.util.List;

public class PlaylistPresenter {
	private final PlaylistSelectDialog addDialog;
	private final PlaylistNewDialog newDialog;
	private final PlaylistManager manager;

	public PlaylistPresenter(
			PlaylistSelectDialog addDialog,
			PlaylistNewDialog newDialog,
			PlaylistManager manager) {
		this.addDialog = addDialog;
		this.addDialog.setOnNewPlaylistClickListener(v -> this.onNewPlaylistClick());
		this.addDialog.setOnPlaylistClickListener(this::onPlaylistSelected);
		this.newDialog = newDialog;
		this.newDialog.setOnConfirmListener(this::onNewPlaylistConfirm);
		this.newDialog.setOnDismissListener(this::onNewPlaylistDismiss);
		this.manager = manager;
	}

	public void onAddToPlaylistClick(Track track) {
		onAddToPlaylistClick(Collections.singletonList(track));
	}

	public void onAddToPlaylistClick(List<Track> tracks) {
		manager.addPendingTracks(tracks);
		addDialog.show(manager.list());
	}

	public void onPlaylistSelected(int position) {
		manager.confirmPendingTracks(position);
		addDialog.close();
	}

	public void onNewPlaylistClick() {
		addDialog.close();
		newDialog.show();
	}

	public void onNewPlaylistConfirm(String playlistName) {
		if (manager.create(playlistName)) {
			newDialog.close();
			addDialog.show(manager.list());
		} else {
			newDialog.showAlreadyExistsError();
		}
	}

	public void onNewPlaylistDismiss() {
		addDialog.show(manager.list());
	}
}
