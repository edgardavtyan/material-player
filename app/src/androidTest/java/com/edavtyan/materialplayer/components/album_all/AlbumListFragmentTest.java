package com.edavtyan.materialplayer.components.album_all;

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

public class AlbumListFragmentTest extends FragmentTest<AlbumListFragment> {
	private AlbumListAdapter adapter;
	private AlbumListMvp.Presenter presenter;
	private Navigator navigator;

	@Override
	public void beforeEach() {
		super.beforeEach();

		initFragment(new AlbumListFragment());

		adapter = mock(AlbumListAdapter.class);
		presenter = mock(AlbumListMvp.Presenter.class);
		navigator = mock(Navigator.class);

		AlbumListFactory factory = mock(AlbumListFactory.class);
		when(factory.provideAdapter()).thenReturn(adapter);
		when(factory.providePresenter()).thenReturn(presenter);
		when(factory.provideNavigator()).thenReturn(navigator);
		when(app.getAlbumListDI(any(), any())).thenReturn(factory);
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
	public void goToAlbumDetail_callNavigator() {
		fragment.onCreate(null);
		fragment.goToAlbumDetail(7);
		verify(navigator).gotoAlbumDetail(7);
	}

	@Test
	public void notify_adapter_data_changed() {
		fragment.onCreate(null);
		fragment.notifyDataSetChanged();
		verify(adapter).notifyDataSetChangedNonFinal();
	}
}
