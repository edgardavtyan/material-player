package com.edavtyan.materialplayer.screens.lists.album_list;

import com.edavtyan.materialplayer.screens.lists.lib.ListView;
import com.edavtyan.materialplayer.transition.SourceSharedViews;

public interface AlbumListView extends ListView {
	void gotoAlbumDetail(int albumId, SourceSharedViews sharedViews);
}
