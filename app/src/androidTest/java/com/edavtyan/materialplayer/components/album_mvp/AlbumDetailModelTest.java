package com.edavtyan.materialplayer.components.album_mvp;

import com.edavtyan.materialplayer.lib.BaseTest;
import com.edavtyan.materialplayer.lib.db.AlbumDB;
import com.edavtyan.materialplayer.lib.db.ArtistDB;
import com.edavtyan.materialplayer.lib.db.TrackDB;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AlbumDetailModelTest extends BaseTest {
	private static final int ALBUM_ID = 7;

	private AlbumDetailModel model;
	private TrackDB trackDB;

	@Override
	public void beforeEach() {
		super.beforeEach();

		AlbumDetailActivity view = mock(AlbumDetailActivity.class);
		ArtistDB artistDB = mock(ArtistDB.class);
		AlbumDB albumDB = mock(AlbumDB.class);
		trackDB = mock(TrackDB.class);

		model = new AlbumDetailModel(view, artistDB, albumDB, trackDB, ALBUM_ID);
	}

	@Test
	public void update_callTracksDB() {
		model.update();
		verify(trackDB).getTracksWithAlbumId(ALBUM_ID);
	}
}
