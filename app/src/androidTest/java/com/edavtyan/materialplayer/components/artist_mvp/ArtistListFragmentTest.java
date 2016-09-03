package com.edavtyan.materialplayer.components.artist_mvp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.lib.FragmentTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArtistListFragmentTest extends FragmentTest<ArtistListFragment> {
	private ArtistListAdapter adapter;
	private ArtistListMvp.Presenter presenter;

	@Override
	public void beforeEach() {
		super.beforeEach();

		initFragment(new ArtistListFragment());

		adapter = mock(ArtistListAdapter.class);
		presenter = mock(ArtistListMvp.Presenter.class);

		ArtistListDI mockDI = mock(ArtistListDI.class);
		when(mockDI.provideAdapter()).thenReturn(adapter);
		when(mockDI.providePresenter()).thenReturn(presenter);
		when(app.getArtistListDI(any(), any())).thenReturn(mockDI);
	}

	@Test
	public void onCreate_callPresenter() {
		fragment.onCreate(null);
		verify(presenter).onCreate();
	}

	@Test
	public void onDestroy_callPresenter() {
		fragment.onCreate(null);
		fragment.onDestroy();
		verify(presenter).onDestroy();
	}

	@Test
	public void onCreateView_initList() {
		RecyclerView list = new RecyclerView(context);
		when(fragmentView.findViewById(anyInt())).thenReturn(list);

		fragment.onCreate(null);
		fragment.onCreateView(inflater, null, null);

		assertThat(list.getAdapter()).isEqualTo(adapter);
		assertThat(list.getLayoutManager()).isOfAnyClassIn(LinearLayoutManager.class);
	}
}
