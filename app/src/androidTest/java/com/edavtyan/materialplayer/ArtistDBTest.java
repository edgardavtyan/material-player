package com.edavtyan.materialplayer;

import com.edavtyan.materialplayer.components.artist_mvp.ArtistDB;
import com.edavtyan.materialplayer.db.DBTest;
import com.edavtyan.materialplayer.db.TestArtistDBHelper;
import com.edavtyan.materialplayer.db.TestArtistDBProvider;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArtistDBTest extends DBTest {
	private static ArtistDB db;
	private static TestArtistDBHelper testArtistDB;

	@BeforeClass
	public static void beforeClass() {
		initProvider(TestArtistDBProvider.class);
		db = new ArtistDB(context);
		testArtistDB = new TestArtistDBHelper(context);
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
}
