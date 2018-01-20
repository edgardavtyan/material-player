package com.edavtyan.materialplayer.components.lists.artist_list;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.lists.lib.ListFragment;
import com.edavtyan.materialplayer.lib.lastfm.LastFmFactory;

import javax.inject.Inject;

public class ArtistListFragment extends ListFragment implements ArtistListView {

	@Inject Navigator navigator;
	@Inject ArtistListPresenter presenter;
	@Inject ArtistListAdapter adapter;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getComponent().inject(this);
		initListView(presenter, adapter);
	}

	@Override
	public void gotoArtistDetailNormal(String title) {
		navigator.gotoArtistDetailNormal(title);
	}

	@Override
	public void gotoArtistDetailCompact(String title) {
		navigator.gotoArtistDetailCompact(title);
	}

	protected ArtistListComponent getComponent() {
		return DaggerArtistListComponent
				.builder()
				.artistListModule(new ArtistListModule(getActivity(), this))
				.build();
	}
}
