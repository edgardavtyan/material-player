package com.edavtyan.materialplayer.utils.tag;

public interface MusicTagReader {
	void setDataSource(String filePath);
	byte[] getAlbumArtBytes();
}
