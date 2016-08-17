package com.edavtyan.materialplayer;

import com.edavtyan.materialplayer.components.album_mvp.AlbumDB;
import com.edavtyan.materialplayer.components.album_mvp.TrackDB;
import com.edavtyan.materialplayer.lib.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TrackDBTest extends BaseTest {
	private TrackDB trackDB;

	@Override
	public void beforeEach() {
		super.beforeEach();
		trackDB = new TrackDB(context);
	}

	@Test
	public void getTracksWithAlbumId_correctTracks() {
		AlbumDB albumDB = new AlbumDB(context);
		int id = albumDB.getAllAlbums().get(0).getId();

		assertThat(trackDB.getTracksWithAlbumId(id))
				.hasSize(4)
				.isSortedAccordingTo((lhs, rhs) -> lhs.getTrack() - rhs.getTrack());
	}

	@Test
	public void getAllTracks_correctTracks() {
		assertThat(trackDB.getAllTracks())
				.hasSize(9)
				.isSortedAccordingTo((lhs, rhs) -> lhs.getTitle().compareTo(rhs.getTitle()));
	}
}
