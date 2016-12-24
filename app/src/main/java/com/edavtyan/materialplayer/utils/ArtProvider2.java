package com.edavtyan.materialplayer.utils;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.utils.tag.MusicTagReader;

import java.io.File;

public class ArtProvider2 {
	private final DataStorage dataStorage;
	private final MusicTagReader tagReader;

	public ArtProvider2(DataStorage dataStorage, MusicTagReader tagReader) {
		this.dataStorage = dataStorage;
		this.tagReader = tagReader;
	}

	public File load(Track track) {
		File artFilePath = dataStorage.getArtFile(track.getAlbumId());

		if (!artFilePath.exists()) {
			try {
				tagReader.setDataSource(track.getPath());
				dataStorage.saveFile(artFilePath, tagReader.getAlbumArtBytes());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return artFilePath;
	}
}
