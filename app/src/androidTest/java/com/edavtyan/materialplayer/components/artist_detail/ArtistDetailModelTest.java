package com.edavtyan.materialplayer.components.artist_detail;

import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArtistDetailModelTest extends BaseTest {
	private ArtistDB artistDB;
	private ArtistDetailModel model;

	@Override public void beforeEach() {
		super.beforeEach();

		artistDB = mock(ArtistDB.class);
		model = new ArtistDetailModel(
				context,
				artistDB,
				mock(AlbumDB.class),
				mock(TrackDB.class),
				mock(AdvancedSharedPrefs.class),
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
}
