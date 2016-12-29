package com.edavtyan.materialplayer.components.album_detail;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AlbumDetailPresenterTest extends BaseTest {
	private AlbumDetailMvp.Model model;
	private AlbumDetailMvp.View view;
	private AlbumDetailMvp.Presenter presenter;

	@Override public void beforeEach() {
		super.beforeEach();

		model = mock(AlbumDetailMvp.Model.class);
		view = mock(AlbumDetailMvp.View.class);
		presenter = new AlbumDetailPresenter(model, view);
	}

	@Test public void onCreate_setViewInfo() {
		Album album = new Album();
		album.setTitle("title");
		album.setArtistTitle("artist");
		album.setTracksCount(9);
		Bitmap art = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		when(model.getAlbum()).thenReturn(album);
		when(model.getAlbumArt()).thenReturn(art);

		presenter.onCreate();

		verify(view).setAlbumTitle("title");
		verify(view).setAlbumInfo("artist", 9);
		verify(view).setAlbumImage(art);
	}
}
