package com.edavtyan.materialplayer.components.lists.artist_list;

import com.edavtyan.materialplayer.components.lists.lib.ListView;

public interface ArtistListView extends ListView {
	void gotoArtistDetailNormal(String title);
	void gotoArtistDetailCompact(String title);
}
