package com.edavtyan.materialplayer.ui.lists.playlist_list;

import android.support.v4.app.FragmentActivity;

import com.edavtyan.materialplayer.ui.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PlaylistDialogsFactoryFragment {
	@Provides
	@FragmentScope
	public PlaylistSelectDialog providePlaylistSelectDialog(FragmentActivity activity) {
		return new PlaylistSelectDialog(activity);
	}

	@Provides
	@FragmentScope
	public PlaylistNewDialog providePlaylistNewDialog(FragmentActivity activity) {
		return new PlaylistNewDialog(activity);
	}

	@Provides
	@FragmentScope
	public PlaylistDialogPresenter providePlaylistDialogPresenter(
			PlaylistSelectDialog addDialog,
			PlaylistNewDialog newDialog,
			PlaylistManager manager) {
		return new PlaylistDialogPresenter(addDialog, newDialog, manager);
	}
}
