package com.edavtyan.materialplayer.components.lists.album_list;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.testlib.tests.FragmentTest2;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SuppressLint("StaticFieldLeak")
public class AlbumListFragmentTest extends FragmentTest2 {
	private static AlbumListAdapter adapter;
	private static AlbumListPresenter presenter;
	private static Navigator navigator;

	public static class TestAlbumListFragment extends AlbumListFragment {
		@Override
		public void onCreate(@Nullable Bundle savedInstanceState) {
			this.adapter = AlbumListFragmentTest.adapter;
			this.presenter = AlbumListFragmentTest.presenter;
			this.navigator = AlbumListFragmentTest.navigator;
			super.onCreate(savedInstanceState);
		}

		@Override
		protected AlbumListComponent getComponent() {
			return mock(AlbumListComponent.class);
		}
	}

	private TestAlbumListFragment fragment;

	@Override
	public void beforeEach() {
		super.beforeEach();

		adapter = mock(AlbumListAdapter.class);
		presenter = mock(AlbumListPresenter.class);
		navigator = mock(Navigator.class);

		fragment = new TestAlbumListFragment();
		initFragment(fragment);
	}

	@Test
	public void gotoAlbumDetail_callNavigator() {
		fragment.gotoAlbumDetail(7);
		verify(navigator).gotoAlbumDetail(7);
	}
}
