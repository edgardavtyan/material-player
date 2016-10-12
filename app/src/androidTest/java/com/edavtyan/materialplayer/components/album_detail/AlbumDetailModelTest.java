package com.edavtyan.materialplayer.components.album_detail;

import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.BaseTest;
import com.edavtyan.materialplayer.utils.ArtProvider2;

import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AlbumDetailModelTest extends BaseTest {
	private static final int ALBUM_ID = 7;

	private AlbumDetailModel model;
	private TrackDB trackDB;
	private ArtProvider2 artProvider;

	@Override
	public void beforeEach() {
		super.beforeEach();

		AlbumDetailActivity view = mock(AlbumDetailActivity.class);
		AlbumDB albumDB = mock(AlbumDB.class);
		trackDB = mock(TrackDB.class);
		artProvider = mock(ArtProvider2.class);

		model = new AlbumDetailModel(view, albumDB, trackDB, artProvider, ALBUM_ID);
	}

	@Test
	public void update_callTracksDB() {
		model.update();
		verify(trackDB).getTracksWithAlbumId(ALBUM_ID);
	}

	@Test public void getAlbumArt_getArtFromArtProvider() {
		File artFile = mock(File.class);
		when(artProvider.load(any())).thenReturn(artFile);
		assertThat(model.getAlbumArt()).isSameAs(artFile);
	}
}
