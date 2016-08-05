package com.edavtyan.materialplayer.components.artist_mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.artists.ArtistDetailActivity;

public class ArtistListFragment extends Fragment implements ArtistListMvp.View {
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_list, container, false);

		ArtistListModel model = new ArtistListModel(getActivity().getContentResolver());
		ArtistListPresenter presenter = new ArtistListPresenter(model, this);
		ArtistListAdapter adapter = new ArtistListAdapter(getActivity(), presenter);

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
