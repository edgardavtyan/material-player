package com.edavtyan.materialplayer.components.artist_all;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.testlib.tests.FragmentTest2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public class ArtistListFragmentTest extends FragmentTest2 {
	private static ArtistListAdapter adapter;
	private static ArtistListMvp.Presenter presenter;
	private static Navigator navigator;
	private static ArtistListFragment fragment;

	@Override
	public void beforeEach() {
		super.beforeEach();

		if (fragment == null) {
			adapter = mock(ArtistListAdapter.class);
			presenter = mock(ArtistListMvp.Presenter.class);
			navigator = mock(Navigator.class);

			ArtistListFactory factory = mock(ArtistListFactory.class);
			when(factory.getAdapter()).thenReturn(adapter);
			when(factory.getPresenter()).thenReturn(presenter);
			when(factory.getNavigator()).thenReturn(navigator);

			app.setArtistListFactory(factory);

			fragment = new ArtistListFragment();
			initFragment(fragment);
		} else {
			reset(adapter, presenter, navigator);
		}
	}

	@Test
	public void onCreate_callPresenter() {
		verify(presenter).onCreate();
	}

	@Test
	public void onCreateView_initList() {
		//noinspection ConstantConditions
		RecyclerView list = (RecyclerView) fragment.getView().findViewById(R.id.list);
		assertThat(list.getAdapter()).isEqualTo(adapter);
		assertThat(list.getLayoutManager()).isInstanceOf(LinearLayoutManager.class);
	}

	@Test
	public void onDestroy_callPresenter() {
		fragment.onDestroy();
		verify(presenter).onDestroy();
	}

	@Test
	public void goToArtistDetail_callNavigator() {
		fragment.goToArtistDetail("title");
		verify(navigator).gotoArtistDetail("title");
	}

	@Test
	public void notifyDataSetChanged_notifyAdapter() {
		fragment.notifyDataSetChanged();
		verify(adapter).notifyDataSetChangedNonFinal();
	}
}
