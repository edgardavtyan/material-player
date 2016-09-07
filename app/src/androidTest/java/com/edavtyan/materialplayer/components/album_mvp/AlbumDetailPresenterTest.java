package com.edavtyan.materialplayer.components.album_mvp;

import com.edavtyan.materialplayer.lib.BaseTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AlbumDetailPresenterTest extends BaseTest {
	private AlbumDetailMvp.Model model;
	private AlbumDetailMvp.View view;
	private AlbumDetailMvp.Presenter presenter;

	@Override
	public void beforeEach() {
		super.beforeEach();

		model = mock(AlbumDetailMvp.Model.class);
		view = mock(AlbumDetailMvp.View.class);
		presenter = new AlbumDetailPresenter(view, model);
	}

	@Test
	public void onCreate_setViewInfo() {
		Album album = new Album();
		album.setTitle("title");
		album.setArtistTitle("artist");
		album.setTracksCount(9);
		when(model.getAlbum()).thenReturn(album);

		presenter.onCreate();

		verify(view).setAlbumTitle("title");
		verify(view).setAlbumInfo("artist", 9);
	}
}
