package com.edavtyan.materialplayer.components.album_mvp;

import android.support.test.runner.AndroidJUnit4;

import com.edavtyan.materialplayer.lib.BaseTest;
import com.edavtyan.materialplayer.lib.DBTest;
import com.edavtyan.materialplayer.lib.db.AlbumDB;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(AndroidJUnit4.class)
public class AlbumDBTest extends DBTest {
	private static AlbumDB albumDB;
	private static TestAlbumDBHelper testAlbumDBHelper;

	@BeforeClass
	public static void beforeClass() {
		BaseTest.beforeClass();
		initProvider(TestAlbumDBProvider.class);
		albumDB = new AlbumDB(context);
		testAlbumDBHelper = new TestAlbumDBHelper(context);
	}

	@After
	public void afterEach() {
		testAlbumDBHelper.reset();
	}

	@Test
	public void getAllAlbums_allArtistsOnDevice() {
		testAlbumDBHelper.addRandomAlbums(10);
		assertThat(albumDB.getAllAlbums())
				.hasSize(10)
				.isSortedAccordingTo((lhs, rhs) -> lhs.getTitle().compareTo(rhs.getTitle()));
	}

	@Test
	public void getAlbumsWithArtistTitle_albumsWithSpecifiedTitle() {
		List<Album> albums = testAlbumDBHelper.addRandomAlbumsWhereSomeWithArtistTitle(10, "title", 4);
		assertThat(albumDB.getAlbumsWithArtistTitle("title"))
				.hasSize(4)
				.containsAll(albums);

	}
}
