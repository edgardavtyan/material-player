package com.edavtyan.materialplayer.components.artist_detail;

import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArtistDetailPresenterTest extends BaseTest {
	private ArtistDetailMvp.Model model;
	private ArtistDetailMvp.View view;
	private ArtistDetailMvp.Presenter presenter;

	@Override public void beforeEach() {
		super.beforeEach();

		model = mock(ArtistDetailMvp.Model.class);
		view = mock(ArtistDetailMvp.View.class);
		presenter = new ArtistDetailPresenter(model, view);
	}

	@Test public void onCreate_setViewTitleAndInfo() {
		Artist artist = new Artist();
		artist.setTitle("title");
		artist.setAlbumsCount(3);
		artist.setTracksCount(9);
		when(model.getArtist()).thenReturn(artist);

		presenter.onCreate();

		verify(view).setArtistTitle("title");
		verify(view).setArtistInfo(3, 9);
	}

	@Test public void onCreate_loadArtistImage() {
		when(model.getArtist()).thenReturn(new Artist());

		presenter.onCreate();

		verify(model).loadArtistImage(any());
	}
}
