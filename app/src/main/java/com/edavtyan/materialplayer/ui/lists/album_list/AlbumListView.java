package com.edavtyan.materialplayer.ui.lists.album_list;

import com.edavtyan.materialplayer.ui.lists.lib.ListView;
import com.edavtyan.materialplayer.transition.SourceSharedViews;

public interface AlbumListView extends ListView {
	void gotoAlbumDetail(int albumId, SourceSharedViews sharedViews);
}
