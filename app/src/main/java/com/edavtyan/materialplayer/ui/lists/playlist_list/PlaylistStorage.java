package com.edavtyan.materialplayer.ui.lists.playlist_list;

import android.content.Context;

import com.edavtyan.materialplayer.lib.file_storage.StringFileStorage;

public class PlaylistStorage extends StringFileStorage {
	public PlaylistStorage(Context context) {
		super(context, "playlist");
	}
}
