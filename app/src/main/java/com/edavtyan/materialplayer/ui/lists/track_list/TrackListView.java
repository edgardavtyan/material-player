package com.edavtyan.materialplayer.ui.lists.track_list;

import com.edavtyan.materialplayer.lib.theme.ThemeableScreen;
import com.edavtyan.materialplayer.ui.lists.lib.ListView;

public interface TrackListView extends ListView, ThemeableScreen {
	void showAddToPlaylistDialog(String[] playlistNames);
	void closeAddToPlaylistDialog();
	void showNewPlaylistDialog();
}
