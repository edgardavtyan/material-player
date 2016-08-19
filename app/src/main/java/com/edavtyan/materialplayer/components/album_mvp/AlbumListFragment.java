package com.edavtyan.materialplayer.components.album_mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.albums.AlbumDetailActivity;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.TrackDB;

import lombok.Getter;
import lombok.Setter;

public class AlbumListFragment extends Fragment implements AlbumListMvp.View {
	// Getters and setters are for testing purposes only!
	private @Getter @Setter AlbumListMvp.Presenter presenter;
	private @Getter @Setter AlbumListAdapter adapter;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Do not reassign presenter if it has already been set by unit test
		// For testing purposes only!
		if (presenter == null) {
			AlbumDB albumDB = new AlbumDB(getActivity());
			TrackDB trackDB = new TrackDB(getActivity());
			AlbumListMvp.Model model = new AlbumListModel(getActivity(), albumDB, trackDB);
			presenter = new AlbumListPresenter(model, this);
		}

		presenter.onCreate();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_list, container, false);

		adapter = new AlbumListAdapter(getActivity(), presenter);
		RecyclerView list = (RecyclerView) view.findViewById(R.id.list);
		list.setLayoutManager(new LinearLayoutManager(getActivity()));
		list.setAdapter(adapter);

		return view;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		presenter.onDestroy();
	}

	@Override
	public void goToAlbumDetail(int albumId) {
		AlbumDetailActivity.startActivity(getActivity(), albumId);
	}

	@Override
	public void notifyDataChanged() {
		adapter.notifyDataSetChangedNonFinal();
	}
}
