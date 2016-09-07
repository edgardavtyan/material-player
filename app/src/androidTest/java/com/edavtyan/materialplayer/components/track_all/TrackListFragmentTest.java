package com.edavtyan.materialplayer.components.track_all;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.components.Navigator;
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
	private Navigator navigator;

	@Override
	public void beforeEach() {
		super.beforeEach();

		initFragment(new TrackListFragment());

		presenter = mock(TrackListMvp.Presenter.class);
		adapter = mock(TrackListAdapter.class);
		navigator  =mock(Navigator.class);

		TrackListFactory factory = mock(TrackListFactory.class);
		when(factory.providePresenter()).thenReturn(presenter);
		when(factory.provideAdapter()).thenReturn(adapter);
		when(factory.provideNavigator()).thenReturn(navigator);
		when(app.getTrackListDI(any(), any())).thenReturn(factory);
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
		assertThat(list.getAdapter()).isEqualTo(adapter);
	}

	@Test
	public void goToNowPlaying_callNavigator() {
		fragment.onCreate(null);
		fragment.goToNowPlaying();
		verify(navigator).gotoNowPlaying();
	}
}
