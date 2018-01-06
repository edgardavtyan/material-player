package com.edavtyan.materialplayer.lib.album_art;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AlbumArtProviderTests extends BaseTest {
	private AlbumArtFileStorage fileStorage;
	private AlbumArtMemoryCache memoryCache;
	private AlbumArtProvider albumArtProvider;
	private AlbumArtReader artReader;
	private Track track;
	private Bitmap art;
	private byte[] artBytes;

	@Test
	public void load_artExists_notSaveNewArt() {
		init(1);
		when(fileStorage.exists("1")).thenReturn(true);
		assertThat(albumArtProvider.load(track)).isSameAs(art);
		verify(fileStorage, never()).saveBytes("1", artBytes);
	}

	@Test
	public void load_artNotExists_saveNewArt() {
		init(2);
		when(fileStorage.exists("2")).thenReturn(false);
		assertThat(albumArtProvider.load(track)).isSameAs(art);
		verify(fileStorage).saveBytes(2, artBytes);
	}

	@Test
	public void return_art_from_memory_cache() {
		init(4);
		when(memoryCache.contains(4)).thenReturn(true);
		when(memoryCache.get(4)).thenReturn(art);
		assertThat(albumArtProvider.load(track)).isSameAs(art);
	}

	private void init(int index) {
		track = new Track();
		track.setAlbumId(index);
		track.setPath("path" + index);

		art = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);

		fileStorage = mock(AlbumArtFileStorage.class);
		memoryCache = mock(AlbumArtMemoryCache.class);
		when(fileStorage.load(Integer.toString(index))).thenReturn(art);

		artBytes = new byte[]{1, 2, 3, 4, 5};
		artReader = mock(AlbumArtReader.class);
		when(artReader.getAlbumArtBytes(track.getPath())).thenReturn(artBytes);

		TestableBitmapFactory bitmapFactory = mock(TestableBitmapFactory.class);
		when(bitmapFactory.fromByteArray(artBytes)).thenReturn(art);

		albumArtProvider = new AlbumArtProvider(fileStorage, memoryCache, artReader, bitmapFactory);
	}
}
