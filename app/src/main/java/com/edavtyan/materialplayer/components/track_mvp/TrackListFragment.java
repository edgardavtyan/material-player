package com.edavtyan.materialplayer.components.track_mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.nowplaying.NowPlayingActivity;

import lombok.Getter;
import lombok.Setter;

public class TrackListFragment extends Fragment implements TrackListMvp.View {
	private @Getter @Setter TrackListMvp.Presenter presenter;
	private @Getter @Setter TrackListAdapter adapter;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (adapter == null) adapter = new TrackListAdapter(getActivity(), presenter);
		if (presenter != null) presenter.onCreate();
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
	public void notifyDataChanged() {
		adapter.notifyDataSetChangedNonFinal();
	}

	@Override
	public void goToNowPlaying() {
		NowPlayingActivity.startActivity(getActivity());
	}
}
