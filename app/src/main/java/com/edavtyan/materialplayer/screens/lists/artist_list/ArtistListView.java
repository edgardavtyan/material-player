package com.edavtyan.materialplayer.screens.lists.artist_list;

import com.edavtyan.materialplayer.screens.lists.lib.ListView;

public interface ArtistListView extends ListView {
	void gotoArtistDetailNormal(String title);
	void gotoArtistDetailCompact(String title);
}
