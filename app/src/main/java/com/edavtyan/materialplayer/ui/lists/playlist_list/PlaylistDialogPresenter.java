package com.edavtyan.materialplayer.ui.lists.playlist_list;

public class PlaylistDialogPresenter {
	private final PlaylistAddDialog addDialog;
	private final PlaylistNewDialog newDialog;
	private final PlaylistManager manager;

	public PlaylistDialogPresenter(
			PlaylistAddDialog addDialog,
			PlaylistNewDialog newDialog,
			PlaylistManager manager) {
		this.addDialog = addDialog;
		this.addDialog.setOnNewPlaylistClickListener(this::onNewPlaylistClick);
		this.newDialog = newDialog;
		this.newDialog.setOnConfirmListener(this::onNewPlaylistConfirm);
		this.newDialog.setOnDismissListener(this::onNewPlaylistDismiss);
		this.manager = manager;
	}

	public void onAddToPlaylistClick() {
		addDialog.show(manager.list());
	}

	private void onNewPlaylistClick() {
		addDialog.close();
		newDialog.show();
	}

	private void onNewPlaylistConfirm(String playlistName) {
		manager.create(playlistName);
		addDialog.show(manager.list());
	}

	private void onNewPlaylistDismiss() {
		addDialog.show(manager.list());
	}
}
