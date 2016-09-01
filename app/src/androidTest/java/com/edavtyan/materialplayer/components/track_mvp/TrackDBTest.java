package com.edavtyan.materialplayer.components.track_mvp;

import com.edavtyan.materialplayer.lib.db.TrackDB;
import com.edavtyan.materialplayer.lib.DBTest;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TrackDBTest extends DBTest {
	private static TrackDB trackDB;
	private static TestTrackDBHelper testTrackDB;

	@BeforeClass
	public static void beforeClass() {
		initProvider(TestTrackDBProvider.class);
		trackDB = new TrackDB(context);
		testTrackDB = new TestTrackDBHelper(context);
	}

	@After
	public void afterEach() {
		testTrackDB.reset();
	}

	@Test
	public void getTracksWithAlbumId_correctTracks() {
		testTrackDB.addRandomTracksWhereSomeHaveSameAlbumId(10, 10);

		assertThat(trackDB.getTracksWithAlbumId(10))
				.hasSize(10)
				.isSortedAccordingTo((lhs, rhs) -> lhs.getTrack() - rhs.getTrack());
	}

	@Test
	public void getAllTracks_correctTracks() {
		testTrackDB.addRandomTracks(10);

		assertThat(trackDB.getAllTracks())
				.hasSize(10)
				.isSortedAccordingTo((lhs, rhs) -> lhs.getTitle().compareTo(rhs.getTitle()));
	}
}
