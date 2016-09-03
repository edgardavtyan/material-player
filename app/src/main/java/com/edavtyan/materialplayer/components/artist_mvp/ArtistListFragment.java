package com.edavtyan.materialplayer.components.artist_mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.BaseFragment;
import com.edavtyan.materialplayer.components.ListFragment;
import com.edavtyan.materialplayer.components.artists.ArtistDetailActivity;

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
