package com.edavtyan.materialplayer.components.artist_all;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.lib.mvp.list.ListFragment;
import com.edavtyan.materialplayer.components.artist_detail.ArtistDetailActivity;

public class ArtistListFragment
		extends ListFragment<ArtistListMvp.Presenter>
		implements ArtistListMvp.View {

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ArtistListDI artistListDI = app.getArtistListDI(getContext(), this);
		initListView(artistListDI.providePresenter(), artistListDI.provideAdapter());
	}

	@Override
	public void goToArtistDetail(String title) {
		ArtistDetailActivity.startActivity(getActivity(), title);
	}
}
