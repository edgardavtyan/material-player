package com.edavtyan.materialplayer.ui.lists.playlist_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.edavtyan.materialplayer.lib.playlist.models.PlaylistManager;
import com.edavtyan.materialplayer.lib.playlist.dialogs.PlaylistDeleteDialog;
import com.edavtyan.materialplayer.ui.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PlaylistListFactory {
	private final PlaylistListFragment view;

	public PlaylistListFactory(PlaylistListFragment view) {
		this.view = view;
	}

	@Provides
	@FragmentScope
	public FragmentActivity provideFragmentActivity() {
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
			FragmentActivity activity,
			PlaylistListPresenter presenter) {
		return new PlaylistListAdapter(activity, presenter);
	}

	@Provides
	@FragmentScope
	public PlaylistDeleteDialog providePlaylistDeleteDialog(PlaylistListPresenter presenter) {
		return new PlaylistDeleteDialog(view.getActivity(), presenter);
	}
}
