package com.edavtyan.materialplayer.lib.album_art;

import android.media.MediaMetadataRetriever;

public class AlbumArtReader {
	private final MediaMetadataRetriever metadataRetriever;

	public AlbumArtReader(MediaMetadataRetriever metadataRetriever) {
		this.metadataRetriever = metadataRetriever;
	}

	public byte[] getAlbumArtBytes(String path) {
		metadataRetriever.setDataSource(path);
		return metadataRetriever.getEmbeddedPicture();
	}
}
