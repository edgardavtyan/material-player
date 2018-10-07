package com.edavtyan.materialplayer.ui.lists.playlist_list;

import android.support.v4.app.FragmentActivity;

import com.edavtyan.materialplayer.ui.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PlaylistDialogsFactoryFragment {
	@Provides
	@FragmentScope
	public PlaylistAddDialog providePlaylistAddDialog(FragmentActivity activity) {
		return new PlaylistAddDialog(activity);
	}

	@Provides
	@FragmentScope
	public PlaylistNewDialog providePlaylistNewDialog(FragmentActivity activity) {
		return new PlaylistNewDialog(activity);
	}

	@Provides
	@FragmentScope
	public PlaylistDialogPresenter providePlaylistDialogPresenter(
			PlaylistAddDialog addDialog,
			PlaylistNewDialog newDialog,
			PlaylistManager manager) {
		return new PlaylistDialogPresenter(addDialog, newDialog, manager);
	}
}
