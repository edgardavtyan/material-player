package com.edavtyan.materialplayer.ui.lists.artist_list;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.ui.lists.lib.ListView;
import com.edavtyan.materialplayer.transition.SourceSharedViews;

import java.util.List;

public interface ArtistListView extends ListView {
	void gotoArtistDetail(String title, SourceSharedViews sharedViews);
	void showAddToPlaylistDialog(List<Track> tracks);
}
