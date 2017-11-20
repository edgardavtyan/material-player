package com.edavtyan.materialplayer.components.lists.track_list;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.testlib.tests.FragmentTest2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("StaticFieldLeak")
public class TrackListFragmentTest extends FragmentTest2 {
	private static TrackListAdapter adapter;
	private static Navigator navigator;
	private static TrackListFragment fragment;

	@Override
	public void beforeEach() {
		super.beforeEach();

		if (fragment == null) {
			adapter = mock(TrackListAdapter.class);
			navigator = mock(Navigator.class);
			TrackListMvp.Presenter presenter = mock(TrackListMvp.Presenter.class);

			TrackListFactory factory = mock(TrackListFactory.class);
			when(factory.getAdapter()).thenReturn(adapter);
			when(factory.getPresenter()).thenReturn(presenter);
			when(factory.getNavigator()).thenReturn(navigator);

			app.setTrackListFactory(factory);

			fragment = new TrackListFragment();
			initFragment(fragment);
		}
	}

	@Test
	public void onCreateView_initList() {
		RecyclerView list = (RecyclerView) fragment.getView().findViewById(R.id.list);
		assertThat(list.getAdapter()).isEqualTo(adapter);
		assertThat(list.getLayoutManager()).isInstanceOf(LinearLayoutManager.class);
	}

	@Test
	public void gotoNowPlaying_callNavigator() {
		fragment.gotoNowPlaying();
		verify(navigator).gotoNowPlaying();
	}
}
