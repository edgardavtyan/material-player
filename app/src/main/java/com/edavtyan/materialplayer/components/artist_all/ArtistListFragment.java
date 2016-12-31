package com.edavtyan.materialplayer.components.artist_all;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.lib.mvp.list.ListFragment;

public class ArtistListFragment
		extends ListFragment<ArtistListMvp.Presenter>
		implements ArtistListMvp.View {

	private Navigator navigator;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ArtistListFactory factory = app.getArtistListDI(getContext(), this);
		initListView(factory.providePresenter(), factory.provideAdapter());
		navigator = factory.provideNavigator();
	}

	@Override
	public void goToArtistDetail(String title) {
		navigator.gotoArtistDetail(title);
	}

	@Override public void notifyDataSetChanged() {
		adapter.notifyDataSetChanged();
	}
}
