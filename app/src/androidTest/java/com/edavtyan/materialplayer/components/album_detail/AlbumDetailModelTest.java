package com.edavtyan.materialplayer.components.album_detail;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.mvp.list.CompactListPref;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;
import com.edavtyan.materialplayer.utils.ArtProvider;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AlbumDetailModelTest extends BaseTest {
	private static final int ALBUM_ID = 7;

	private AlbumDetailModel model;
	private TrackDB trackDB;
	private ArtProvider artProvider;

	@Override public void beforeEach() {
		super.beforeEach();

		AlbumDetailActivity view = mock(AlbumDetailActivity.class);
		AlbumDB albumDB = mock(AlbumDB.class);
		CompactListPref prefs = mock(CompactListPref.class);
		trackDB = mock(TrackDB.class);
		artProvider = mock(ArtProvider.class);

		model = new AlbumDetailModel(view, albumDB, trackDB, artProvider, prefs, ALBUM_ID);
	}

	@Test public void update_callTracksDB() {
		model.update();
		verify(trackDB).getTracksWithAlbumId(ALBUM_ID);
	}

	@Test public void getAlbumArt_getArtFromArtProvider() {
		Bitmap art = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		when(artProvider.load(any())).thenReturn(art);
		assertThat(model.getAlbumArt()).isEqualTo(art);
	}
}
