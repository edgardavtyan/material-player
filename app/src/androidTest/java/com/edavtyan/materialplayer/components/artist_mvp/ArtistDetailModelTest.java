package com.edavtyan.materialplayer.components.artist_mvp;

import com.edavtyan.materialplayer.lib.BaseTest;
import com.edavtyan.materialplayer.lib.db.AlbumDB;
import com.edavtyan.materialplayer.lib.db.ArtistDB;
import com.edavtyan.materialplayer.lib.db.TrackDB;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArtistDetailModelTest extends BaseTest {
	private ArtistDB artistDB;
	private ArtistDetailModel model;

	@Override
	public void beforeEach() {
		super.beforeEach();

		artistDB = mock(ArtistDB.class);
		model = new ArtistDetailModel(
				context,
				artistDB,
				mock(AlbumDB.class),
				mock(TrackDB.class),
				"title");
	}

	@Test
	public void getArtist_existingArtist_correctArtist()
	{
		Artist artist = new Artist();
		artist.setId(3);
		artist.setTitle("title");
		artist.setAlbumsCount(3);
		artist.setTracksCount(9);

		when(artistDB.getArtistWithTitle("title")).thenReturn(artist);

		assertThat(model.getArtist()).isEqualTo(artist);
	}
}
