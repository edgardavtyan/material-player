package com.edavtyan.materialplayer.components.track_mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.BaseFragment;
import com.edavtyan.materialplayer.components.nowplaying.NowPlayingActivity;

public class TrackListFragment extends BaseFragment implements TrackListMvp.View {
	private TrackListMvp.Presenter presenter;
	private TrackListAdapter adapter;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TrackListDI trackListDI = app.getTrackListDI(getContext(), this);
		presenter = trackListDI.providePresenter();
		adapter = trackListDI.provideAdapter();

		presenter.onCreate();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_list, container, false);

		RecyclerView list = (RecyclerView) view.findViewById(R.id.list);
		list.setAdapter(adapter);
		list.setLayoutManager(new LinearLayoutManager(getActivity()));

		return view;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		presenter.onDestroy();
	}

	@Override
	public void goToNowPlaying() {
		NowPlayingActivity.startActivity(getActivity());
	}
}
