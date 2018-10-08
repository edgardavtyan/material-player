package com.edavtyan.materialplayer.lib.playlist.models;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlaylistManager {
	private final PlaylistStorage storage;
	private final ModelServiceModule serviceModule;

	private List<Track> pendingTracks;

	public PlaylistManager(PlaylistStorage storage, ModelServiceModule serviceModule) {
		this.storage = storage;
		this.serviceModule = serviceModule;
	}

	public void bind() {
		serviceModule.bind();
	}

	public void unbind() {
		serviceModule.unbind();
	}

	public void create(String name) {
		storage.save(name, Collections.emptyList());
	}

	public String[] list() {
		return storage.list();
	}

	public void delete(int position) {
		storage.delete(position);
	}

	public void addPendingTracks(Track track) {
		pendingTracks = new ArrayList<>();
		pendingTracks.add(track);
	}

	public void addPendingTracks(List<Track> tracks) {
		pendingTracks = new ArrayList<>();
		pendingTracks.addAll(tracks);
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

	public void moveTrack(String playlistName, int fromPosition, int toPosition) {
		List<Track> playlist = storage.load(playlistName);
		if (fromPosition < toPosition) {
			for (int i = fromPosition; i < toPosition; i++) {
				Collections.swap(playlist, i, i + 1);
			}
		} else {
			for (int i = fromPosition; i > toPosition; i--) {
				Collections.swap(playlist, i, i - 1);
			}
		}
		storage.save(playlistName, playlist);
	}

	public void playPlaylist(String playlistName, int position) {
		serviceModule.getService().getPlayer().playNewTracks(load(playlistName), position);
	}
}
