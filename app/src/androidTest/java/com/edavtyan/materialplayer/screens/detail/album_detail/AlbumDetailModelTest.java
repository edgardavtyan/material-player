package com.edavtyan.materialplayer.screens.detail.album_detail;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AlbumDetailModelTest extends BaseTest {
	private static final int ALBUM_ID = 7;

	private AlbumDetailModel model;
	private TrackDB trackDB;
	private AlbumArtProvider albumArtProvider;
	private AlbumDB albumDB;

	@Override
	public void beforeEach() {
		super.beforeEach();

		ModelServiceModule serviceModule = mock(ModelServiceModule.class);
		albumDB = mock(AlbumDB.class);
		trackDB = mock(TrackDB.class);
		albumArtProvider = mock(AlbumArtProvider.class);

		model = new AlbumDetailModel(serviceModule, albumDB, trackDB, albumArtProvider, ALBUM_ID);
	}

	@Test
	public void update_callTracksDB() {
		model.update();
		verify(trackDB).getTracksWithAlbumId(ALBUM_ID);
	}

	@Test
	public void getAlbum_returnAlbumFromDB() {
		Album album = new Album();
		when(albumDB.getAlbumWithAlbumId(ALBUM_ID)).thenReturn(album);
		assertThat(model.getAlbum()).isSameAs(album);
	}

	@Test
	public void getTotalAlbumDuration_returnAllTracksDuration() {
		Track track1 = new Track();
		Track track2 = new Track();
		Track track3 = new Track();
		track1.setDuration(300);
		track2.setDuration(400);
		track3.setDuration(500);

		List<Track> tracks = new ArrayList<>();
		tracks.add(track1);
		tracks.add(track2);
		tracks.add(track3);

		when(trackDB.getTracksWithAlbumId(ALBUM_ID)).thenReturn(tracks);

		model.update();

		assertThat(model.getTotalAlbumDuration()).isEqualTo(1200);
	}

	@Test
	public void getAlbumArt_getArtFromArtProvider() {
		Bitmap art = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		when(albumArtProvider.load(any())).thenReturn(art);
		assertThat(model.getAlbumArt()).isEqualTo(art);
	}
}
