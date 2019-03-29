package com.edavtyan.materialplayer.ui.lists.track_list;

import com.edavtyan.materialplayer.db.types.Track;
import com.edavtyan.materialplayer.lib.theme.ThemeableScreen;
import com.edavtyan.materialplayer.ui.lists.lib.ListView;

public interface TrackListView extends ListView, ThemeableScreen {
	void showPlaylistSelectionDialog(Track track);
}
