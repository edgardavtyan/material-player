package com.edavtyan.materialplayer.components.album_mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.BaseFragment;
import com.edavtyan.materialplayer.components.albums.AlbumDetailActivity;

public class AlbumListFragment extends BaseFragment implements AlbumListMvp.View {
	private AlbumListMvp.Presenter presenter;
	private AlbumListAdapter adapter;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AlbumListDI albumListDI = app.getAlbumListDI(getActivity(), this);
		presenter = albumListDI.providePresenter();
		adapter = albumListDI.provideAdapter();

		presenter.onCreate();
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
