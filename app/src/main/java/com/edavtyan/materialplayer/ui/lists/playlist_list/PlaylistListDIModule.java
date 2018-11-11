package com.edavtyan.materialplayer.ui.lists.playlist_list;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.edavtyan.materialplayer.lib.playlist.dialogs.PlaylistDeleteDialog;
import com.edavtyan.materialplayer.lib.playlist.models.PlaylistManager;
import com.edavtyan.materialplayer.ui.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PlaylistListDIModule {
	private final PlaylistListFragment view;

	public PlaylistListDIModule(PlaylistListFragment view) {
		this.view = view;
	}

	@Provides
	@FragmentScope
	public Activity provideActivity() {
		return view.getActivity();
	}

	@Provides
	@FragmentScope
	public Fragment provideFragment() {
		return view;
	}

	@Provides
	@FragmentScope
	public PlaylistListPresenter providePlaylistListPresenter(PlaylistManager manager) {
		return new PlaylistListPresenter(view, manager);
	}

	@Provides
	@FragmentScope
	public PlaylistListAdapter providePlaylistListAdapter(
			Activity activity,
			PlaylistListPresenter presenter) {
		return new PlaylistListAdapter(activity, presenter);
	}

	@Provides
	@FragmentScope
	public PlaylistDeleteDialog providePlaylistDeleteDialog(PlaylistListPresenter presenter) {
		return new PlaylistDeleteDialog(view.getActivity(), presenter);
	}
}
