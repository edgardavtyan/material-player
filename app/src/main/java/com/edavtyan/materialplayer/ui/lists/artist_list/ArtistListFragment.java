package com.edavtyan.materialplayer.ui.lists.artist_list;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.transition.SharedTransitionsManager;
import com.edavtyan.materialplayer.transition.SourceSharedViews;
import com.edavtyan.materialplayer.ui.Navigator;
import com.edavtyan.materialplayer.ui.lists.lib.ListFragment;
import com.edavtyan.materialplayer.lib.playlist.PlaylistPresenter;

import java.util.List;

import javax.inject.Inject;

public class ArtistListFragment extends ListFragment implements ArtistListView {

	@Inject Navigator navigator;
	@Inject ArtistListPresenter presenter;
	@Inject ArtistListAdapter adapter;
	@Inject SharedTransitionsManager transitionsManager;
	@Inject PlaylistPresenter playlistPresenter;
	@Inject ScreenThemeModule screenThemeModule;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getComponent().inject(this);
		initListView(presenter, adapter);
		addModule(screenThemeModule);
	}

	@Override
	public void onThemeChanged(ThemeColors colors) {
		super.onThemeChanged(colors);
		playlistPresenter.onThemeChanged(colors);
	}

	@Override
	public void gotoArtistDetail(String title, SourceSharedViews sharedViews) {
		transitionsManager.pushSourceViews(sharedViews);
		navigator.gotoArtistDetailCompact(getActivity(), title, sharedViews.build());

	}

	@Override
	public void showPlaylistSelectionDialog(List<Track> tracks) {
		playlistPresenter.onAddToPlaylistClick(tracks);
	}

	protected ArtistListDIComponent getComponent() {
		return DaggerArtistListDIComponent
				.builder()
				.appDIComponent(((App) getContext().getApplicationContext()).getAppComponent())
				.artistListDIModule(new ArtistListDIModule(getActivity(), this))
				.build();
	}
}
