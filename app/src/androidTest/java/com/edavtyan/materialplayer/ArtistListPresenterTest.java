package com.edavtyan.materialplayer;

import com.edavtyan.materialplayer.components.artist_mvp.ArtistListMvp;
import com.edavtyan.materialplayer.components.artist_mvp.ArtistListPresenter;
import com.edavtyan.materialplayer.components.artist_mvp.ArtistsListViewHolder;
import com.edavtyan.materialplayer.components.artists.Artist;

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
	private ArtistsListViewHolder holder;
	private ArtistListPresenter presenter;

	@Override
	@Before
	public void beforeEach() {
		super.beforeEach();
		model = mock(ArtistListMvp.Model.class);
		view = mock(ArtistListMvp.View.class);
		holder = mock(ArtistsListViewHolder.class);
		presenter = new ArtistListPresenter(model, view);
	}

	@Test
	public void getItemCount_returnCountFromModel() {
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

		presenter.bindViewHolder(holder, 0);

		verify(holder).setTitle("title");
		verify(holder).setInfo(3, 11);
		verifyNoMoreInteractions(holder);
	}

	@Test
	public void onHolderClick_goToArtistDetail() {
		when(holder.getTitle()).thenReturn("title");

		presenter.onHolderClicked(holder, 0);

		verify(view).goToArtistDetail("title");
	}
}
