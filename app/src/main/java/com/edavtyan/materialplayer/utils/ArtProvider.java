package com.edavtyan.materialplayer.utils;

import android.media.MediaMetadataRetriever;
import android.util.Log;

import com.edavtyan.materialplayer.components.tracks.Track;
import com.esotericsoftware.wildcard.Paths;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public final class ArtProvider {
	private ArtProvider() {}


	private static final String TAG = "ArtProvider";


	public static File fromTrack(Track track) {
		String artFolder = DataStorage.DIR_ART.getAbsolutePath();
		String artGlob = track.getAlbumId() + "@*";
		List<File> artFiles = new Paths().glob(artFolder, artGlob).getFiles();

		boolean foundArt = artFiles.size() != 0;
		boolean artOutdated = foundArt && artFiles.get(0).lastModified() < track.getDateModified();

		int artGeneration = 1;
		if (artOutdated) {
			String artFileName = artFiles.get(0).getName();
			FileUtils.delete(artFileName);
			artGeneration = getArtGeneration(artFileName);
			artGeneration++;
		}

		File artFile = new File(
				DataStorage.DIR_ART,
				String.format("%d@%d", track.getAlbumId(), artGeneration));

		if (artOutdated || !foundArt) {
			Log.d(TAG, "Art for " + track.getAlbumTitle() + " does not exist or outdated");
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
