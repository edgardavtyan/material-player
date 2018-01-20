package com.edavtyan.materialplayer.screens.lists.album_list;

import com.edavtyan.materialplayer.screens.lists.lib.ListView;

public interface AlbumListView extends ListView {
	void gotoAlbumDetailNormal(int albumId);
	void gotoAlbumDetailCompact(int albumId);
}
