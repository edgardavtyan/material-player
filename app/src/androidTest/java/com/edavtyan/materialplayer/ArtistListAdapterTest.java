package com.edavtyan.materialplayer;

import android.support.test.runner.AndroidJUnit4;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.components.artist_mvp.ArtistListAdapter;
import com.edavtyan.materialplayer.components.artist_mvp.ArtistListMvp;
import com.edavtyan.materialplayer.components.artist_mvp.ArtistListViewHolder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class ArtistListAdapterTest extends BaseTest {

	private ArtistListMvp.Presenter presenter;
	private ArtistListAdapter adapter;
	private ArtistListViewHolder holder;

	@Before
	public void beforeEach() {
		super.beforeEach();
		presenter = mock(ArtistListMvp.Presenter.class);
		adapter = new ArtistListAdapter(context, presenter);
		holder = mock(ArtistListViewHolder.class);
	}

	@Test
	public void onCreateViewHolder_createsHolder() {
		ViewGroup parent = mock(ViewGroup.class);
		ArtistListViewHolder holder = adapter.onCreateViewHolder(parent, 0);
		assertThat(holder).isNotNull();
	}

	@Test
	public void onBindViewHolder_callPresenter() {
		adapter.onBindViewHolder(holder, 0);
		verify(presenter).bindViewHolder(holder, 0);
	}

	@Test
	public void onBindViewHolder_setClickListener() {
		adapter.onBindViewHolder(holder, 0);
		verify(holder).setOnHolderClickListener(adapter);
	}

	@Test
	public void getItemCount_callPresenter() {
		adapter.getItemCount();
		verify(presenter).getItemCount();
	}

	@Test
	public void onClick_callPresenter() {
		adapter.onHolderClick(holder, 3);
		verify(presenter).onHolderClicked(holder, 3);
	}
}
