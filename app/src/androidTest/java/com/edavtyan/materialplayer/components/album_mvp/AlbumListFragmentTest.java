package com.edavtyan.materialplayer.components.album_mvp;

import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.album_mvp.AlbumListAdapter;
import com.edavtyan.materialplayer.components.album_mvp.AlbumListDI;
import com.edavtyan.materialplayer.components.album_mvp.AlbumListFragment;
import com.edavtyan.materialplayer.components.album_mvp.AlbumListMvp;
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

	@Override
	public void beforeEach() {
		super.beforeEach();

		initFragment(new AlbumListFragment());

		presenter = mock(AlbumListMvp.Presenter.class);
		adapter = mock(AlbumListAdapter.class);

		AlbumListDI mockDI = mock(AlbumListDI.class);
		when(mockDI.providePresenter()).thenReturn(presenter);
		when(mockDI.provideAdapter()).thenReturn(adapter);
		when(app.getAlbumListDI(any(), any())).thenReturn(mockDI);
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
	public void notifyDataChanged_callAdapter() {
		fragment.onCreate(null);
		fragment.notifyDataChanged();
		verify(adapter).notifyDataSetChangedNonFinal();
	}
}
