package com.edavtyan.materialplayer.utils;

import android.media.MediaMetadataRetriever;

import com.edavtyan.materialplayer.components.track_mvp.Track;
import com.esotericsoftware.wildcard.Paths;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public final class ArtProvider {
	private ArtProvider() {}


	private static final String TAG = "ArtProvider";


	public static File fromTrack(Track track) {
		String artsCacheFolder = DataStorage.DIR_ART.getAbsolutePath();
		String artFilePattern = track.getAlbumId() + "@*";
		List<File> artFiles = new Paths().glob(artsCacheFolder, artFilePattern).getFiles();

		boolean isArtFound = artFiles.size() != 0;
		boolean isArtOutdated = isArtFound && artFiles.get(0).lastModified() < track.getDateModified();

		int artGeneration = 1;
		if (isArtOutdated) {
			String artFileName = artFiles.get(0).getName();
			FileUtils.delete(artFileName);
			artGeneration = getArtGeneration(artFileName);
			artGeneration++;
		}

		File artFile = new File(DataStorage.DIR_ART, track.getAlbumId() + "@" + artGeneration);

		if (isArtOutdated || !isArtFound) {
			try {
				MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
				metadataRetriever.setDataSource(track.getPath());
				byte[] artBytes = metadataRetriever.getEmbeddedPicture();

				FileOutputStream outputStream = new FileOutputStream(artFile);
				outputStream.write(artBytes);
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return artFile;
	}

	public static File fromPath(String artPath) {
		File artFile = null;
		if (artPath != null) {
			artFile = new File(artPath);
		}

		return artFile;
	}

	private static int getArtGeneration(String artFileName) {
		return Integer.parseInt(artFileName.substring(artFileName.indexOf("@") + 1));
	}
}
