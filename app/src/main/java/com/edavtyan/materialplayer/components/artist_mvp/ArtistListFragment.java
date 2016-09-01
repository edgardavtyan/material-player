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
import com.edavtyan.materialplayer.components.artists.ArtistDetailActivity;

public class ArtistListFragment extends BaseFragment implements ArtistListMvp.View {

	private ArtistListAdapter adapter;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ArtistListDI artistListDI = app.getArtistListDI(getContext(), this);
		adapter = artistListDI.provideAdapter();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_list, container, false);

		RecyclerView list = (RecyclerView) view.findViewById(R.id.list);
		list.setLayoutManager(new LinearLayoutManager(getActivity()));
		list.setAdapter(adapter);

		return view;
	}

	@Override
	public void goToArtistDetail(String title) {
		ArtistDetailActivity.startActivity(getActivity(), title);
	}
}
