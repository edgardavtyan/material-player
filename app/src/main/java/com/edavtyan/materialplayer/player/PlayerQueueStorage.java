package com.edavtyan.materialplayer.player;

import android.content.Context;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.file_storage.GsonFileStorage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class PlayerQueueStorage extends GsonFileStorage {
	private static final String QUEUE_FILENAME = "current_queue";

	private final Type type;

	public PlayerQueueStorage(Context context, Gson gson) {
		super(context, gson, "queue");
		this.type = new TypeToken<List<Track>>() {}.getType();
	}

	public void save(List<Track> tracks) {
		save(QUEUE_FILENAME, tracks);
	}

	public List<Track> load() {
		return load(QUEUE_FILENAME, type);
	}
}
