package com.edavtyan.materialplayer.ui.lists.playlist_list;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.ui.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PlaylistDialogsFactoryActivity {
	@Provides
	@ActivityScope
	public PlaylistAddDialog providePlaylistAddDialog(AppCompatActivity activity) {
		return new PlaylistAddDialog(activity);
	}

	@Provides
	@ActivityScope
	public PlaylistNewDialog providePlaylistNewDialog(AppCompatActivity activity) {
		return new PlaylistNewDialog(activity);
	}

	@Provides
	@ActivityScope
	public PlaylistDialogPresenter providePlaylistDialogPresenter(
			PlaylistAddDialog addDialog,
			PlaylistNewDialog newDialog,
			PlaylistManager manager) {
		return new PlaylistDialogPresenter(addDialog, newDialog, manager);
	}
}
