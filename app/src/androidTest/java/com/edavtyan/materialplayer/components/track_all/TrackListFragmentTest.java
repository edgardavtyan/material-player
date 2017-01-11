package com.edavtyan.materialplayer.components.track_all;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.testlib.tests.FragmentTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TrackListFragmentTest extends FragmentTest<TrackListFragment> {
	private TrackListAdapter adapter;
	private TrackListMvp.Presenter presenter;
	private Navigator navigator;

	@Override
	public void beforeEach() {
		super.beforeEach();

		initFragment(new TrackListFragment());

		adapter = mock(TrackListAdapter.class);
		presenter = mock(TrackListMvp.Presenter.class);
		navigator = mock(Navigator.class);

		TrackListFactory factory = mock(TrackListFactory.class);
		when(factory.provideAdapter()).thenReturn(adapter);
		when(factory.providePresenter()).thenReturn(presenter);
		when(factory.provideNavigator()).thenReturn(navigator);
		when(app.getTrackListDI(any(), any())).thenReturn(factory);
	}

	@Test
	public void onCreate_callPresenter() {
		fragment.onCreate(null);
		verify(presenter).onCreate();
	}

	@Test
	public void onCreateView_initList() {
		RecyclerView list = new RecyclerView(context);
		when(fragmentView.findViewById(R.id.list)).thenReturn(list);

		fragment.onCreate(null);
		fragment.onCreateView(inflater, null, null);

		assertThat(list.getAdapter()).isEqualTo(adapter);
		assertThat(list.getLayoutManager()).isInstanceOf(LinearLayoutManager.class);
	}

	@Test
	public void onDestroy_callPresenter() {
		fragment.onCreate(null);
		fragment.onDestroy();
		verify(presenter).onDestroy();
	}

	@Test
	public void goToNowPlaying_callNavigator() {
		fragment.onCreate(null);
		fragment.goToNowPlaying();
		verify(navigator).gotoNowPlaying();
	}
}
