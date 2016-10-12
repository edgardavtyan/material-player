package com.edavtyan.materialplayer.db;

import com.edavtyan.materialplayer.db.lib.TrackDBHelper;
import com.edavtyan.materialplayer.db.lib.TrackDBProvider;
import com.edavtyan.materialplayer.lib.DBTest;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TrackDBTest extends DBTest {
	private static TrackDB trackDB;
	private static TrackDBHelper testTrackDB;

	@BeforeClass
	public static void beforeClass() {
		initProvider(TrackDBProvider.class);
		trackDB = new TrackDB(context);
		testTrackDB = new TrackDBHelper(context);
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
