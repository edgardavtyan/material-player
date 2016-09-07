package com.edavtyan.materialplayer.components.artist_all;

import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.lib.BaseTest;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class ArtistListPresenterTest extends BaseTest {
	private ArtistListMvp.Model model;
	private ArtistListMvp.View view;
	private ArtistListViewHolder holder;
	private ArtistListPresenter presenter;

	@Override
	@Before
	public void beforeEach() {
		super.beforeEach();
		model = mock(ArtistListMvp.Model.class);
		view = mock(ArtistListMvp.View.class);
		holder = mock(ArtistListViewHolder.class);
		presenter = new ArtistListPresenter(model, view);
	}

	@Test
	public void getItemCount_countFromModel() {
		when(model.getArtistCount()).thenReturn(5);

		assertThat(presenter.getItemCount()).isEqualTo(5);
	}

	@Test
	public void bindViewHolder_setAllHolderData() {
		Artist artist = new Artist();
		artist.setTitle("title");
		artist.setAlbumsCount(3);
		artist.setTracksCount(11);

		when(model.getArtistAtIndex(0)).thenReturn(artist);

		presenter.onBindViewHolder(holder, 0);

		verify(holder).setTitle("title");
		verify(holder).setInfo(3, 11);
		verifyNoMoreInteractions(holder);
	}

	@Test
	public void onHolderClick_goToArtistDetail() {
		Artist artist = mock(Artist.class);
		when(artist.getTitle()).thenReturn("title");
		when(model.getArtistAtIndex(3)).thenReturn(artist);

		presenter.onHolderClick(3);
		verify(view).goToArtistDetail("title");
	}
}
