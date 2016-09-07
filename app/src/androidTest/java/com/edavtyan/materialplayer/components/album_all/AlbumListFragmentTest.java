package com.edavtyan.materialplayer.components.album_all;

import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.lib.FragmentTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class AlbumListFragmentTest extends FragmentTest<AlbumListFragment> {

	private AlbumListMvp.Presenter presenter;
	private AlbumListAdapter adapter;
	private Navigator navigator;

	@Override
	public void beforeEach() {
		super.beforeEach();

		initFragment(new AlbumListFragment());

		presenter = mock(AlbumListMvp.Presenter.class);
		adapter = mock(AlbumListAdapter.class);
		navigator = mock(Navigator.class);

		AlbumListFactory factory = mock(AlbumListFactory.class);
		when(factory.providePresenter()).thenReturn(presenter);
		when(factory.provideAdapter()).thenReturn(adapter);
		when(factory.provideNavigator()).thenReturn(navigator);
		when(app.getAlbumListDI(any(), any())).thenReturn(factory);
	}

	@Test
	public void onCreate_callPresenter() {
		fragment.onCreate(null);
		verify(presenter).onCreate();
	}

	@Test
	public void onCreateView_setRecyclerViewAdapter() {
		RecyclerView list = new RecyclerView(context);
		when(fragmentView.findViewById(R.id.list)).thenReturn(list);

		fragment.onCreate(null);
		fragment.onCreateView(inflater, null, null);

		assertThat(list.getAdapter()).isEqualTo(adapter);
		assertThat(list.getLayoutManager()).isOfAnyClassIn(LinearLayoutManager.class);
	}

	@Test
	public void onDestroy_callPresenter() {
		fragment.onCreate(null);
		fragment.onDestroy();
		verify(presenter).onDestroy();
	}

	@Test
	public void goToAlbumDetail_callNavigator() {
		fragment.onCreate(null);
		fragment.goToAlbumDetail(7);
		verify(navigator).gotoAlbumDetail(7);
	}
}
