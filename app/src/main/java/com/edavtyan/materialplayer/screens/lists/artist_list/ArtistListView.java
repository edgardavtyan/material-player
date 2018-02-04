package com.edavtyan.materialplayer.screens.lists.artist_list;

import com.edavtyan.materialplayer.screens.lists.lib.ListView;
import com.edavtyan.materialplayer.transition.SourceSharedViews;

public interface ArtistListView extends ListView {
	void gotoArtistDetailNormal(String title);
	void gotoArtistDetailCompact(String title, SourceSharedViews sharedViews);
}
