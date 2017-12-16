package com.edavtyan.materialplayer.lib.lastfm;

import android.content.Context;

import com.edavtyan.materialplayer.lib.file_storage.StringFileStorage;

public class LastfmArtistInfoFileStorage extends StringFileStorage {
	public LastfmArtistInfoFileStorage(Context context) {
		super(context, "artist_info");
	}
}
