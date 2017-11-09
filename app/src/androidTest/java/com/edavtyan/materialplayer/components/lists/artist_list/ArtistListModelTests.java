package com.edavtyan.materialplayer.components.lists.artist_list;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.components.lists.lib.CompactListPref;
import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArtistListModelTests extends BaseTest {
	private List artists;
	private ArtistListModel model;
	private ArtistListImageLoader imageLoader;

	@SuppressWarnings("unchecked")
	@Override
	public void beforeEach() {
		super.beforeEach();
		ArtistDB db = mock(ArtistDB.class);
		artists = mock(List.class);
		when(db.getAllArtists()).thenReturn(artists);

		CompactListPref prefs = mock(CompactListPref.class);
		imageLoader = mock(ArtistListImageLoader.class);

		TrackDB trackDB = mock(TrackDB.class);
		ModelServiceModule serviceModule = mock(ModelServiceModule.class);

		model = new ArtistListModel(serviceModule, db, trackDB, imageLoader, prefs);
	}

	@Test
	public void getArtistCount_correctCount() {
		when(artists.size()).thenReturn(4);
		model.update();
		assertThat(model.getArtistCount()).isEqualTo(4);
	}

	@Test
	public void getArtistCount_noArtists_zero() {
		assertThat(model.getArtistCount()).isEqualTo(0);
	}

	@Test
	public void getArtistAtIndex_correctArtist() {
		Artist artist = new Artist();
		when(artists.get(0)).thenReturn(artist);

		model.update();

		assertThat(model.getArtistAtIndex(0)).isSameAs(artist);
	}

	@Test
	public void getArtistAtIndex_noArtists_null() {
		assertThat(model.getArtistAtIndex(0)).isNull();
	}

	@Test
	public void getArtistImage_imageCached_callCallbackSynchronously() {
		Bitmap art = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		when(imageLoader.getImageFromMemoryCache(any())).thenReturn(art);

		ArtistListImageTask.Callback callback = mock(ArtistListImageTask.Callback.class);

		Artist artist = new Artist();
		artist.setTitle("title");
		when(artists.get(0)).thenReturn(artist);

		model.update();
		model.getArtistImage(0, callback);

		verify(callback).onArtLoaded(art);
	}

	@Test
	public void getArtistImage_imageNonCached_callCallbackViaImageLoadTask() {
		Bitmap art = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		when(imageLoader.getImageFromFileSystemOrApi("title")).thenReturn(art);
		when(imageLoader.getImageFromMemoryCache("title")).thenReturn(null);

		ArtistListImageTask.Callback callback = mock(ArtistListImageTask.Callback.class);

		Artist artist = new Artist();
		artist.setTitle("title");
		when(artists.get(0)).thenReturn(artist);

		model.update();
		model.getArtistImage(0, callback);

		verify(callback, timeout(1000)).onArtLoaded(art);
	}
}
