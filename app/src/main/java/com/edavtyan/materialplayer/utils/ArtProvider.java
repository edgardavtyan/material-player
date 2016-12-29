package com.edavtyan.materialplayer.utils;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer.utils.tag.MusicTagReader;

import java.io.File;

public class ArtProvider {
	private final DataStorage dataStorage;
	private final MusicTagReader tagReader;
	private final TestableBitmapFactory bitmapFactory;

	public ArtProvider(
			DataStorage dataStorage,
			MusicTagReader tagReader,
			TestableBitmapFactory bitmapFactory) {
		this.dataStorage = dataStorage;
		this.tagReader = tagReader;
		this.bitmapFactory = bitmapFactory;
	}

	public Bitmap load(Track track) {
		File artFilePath = dataStorage.getArtFile(track.getAlbumId());

		if (!artFilePath.exists()) {
			try {
				tagReader.setDataSource(track.getPath());
				dataStorage.saveFile(artFilePath, tagReader.getAlbumArtBytes());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return bitmapFactory.fromPath(artFilePath.getAbsolutePath());
	}
}
