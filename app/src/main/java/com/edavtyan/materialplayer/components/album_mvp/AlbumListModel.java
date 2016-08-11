package com.edavtyan.materialplayer.components.album_mvp;

import com.edavtyan.materialplayer.components.albums.Album;

public class AlbumListModel implements AlbumListMvp.Model {
	@Override
	public Album getAlbumAtIndex(int index) {
		return null;
	}

	@Override
	public int getAlbumsCount() {
		return 0;
	}

	@Override
	public void addToPlaylist(int albumId) {

	}
}
