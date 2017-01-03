package com.edavtyan.materialplayer.components.artist_all;

import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArtistListPresenterTest extends BaseTest {
	private ArtistListMvp.Model model;
	private ArtistListMvp.View view;
	private ArtistListViewHolder holder;
	private ArtistListPresenter presenter;

	@Override public void beforeEach() {
		super.beforeEach();
		model = mock(ArtistListMvp.Model.class);
		view = mock(ArtistListMvp.View.class);
		holder = mock(ArtistListViewHolder.class);
		presenter = new ArtistListPresenter(model, view);
	}

	@Test public void bindViewHolder_setAllHolderValues() {
		Artist artist = new Artist();
		artist.setTitle("title");
		artist.setAlbumsCount(3);
		artist.setTracksCount(11);

		when(model.getArtistAtIndex(0)).thenReturn(artist);

		presenter.onBindViewHolder(holder, 0);

		verify(holder).setTitle(artist.getTitle());
		verify(holder).setInfo(artist.getAlbumsCount(), artist.getTracksCount());
	}

	@Test public void getItemCount_countFromModel() {
		when(model.getArtistCount()).thenReturn(5);
		assertThat(presenter.getItemCount()).isEqualTo(5);
	}

	@Test public void onHolderClick_goToArtistDetail() {
		Artist artist = new Artist();
		artist.setTitle("title");
		when(model.getArtistAtIndex(3)).thenReturn(artist);

		presenter.onHolderClick(3);
		verify(view).goToArtistDetail("title");
	}

	@Test public void onCreate_initModel() {
		presenter.onCreate();
		verify(model).update();
	}

	@Test public void onDestroy_doesNothing() {
		presenter.onDestroy();
	}
}
