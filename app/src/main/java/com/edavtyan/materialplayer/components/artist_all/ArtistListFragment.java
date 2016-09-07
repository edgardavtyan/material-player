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
		ArtistListDI artistListDI = app.getArtistListDI(getContext(), this);
		initListView(artistListDI.providePresenter(), artistListDI.provideAdapter());
		navigator = artistListDI.provideNavigator();
	}

	@Override
	public void goToArtistDetail(String title) {
		navigator.gotoArtistDetail(title);
	}
}
