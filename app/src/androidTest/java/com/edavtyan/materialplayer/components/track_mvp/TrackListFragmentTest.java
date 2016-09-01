package com.edavtyan.materialplayer.components.track_mvp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.components.track_mvp.TrackListAdapter;
import com.edavtyan.materialplayer.components.track_mvp.TrackListDI;
import com.edavtyan.materialplayer.components.track_mvp.TrackListFragment;
import com.edavtyan.materialplayer.components.track_mvp.TrackListMvp;
import com.edavtyan.materialplayer.lib.FragmentTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TrackListFragmentTest extends FragmentTest<TrackListFragment> {
	private TrackListMvp.Presenter presenter;
	private TrackListAdapter adapter;

	@Override
	public void beforeEach() {
		super.beforeEach();

		initFragment(new TrackListFragment());

		presenter = mock(TrackListMvp.Presenter.class);
		adapter = mock(TrackListAdapter.class);

		TrackListDI mockDI = mock(TrackListDI.class);
		when(mockDI.providePresenter()).thenReturn(presenter);
		when(mockDI.provideAdapter()).thenReturn(adapter);
		when(app.getTrackListDI(any(), any())).thenReturn(mockDI);
	}

	@Test
	public void onCreate_callPresenter() {
		fragment.onCreate(null);
		verify(presenter).onCreate();
	}

	@Test
	public void onDestroy_callPresenter() {
		fragment.onCreate(null);
		fragment.onDestroy();
		verify(presenter).onDestroy();
	}

	@Test
	public void onCreateView_initRecyclerView() {
		RecyclerView list = new RecyclerView(context);
		when(fragmentView.findViewById(anyInt())).thenReturn(list);

		fragment.onCreate(null);
		fragment.onCreateView(inflater, null, null);

		assertThat(list.getLayoutManager()).isInstanceOf(LinearLayoutManager.class);
		assertThat(list.getAdapter()).isInstanceOf(TrackListAdapter.class);
	}

	@Test
	public void notifyDataChanged_callAdapter() {
		fragment.onCreate(null);
		fragment.notifyDataChanged();

		verify(adapter).notifyDataSetChangedNonFinal();
	}
}
