package com.edavtyan.materialplayer.ui.lists.playlist_list;

import com.edavtyan.materialplayer.db.Track;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlaylistManager {
	private final PlaylistStorage storage;

	private List<Track> pendingTracks;

	public PlaylistManager(PlaylistStorage storage) {
		this.storage = storage;
	}

	public void create(String name) {
		storage.save(name, Collections.emptyList());
	}

	public String[] list() {
		return storage.list();
	}

	public void addPendingTracks(Track track) {
		pendingTracks = new ArrayList<>();
		pendingTracks.add(track);
	}

	public void confirmPendingTracks(int position) {
		storage.addTracks(position, pendingTracks);
		pendingTracks.clear();
	}

	public List<Track> load(String playlistName) {
		return storage.load(playlistName);
	}

	public void removeTrack(String playlistName, int position) {
		List<Track> playlist = storage.load(playlistName);
		playlist.remove(position);
		storage.save(playlistName, playlist);
	}
}
