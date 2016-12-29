package com.edavtyan.materialplayer.utils;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;
import com.edavtyan.materialplayer.utils.tag.MusicTagReader;

import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArtProviderTests extends BaseTest {
	private DataStorage dataStorage;
	private ArtProvider artProvider;
	private TestableBitmapFactory bitmapFactory;
	private Track track;
	private File artFile;
	private Bitmap art;

	@Test public void load_artExists_notSaveNewArt() {
		init(1);
		when(artFile.exists()).thenReturn(true);
		assertThat(artProvider.load(track)).isSameAs(art);
		verify(dataStorage, never()).saveFile(eq(artFile), any());
	}

	@Test public void load_artNotExists_saveNewArt() {
		init(2);
		when(artFile.exists()).thenReturn(false);
		assertThat(artProvider.load(track)).isSameAs(art);
		verify(dataStorage).saveFile(eq(artFile), any());
	}

	@Test public void load_getSameArtManyTimes_loadArtFromCache() {
		init(3);
		artProvider.load(track);
		artProvider.load(track);
		artProvider.load(track);
		artProvider.load(track);
		verify(bitmapFactory, times(1)).fromPath("art_path3");
	}

	private void init(int index) {
		track = new Track();
		track.setAlbumId(index);
		track.setPath("path" + index);

		artFile = mock(File.class);
		when(artFile.getAbsolutePath()).thenReturn("art_path" + index);

		art = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		bitmapFactory = mock(TestableBitmapFactory.class);
		when(bitmapFactory.fromPath("art_path" + index)).thenReturn(art);

		dataStorage = mock(DataStorage.class);
		when(dataStorage.getArtFile(index)).thenReturn(artFile);

		artProvider = new ArtProvider(dataStorage, mock(MusicTagReader.class), bitmapFactory);
	}
}
