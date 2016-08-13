package com.edavtyan.materialplayer;

import com.edavtyan.materialplayer.components.album_mvp.Album;
import com.edavtyan.materialplayer.components.album_mvp.AlbumDB;
import com.edavtyan.materialplayer.lib.BaseTest;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AlbumDBTest extends BaseTest {
	private AlbumDB albumDB;

	@Override
	public void beforeEach() {
		super.beforeEach();
		albumDB = new AlbumDB(context);
	}

	@Test
	public void getAllAlbums_allArtistsOnDevice() {
		List<Album> albums = albumDB.getAllAlbums();
		assertThat(albums.size()).isEqualTo(5);
	}
}
