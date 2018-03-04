package com.edavtyan.materialplayer.ui.lists.artist_list;

import android.annotation.SuppressLint;
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
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public class ArtistListFragmentTest extends FragmentTest {
	private static ArtistListAdapter adapter;
	private static ArtistListPresenter presenter;
	private static Navigator navigator;
	private static SharedTransitionsManager transitionsManager;

	public static class TestArtistListFragment extends ArtistListFragment {
		@Override
		public void onCreate(@Nullable Bundle savedInstanceState) {
			this.adapter = ArtistListFragmentTest.adapter;
			this.presenter = ArtistListFragmentTest.presenter;
			this.navigator = ArtistListFragmentTest.navigator;
			this.transitionsManager = ArtistListFragmentTest.transitionsManager;
			super.onCreate(savedInstanceState);
		}

		@Override
		protected ArtistListDIComponent getComponent() {
			return mock(ArtistListDIComponent.class);
		}
	}

	private ArtistListFragment fragment;

	@Override
	public void beforeEach() {
		super.beforeEach();

		adapter = mock(ArtistListAdapter.class);
		presenter = mock(ArtistListPresenter.class);
		navigator = mock(Navigator.class);
		transitionsManager = mock(SharedTransitionsManager.class);

		fragment = new TestArtistListFragment();
		initFragment(fragment);
	}

	@Test
	public void onCreate_initList() {
		RecyclerView list = (RecyclerView) fragment.getView().findViewById(R.id.list);
		assertThat(list.getAdapter()).isEqualTo(adapter);
		assertThat(list.getLayoutManager()).isInstanceOf(LinearLayoutManager.class);
	}

	@Test
	public void gotoArtistDetail_callNavigator() {
		Bundle bundle = new Bundle();
		SourceSharedViews sharedViews = mock(SourceSharedViews.class);
		when(sharedViews.build()).thenReturn(bundle);

		fragment.gotoArtistDetail("title", sharedViews);

		ArgumentCaptor<Bundle> bundleCaptor = ArgumentCaptor.forClass(Bundle.class);
		verify(navigator).gotoArtistDetailCompact(eq(getActivity()), eq("title"), bundleCaptor.capture());
		assertThatBundle(bundleCaptor.getValue()).isEqualTo(sharedViews.build());
	}
}
