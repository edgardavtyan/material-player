package com.edavtyan.materialplayer.components.artist_all;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.lib.FragmentTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArtistListFragmentTest extends FragmentTest<ArtistListFragment> {
	private ArtistListAdapter adapter;
	private ArtistListMvp.Presenter presenter;
	private Navigator navigator;

	@Override public void beforeEach() {
		super.beforeEach();

		initFragment(new ArtistListFragment());

		adapter = mock(ArtistListAdapter.class);
		presenter = mock(ArtistListMvp.Presenter.class);
		navigator = mock(Navigator.class);

		ArtistListFactory factory = mock(ArtistListFactory.class);
		when(factory.provideAdapter()).thenReturn(adapter);
		when(factory.providePresenter()).thenReturn(presenter);
		when(factory.provideNavigator()).thenReturn(navigator);
		when(app.getArtistListDI(any(), any())).thenReturn(factory);
	}

	@Test public void onCreate_callPresenter() {
		fragment.onCreate(null);
		verify(presenter).onCreate();
	}

	@Test public void onCreateView_initList() {
		RecyclerView list = new RecyclerView(context);
		when(fragmentView.findViewById(R.id.list)).thenReturn(list);

		fragment.onCreate(null);
		fragment.onCreateView(inflater, null, null);

		assertThat(list.getAdapter()).isEqualTo(adapter);
		assertThat(list.getLayoutManager()).isInstanceOf(LinearLayoutManager.class);
	}

	@Test public void onDestroy_callPresenter() {
		fragment.onCreate(null);
		fragment.onDestroy();
		verify(presenter).onDestroy();
	}

	@Test public void goToArtistDetail_callNavigator() {
		fragment.onCreate(null);
		fragment.goToArtistDetail("title");
		verify(navigator).gotoArtistDetail("title");
	}
}
