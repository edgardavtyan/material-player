package com.edavtyan.materialplayer.lib.file_storage;

import android.content.Context;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public abstract class GsonFileStorage {

	private final Gson gson;
	private final StringFileStorage stringStorage;

	public GsonFileStorage(Context context, Gson gson, String dir) {
		this.gson = gson;
		this.stringStorage = new StringFileStorage(context, dir);
	}

	public <T> void save(String filename, List<T> tracks) {
		String tracksJson = gson.toJson(tracks);
		stringStorage.save(filename, tracksJson);
	}

	public <T> List<T> load(String filename, Type type) {
		if (stringStorage.exists(filename)) {
			String tracksJson = stringStorage.load(filename);
			return gson.fromJson(tracksJson, type);
		} else {
			return Collections.emptyList();
		}
	}

	public String[] list() {
		return stringStorage.list();
	}

	public void delete(int position) {
		stringStorage.delete(position);
	}

	public boolean exists(String name) {
		return stringStorage.exists(name);
	}
}
