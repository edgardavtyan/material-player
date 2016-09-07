package com.edavtyan.materialplayer.components.track_mvp;

import com.edavtyan.materialplayer.components.tracks.Track;
import com.edavtyan.materialplayer.lib.DBTest;
import com.edavtyan.materialplayer.lib.db.TrackDB;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

	@Test
	public void getFirstAlbumTrack_correctTrack() {
		Comparator<Track> comparator = (lhs, rhs) -> lhs.getTrack() - rhs.getTrack();
		List<Track> tracks = testTrackDB.addRandomTracksWhereSomeHaveSameAlbumId(5, 5);
		Collections.sort(tracks, comparator);

		Track track = tracks.get(0);

		assertThat(trackDB.getFirstTrackWithAlbumId(5)).isEqualTo(track);
	}
}
