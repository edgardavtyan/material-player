package com.edavtyan.materialplayer.player;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;
import com.google.gson.Gson;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerQueueStorageTest extends BaseTest {
	@Rule public final TemporaryFolder tempFolder = new TemporaryFolder();
	private PlayerQueueStorage storage;

	@Override
	public void beforeEach() {
		super.beforeEach();
		storage = new PlayerQueueStorage(context, new Gson());
	}

	@Test
	public void save_and_load() {
		Track track1 = createRandomTrack();
		Track track2 = createRandomTrack();
		Track track3 = createRandomTrack();
		List<Track> tracks = new ArrayList<>();
		tracks.add(track1);
		tracks.add(track2);
		tracks.add(track3);

		storage.save(tracks);

		List<Track> loadedTracks = storage.load();
		assertThat(loadedTracks).isEqualTo(tracks).hasSize(3);
		assertThat(loadedTracks.get(0).getAlbumId()).isEqualTo(track1.getAlbumId());
		assertThat(loadedTracks.get(1).getAlbumId()).isEqualTo(track2.getAlbumId());
		assertThat(loadedTracks.get(2).getAlbumId()).isEqualTo(track3.getAlbumId());
	}

	private Track createRandomTrack() {
		Random random = new Random();
		Track track = new Track();
		track.setAlbumId(random.nextInt());
		return track;
	}
}
