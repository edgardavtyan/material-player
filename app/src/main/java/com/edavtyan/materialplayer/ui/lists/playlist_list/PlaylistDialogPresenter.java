package com.edavtyan.materialplayer.ui.lists.playlist_list;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.theme.ThemeColors;

import java.util.Collections;
import java.util.List;

public class PlaylistDialogPresenter {
	private final PlaylistSelectDialog addDialog;
	private final PlaylistNewDialog newDialog;
	private final PlaylistManager manager;

	public PlaylistDialogPresenter(
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
		manager.create(playlistName);
		addDialog.show(manager.list());
	}

	public void onNewPlaylistDismiss() {
		addDialog.show(manager.list());
	}

	public void onThemeChanged(ThemeColors colors) {
		addDialog.setTint(colors.getColorPrimary());
		newDialog.setTint(colors.getColorPrimary());
	}
}
