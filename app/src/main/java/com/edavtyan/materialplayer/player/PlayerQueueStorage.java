package com.edavtyan.materialplayer.player;

import android.content.Context;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.file_storage.StringFileStorage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class PlayerQueueStorage extends StringFileStorage {
	private static final String QUEUE_FILENAME = "current_queue";

	private final Gson gson;
	private final Type type;

	public PlayerQueueStorage(Context context, Gson gson) {
		super(context, "queue");
		this.gson = gson;
		this.type = new TypeToken<List<Track>>() {}.getType();
	}

	public void save(List<Track> tracks) {
		String tracksJson = gson.toJson(tracks);
		save(QUEUE_FILENAME, tracksJson);
	}

	public List<Track> load() {
		if (exists(QUEUE_FILENAME)) {
			String tracksJson = load(QUEUE_FILENAME);
			return gson.fromJson(tracksJson, type);
		} else {
			return Collections.emptyList();
		}
	}
}
