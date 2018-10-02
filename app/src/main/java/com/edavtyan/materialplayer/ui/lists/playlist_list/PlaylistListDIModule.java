package com.edavtyan.materialplayer.ui.lists.playlist_list;

import android.support.v4.app.FragmentActivity;

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
	public FragmentActivity provideFragmentActivity() {
		return view.getActivity();
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
}
