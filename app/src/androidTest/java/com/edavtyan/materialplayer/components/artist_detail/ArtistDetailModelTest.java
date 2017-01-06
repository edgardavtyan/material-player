package com.edavtyan.materialplayer.components.artist_detail;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.mvp.list.CompactListPref;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArtistDetailModelTest extends BaseTest {
	private ArtistDB artistDB;
	private ArtistDetailModel model;
	private ArtistDetailImageLoader artistArtLoader;

	@Override public void beforeEach() {
		super.beforeEach();

		artistDB = mock(ArtistDB.class);
		artistArtLoader = mock(ArtistDetailImageLoader.class);
		model = new ArtistDetailModel(
				context,
				artistDB,
				mock(AlbumDB.class),
				mock(TrackDB.class),
				mock(CompactListPref.class),
				artistArtLoader,
				"title");
	}

	@Test public void getArtist_existingArtist_correctArtist() {
		Artist artist = new Artist();
		artist.setId(3);
		artist.setTitle("title");
		artist.setAlbumsCount(3);
		artist.setTracksCount(9);

		when(artistDB.getArtistWithTitle("title")).thenReturn(artist);

		assertThat(model.getArtist()).isEqualTo(artist);
	}

	@Test public void getLocalArtistImage_returnImageFromArtistArtLoader() {
		Bitmap art = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		when(artistArtLoader.getArtFromLocalStorage("title")).thenReturn(art);
		assertThat(model.getLocalArtistImage()).isEqualTo(art);
	}

	@Test public void loadArtistImage_callOnArtLoadedListener() throws InterruptedException {
		Bitmap art = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		when(artistArtLoader.load(anyString())).thenReturn(art);

		ArtistImageTask.OnArtLoadedCallback callback = mock(ArtistImageTask.OnArtLoadedCallback.class);

		model.loadArtistImageFromApi(callback);

		verify(callback, timeout(1000)).OnArtLoaded(art);
	}
}
