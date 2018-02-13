package com.edavtyan.materialplayer.screens.detail.album_detail;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AlbumDetailPresenterTest extends BaseTest {
	private AlbumDetailModel model;
	private AlbumDetailActivity activity;
	private AlbumDetailPresenter presenter;

	@Override
	public void beforeEach() {
		super.beforeEach();

		model = mock(AlbumDetailModel.class);
		activity = mock(AlbumDetailActivity.class);
		presenter = new AlbumDetailPresenter(model, activity);
	}

	@Test
	public void onCreate_setViewInfo() {
		Album album = new Album();
		album.setTitle("title");
		album.setArtistTitle("artist");
		album.setTracksCount(9);
		when(model.getAlbum()).thenReturn(album);

		Bitmap art = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		when(model.getAlbumArt()).thenReturn(art);

		presenter.onCreate();

		verify(activity).setAlbumTitle("title");
		verify(activity).setAlbumInfo("artist", 9, 0);
		verify(activity).setAlbumImage(art);
	}
}
