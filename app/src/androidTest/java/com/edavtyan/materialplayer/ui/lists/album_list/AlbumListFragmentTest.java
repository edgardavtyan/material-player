package com.edavtyan.materialplayer.ui.lists.album_list;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.testlib.tests.FragmentTest;
import com.edavtyan.materialplayer.transition.SharedTransitionsManager;
import com.edavtyan.materialplayer.transition.SourceSharedViews;
import com.edavtyan.materialplayer.ui.Navigator;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public class AlbumListFragmentTest extends FragmentTest {
	private static AlbumListAdapter adapter;
	private static AlbumListPresenter presenter;
	private static Navigator navigator;
	private static SharedTransitionsManager transitionManager;

	public static class TestAlbumListFragment extends AlbumListFragment {
		@Override
		public void onCreate(@Nullable Bundle savedInstanceState) {
			this.adapter = AlbumListFragmentTest.adapter;
			this.presenter = AlbumListFragmentTest.presenter;
			this.navigator = AlbumListFragmentTest.navigator;
			this.transitionsManager = AlbumListFragmentTest.transitionManager;
			super.onCreate(savedInstanceState);
		}

		@Override
		protected AlbumListDIComponent getComponent() {
			return mock(AlbumListDIComponent.class);
		}
	}

	private TestAlbumListFragment fragment;

	@Override
	public void beforeEach() {
		super.beforeEach();

		adapter = mock(AlbumListAdapter.class);
		presenter = mock(AlbumListPresenter.class);
		navigator = mock(Navigator.class);
		transitionManager = mock(SharedTransitionsManager.class);

		fragment = new TestAlbumListFragment();
		initFragment(fragment);
	}

	@Test
	public void gotoAlbumDetail_callNavigator() {
		Bundle bundle = new Bundle();
		SourceSharedViews sharedViews = mock(SourceSharedViews.class);
		when(sharedViews.build()).thenReturn(bundle);
		fragment.gotoAlbumDetail(7, sharedViews);
		verify(navigator).gotoAlbumDetail(getActivity(), 7, bundle);
	}
}
