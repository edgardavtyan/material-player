package com.edavtyan.materialplayer.utils;

import android.media.MediaMetadataRetriever;

import com.edavtyan.materialplayer.db.Track;

import java.io.File;
import java.io.FileOutputStream;

public class ArtProvider2 {
	private final File artsFolder;

	public ArtProvider2() {
		artsFolder = new File(DataStorage.DIR_ART.getAbsolutePath());
	}

	public File load(Track track) {
		File artFilePath = new File(artsFolder, String.valueOf(track.getAlbumId()));

		if (!artFilePath.exists()) {
			try {
				MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
				metadataRetriever.setDataSource(track.getPath());
				FileOutputStream outputStream = new FileOutputStream(artFilePath);
				outputStream.write(metadataRetriever.getEmbeddedPicture());
				outputStream.close();
				metadataRetriever.release();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return artFilePath;
	}
}
