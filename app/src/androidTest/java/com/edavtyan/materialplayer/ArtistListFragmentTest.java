package com.edavtyan.materialplayer;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.components.artist_mvp.ArtistListAdapter;
import com.edavtyan.materialplayer.components.artist_mvp.ArtistListDI;
import com.edavtyan.materialplayer.components.artist_mvp.ArtistListFragment;
import com.edavtyan.materialplayer.lib.FragmentTest2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArtistListFragmentTest extends FragmentTest2<ArtistListFragment> {
	private ArtistListAdapter adapter;

	@Override
	public void beforeEach() {
		super.beforeEach();

		initFragment(new ArtistListFragment());

		adapter = mock(ArtistListAdapter.class);

		ArtistListDI mockDI = mock(ArtistListDI.class);
		when(mockDI.provideAdapter()).thenReturn(adapter);
		when(app.getArtistListDI(any(), any())).thenReturn(mockDI);
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
