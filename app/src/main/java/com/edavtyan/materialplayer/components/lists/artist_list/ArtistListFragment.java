package com.edavtyan.materialplayer.components.lists.artist_list;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.lists.lib.ListFragment;

public class ArtistListFragment extends ListFragment implements ArtistListView {

	private Navigator navigator;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ArtistListFactory factory = app.getArtistListDI(getContext(), this);
		initListView(factory.getPresenter(), factory.getAdapter());
		navigator = factory.getNavigator();
	}

	@Override
	public void gotoArtistDetail(String title) {
		navigator.gotoArtistDetail(title);
	}
}
