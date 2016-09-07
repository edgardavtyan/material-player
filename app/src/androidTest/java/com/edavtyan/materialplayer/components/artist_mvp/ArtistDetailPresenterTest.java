package com.edavtyan.materialplayer.components.artist_mvp;

import com.edavtyan.materialplayer.lib.BaseTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArtistDetailPresenterTest extends BaseTest {
	private ArtistDetailModel model;
	private ArtistDetailPresenter presenter;
	private ArtistDetailActivity view;

	@Override
	public void beforeEach() {
		super.beforeEach();

		model = mock(ArtistDetailModel.class);
		view = mock(ArtistDetailActivity.class);
		presenter = new ArtistDetailPresenter(model, view);
	}

	@Test
	public void onCreate_setViewTitleAndInfo() {
		Artist artist = new Artist();
		artist.setTitle("title");
		artist.setAlbumsCount(3);
		artist.setTracksCount(9);

		when(model.getArtist()).thenReturn(artist);

		presenter.onCreate();

		verify(view).setArtistTitle("title");
		verify(view).setArtistInfo(3, 9);
	}
}
