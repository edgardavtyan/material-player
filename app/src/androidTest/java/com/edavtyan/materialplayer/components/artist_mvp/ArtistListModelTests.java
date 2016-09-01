package com.edavtyan.materialplayer.components.artist_mvp;

import com.edavtyan.materialplayer.lib.db.ArtistDB;
import com.edavtyan.materialplayer.components.artists.Artist;
import com.edavtyan.materialplayer.lib.BaseTest;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArtistListModelTests extends BaseTest {
	private List artists;
	private ArtistListModel model;

	@Override
	@Before
	@SuppressWarnings("unchecked")
	public void beforeEach() {
		super.beforeEach();
		ArtistDB db = mock(ArtistDB.class);
		artists = mock(List.class);
		when(db.getAllArtists()).thenReturn(artists);
		model = new ArtistListModel(db);
	}

	@Test
	public void getArtistCount_correctCount() {
		when(artists.size()).thenReturn(4);
		model.updateData();
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

		model.updateData();

		assertThat(model.getArtistAtIndex(0)).isSameAs(artist);
	}

	@Test
	public void getArtistAtIndex_noArtists_null() {
		assertThat(model.getArtistAtIndex(0)).isNull();
	}
}
