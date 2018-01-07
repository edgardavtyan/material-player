package com.edavtyan.materialplayer.components.lists.track_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.testlib.tests.FragmentTest2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SuppressWarnings("StaticFieldLeak")
public class TrackListFragmentTest extends FragmentTest2 {
	private static TrackListAdapter adapter;
	private static TrackListPresenter presenter;
	private static Navigator navigator;

	public static class TestTrackListFragment extends TrackListFragment {
		@Override
		public void onCreate(@Nullable Bundle savedInstanceState) {
			this.adapter = TrackListFragmentTest.adapter;
			this.presenter = TrackListFragmentTest.presenter;
			this.navigator = TrackListFragmentTest.navigator;
			super.onCreate(savedInstanceState);
		}

		@Override
		protected TrackListComponent getComponent() {
			return mock(TrackListComponent.class);
		}
	}

	private TestTrackListFragment fragment;

	@Override
	public void beforeEach() {
		super.beforeEach();

		adapter = mock(TrackListAdapter.class);
		navigator = mock(Navigator.class);
		presenter = mock(TrackListPresenter.class);

		fragment = new TestTrackListFragment();
		initFragment(fragment);
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
