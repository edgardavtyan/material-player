package com.edavtyan.materialplayer.db;

import com.edavtyan.materialplayer.db.lib.ArtistDBHelper;
import com.edavtyan.materialplayer.db.lib.ArtistDBProvider;
import com.edavtyan.materialplayer.testlib.tests.DBTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArtistDBTest extends DBTest {
	private ArtistDB db;
	private ArtistDBHelper testArtistDB;

	@Override public void beforeEach() {
		initProvider(new ArtistDBProvider());
		db = new ArtistDB(context);
		testArtistDB = new ArtistDBHelper(context);
	}

	@Override public void afterEach() {
		testArtistDB.reset();
	}

	@Test public void getAllArtists_correctArtists() {
		testArtistDB.addRandomArtists(10);

		assertThat(db.getAllArtists())
				.hasSize(10)
				.isSortedAccordingTo((lhs, rhs) -> lhs.getTitle().compareTo(rhs.getTitle()));
	}

	@Test public void getArtistWithTitle_artistExists_correctArtist() {
		Artist artist = testArtistDB.addRandomArtist();
		assertThat(db.getArtistWithTitle(artist.getTitle())).isEqualTo(artist);
	}
}
