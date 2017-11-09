package com.edavtyan.materialplayer.components.lists.album_list;

import android.annotation.SuppressLint;

import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.testlib.tests.FragmentTest2;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public class AlbumListFragmentTest extends FragmentTest2 {
	private static AlbumListAdapter adapter;
	private static AlbumListMvp.Presenter presenter;
	private static Navigator navigator;
	private static AlbumListFragment fragment;

	@Override
	public void beforeEach() {
		super.beforeEach();

		if (fragment == null) {
			adapter = mock(AlbumListAdapter.class);
			presenter = mock(AlbumListMvp.Presenter.class);
			navigator = mock(Navigator.class);

			AlbumListFactory factory = mock(AlbumListFactory.class);
			when(factory.getAdapter()).thenReturn(adapter);
			when(factory.getPresenter()).thenReturn(presenter);
			when(factory.getNavigator()).thenReturn(navigator);

			app.setAlbumListFactory(factory);

			fragment = new AlbumListFragment();
			initFragment(fragment);
		} else {
			reset(adapter, presenter, navigator);
		}
	}

	@Test
	public void gotoAlbumDetail_callNavigator() {
		fragment.gotoAlbumDetail(7);
		verify(navigator).gotoAlbumDetail(7);
	}
}
