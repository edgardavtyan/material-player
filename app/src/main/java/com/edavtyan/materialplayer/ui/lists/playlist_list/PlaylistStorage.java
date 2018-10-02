package com.edavtyan.materialplayer.ui.lists.playlist_list;

import android.content.Context;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.file_storage.GsonFileStorage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PlaylistStorage extends GsonFileStorage {
	private final Type type;

	public PlaylistStorage(Context context, Gson gson) {
		super(context, gson, "playlist");
		this.type = new TypeToken<List<Track>>() {}.getType();
	}

	public List<Track> load(String playlist) {
		return load(playlist, type);
	}

	public void addTracks(int position, List<Track> tracks) {
		String playlistName = list()[position];
		List<Track> playlist = load(playlistName);

		if (playlist == null) {
			playlist = new ArrayList<>();
		}

		playlist.addAll(tracks);
		save(playlistName, playlist);
	}
}
