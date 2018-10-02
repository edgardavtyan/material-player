package com.edavtyan.materialplayer.ui.lists.playlist_list;

public class PlaylistManager {
	private final PlaylistStorage storage;

	public PlaylistManager(PlaylistStorage storage) {
		this.storage = storage;
	}

	public void create(String name) {
		storage.save(name, "");
	}

	public String[] list() {
		return storage.list();
	}
}
