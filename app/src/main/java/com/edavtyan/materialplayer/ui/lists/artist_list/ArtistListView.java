package com.edavtyan.materialplayer.ui.lists.artist_list;

import com.edavtyan.materialplayer.ui.lists.lib.ListView;
import com.edavtyan.materialplayer.transition.SourceSharedViews;

public interface ArtistListView extends ListView {
	void gotoArtistDetail(String title, SourceSharedViews sharedViews);
}
