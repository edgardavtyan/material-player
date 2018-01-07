package com.edavtyan.materialplayer.components.lists.artist_list;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.testlib.tests.FragmentTest2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SuppressLint("StaticFieldLeak")
public class ArtistListFragmentTest extends FragmentTest2 {
	private static ArtistListAdapter adapter;
	private static ArtistListPresenter presenter;
	private static Navigator navigator;

	public static class TestArtistListFragment extends ArtistListFragment {
		@Override
		public void onCreate(@Nullable Bundle savedInstanceState) {
			this.adapter = ArtistListFragmentTest.adapter;
			this.presenter = ArtistListFragmentTest.presenter;
			this.navigator = ArtistListFragmentTest.navigator;
			super.onCreate(savedInstanceState);
		}

		@Override
		protected ArtistListComponent getComponent() {
			return mock(ArtistListComponent.class);
		}
	}

	private ArtistListFragment fragment;

	@Override
	public void beforeEach() {
		super.beforeEach();

		adapter = mock(ArtistListAdapter.class);
		presenter = mock(ArtistListPresenter.class);
		navigator = mock(Navigator.class);

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
		fragment.gotoArtistDetail("title");
		verify(navigator).gotoArtistDetail("title");
	}
}
