package com.edavtyan.materialplayer.utils.tag;

import android.media.MediaMetadataRetriever;

public class VanillaMusicTagReader implements MusicTagReader {
	private final MediaMetadataRetriever metadataRetriever;

	public VanillaMusicTagReader(MediaMetadataRetriever metadataRetriever) {
		this.metadataRetriever = metadataRetriever;
	}

	@Override public void setDataSource(String filePath) {
		metadataRetriever.setDataSource(filePath);
	}

	@Override public byte[] getAlbumArtBytes() {
		return metadataRetriever.getEmbeddedPicture();
	}
}
