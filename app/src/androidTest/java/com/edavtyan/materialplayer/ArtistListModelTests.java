package com.edavtyan.materialplayer;

import android.database.MatrixCursor;
import android.test.mock.MockContentProvider;
import android.test.mock.MockContentResolver;

import com.edavtyan.materialplayer.components.artist_mvp.ArtistListModel;
import com.edavtyan.materialplayer.components.artists.Artist;
import com.edavtyan.materialplayer.lib.BaseTest;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArtistListModelTests extends BaseTest {
	private ArtistListModel model;
	private ArtistListModel mockResolverModel;
	private MockContentProvider mockProvider;

	@Override
	@Before
	public void beforeEach() {
		super.beforeEach();
		mockProvider = mock(MockContentProvider.class);
		MockContentResolver mockResolver = mock(MockContentResolver.class);
		mockResolver.addProvider("media", mockProvider);
		mockResolverModel = new ArtistListModel(mockResolver);
		model = new ArtistListModel(context.getContentResolver());
	}

	@Test
	public void getArtistCount_correctCount() {
		model.updateData();
		assertThat(model.getArtistCount()).isEqualTo(4);
	}

	@Test
	public void getArtistCount_noArtists_zero() {
		MatrixCursor cursor = new MatrixCursor(ArtistListModel.PROJECTION);
		when(mockProvider.query(any(), any(), any(), any(), any())).thenReturn(cursor);

		assertThat(mockResolverModel.getArtistCount()).isEqualTo(0);
	}

	@Test
	public void getArtistAtIndex_correctArtist() {
		model.updateData();

		Artist modelArtist = model.getArtistAtIndex(0);

		Artist artist = new Artist();
		artist.setId(modelArtist.getId());
		artist.setTitle("Artist 1");
		artist.setAlbumsCount(2);
		artist.setTracksCount(6);

		assertThat(modelArtist).isEqualTo(artist);
	}

	@Test
	public void getArtistAtIndex_noArtists_null() {
		MatrixCursor cursor = new MatrixCursor(ArtistListModel.PROJECTION);
		when(mockProvider.query(any(), any(), any(), any(), any())).thenReturn(cursor);

		assertThat(mockResolverModel.getArtistAtIndex(0)).isNull();
	}
}
