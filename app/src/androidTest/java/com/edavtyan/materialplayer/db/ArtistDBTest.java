package com.edavtyan.materialplayer.db;

import com.edavtyan.materialplayer.db.lib.ArtistDBHelper;
import com.edavtyan.materialplayer.db.lib.ArtistDBProvider;
import com.edavtyan.materialplayer.lib.DBTest;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArtistDBTest extends DBTest {
	private static ArtistDB db;
	private static ArtistDBHelper testArtistDB;

	@BeforeClass
	public static void beforeClass() {
		initProvider(ArtistDBProvider.class);
		db = new ArtistDB(context);
		testArtistDB = new ArtistDBHelper(context);
	}

	@After
	public void afterEach() {
		testArtistDB.reset();
	}

	@Test
	public void getAllArtists_correctArtists() {
		testArtistDB.addRandomArtists(10);

		assertThat(db.getAllArtists())
				.hasSize(10)
				.isSortedAccordingTo((lhs, rhs) -> lhs.getTitle().compareTo(rhs.getTitle()));
	}

	@Test
	public void getArtistWithTitle_artistExists_correctArtist() {
		Artist artist = testArtistDB.addRandomArtist();
		assertThat(db.getArtistWithTitle(artist.getTitle())).isEqualTo(artist);
	}
}
