package com.edavtyan.materialplayer.lib.playlist;

import android.support.v4.app.FragmentActivity;

import com.edavtyan.materialplayer.lib.playlist.dialogs.PlaylistNewDialog;
import com.edavtyan.materialplayer.lib.playlist.dialogs.PlaylistSelectDialog;
import com.edavtyan.materialplayer.lib.playlist.models.PlaylistManager;
import com.edavtyan.materialplayer.lib.playlist.models.PlaylistPresenter;
import com.edavtyan.materialplayer.ui.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PlaylistDIModuleFragment {
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
	public PlaylistPresenter providePlaylistDialogPresenter(
			PlaylistSelectDialog addDialog,
			PlaylistNewDialog newDialog,
			PlaylistManager manager) {
		return new PlaylistPresenter(addDialog, newDialog, manager);
	}
}
