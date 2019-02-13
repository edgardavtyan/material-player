package com.edavtyan.materialplayer.lib.lyrics;

import android.content.Context;

import com.edavtyan.materialplayer.lib.file_storage.StringFileStorage;
import com.edavtyan.materialplayer.utils.StringUtils;

public class LyricsStorage extends StringFileStorage {
	public LyricsStorage(Context context) {
		super(context, "lyrics");
	}

	public boolean exists(String artist, String track) {
		return exists(getFilename(artist, track));
	}

	public String get(String artist, String track) {
		return load(getFilename(artist, track));
	}

	public void put(String artist, String track, String lyrics) {
		save(getFilename(artist, track), lyrics);
	}

	private String getFilename(String artist, String track) {
		return artist + ":" + StringUtils.encodeFilename(track);
	}
}
