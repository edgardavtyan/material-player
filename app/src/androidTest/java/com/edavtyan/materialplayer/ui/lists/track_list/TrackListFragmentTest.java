package com.edavtyan.materialplayer.ui.lists.track_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.testlib.tests.FragmentTest;
import com.edavtyan.materialplayer.transition.SharedTransitionsManager;
import com.edavtyan.materialplayer.transition.SourceSharedViews;
import com.edavtyan.materialplayer.ui.Navigator;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static com.edavtyan.materialplayer.testlib.assertions.Assertions.assertThatBundle;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SuppressWarnings("StaticFieldLeak")
public class TrackListFragmentTest extends FragmentTest {
	private static TrackListAdapter adapter;
	private static TrackListPresenter presenter;
	private static Navigator navigator;
	private static SharedTransitionsManager transitionManager;

	public static class TestTrackListFragment extends TrackListFragment {
		@Override
		public void onCreate(@Nullable Bundle savedInstanceState) {
			this.adapter = TrackListFragmentTest.adapter;
			this.presenter = TrackListFragmentTest.presenter;
			this.navigator = TrackListFragmentTest.navigator;
			this.transitionsManager = TrackListFragmentTest.transitionManager;
			super.onCreate(savedInstanceState);
		}

		@Override
		protected TrackListDIComponent getComponent() {
			return mock(TrackListDIComponent.class);
		}
	}

	private TestTrackListFragment fragment;

	@Override
	public void beforeEach() {
		super.beforeEach();

		adapter = mock(TrackListAdapter.class);
		navigator = mock(Navigator.class);
		presenter = mock(TrackListPresenter.class);
		transitionManager = mock(SharedTransitionsManager.class);

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
		SourceSharedViews sharedViews = new SourceSharedViews(getActivity());

		ArgumentCaptor<Bundle> bundleCaptor = ArgumentCaptor.forClass(Bundle.class);
		verify(navigator).gotoNowPlaying(eq(getActivity()), bundleCaptor.capture());
		assertThatBundle(bundleCaptor.getValue()).isEqualTo(sharedViews.build());
	}
}
