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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArtProviderTests extends BaseTest {
	private DataStorage dataStorage;
	private MusicTagReader tagReader;
	private ArtProvider artProvider;
	private TestableBitmapFactory bitmapFactory;
	private Track track;
	private File artFile;
	private Bitmap art;

	@Override public void beforeEach() {
		super.beforeEach();

		track = new Track();
		track.setAlbumId(1);
		track.setPath("path");

		artFile = mock(File.class);
		when(artFile.getAbsolutePath()).thenReturn("art_path");

		art = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		bitmapFactory = mock(TestableBitmapFactory.class);
		when(bitmapFactory.fromPath("art_path")).thenReturn(art);

		dataStorage = mock(DataStorage.class);
		when(dataStorage.getArtFile(1)).thenReturn(artFile);

		tagReader = mock(MusicTagReader.class);

		artProvider = new ArtProvider(dataStorage, tagReader, bitmapFactory);
	}

	@Test public void load_artExists_notSaveNewArt() {
		when(artFile.exists()).thenReturn(true);
		assertThat(artProvider.load(track)).isSameAs(art);
		verify(dataStorage, never()).saveFile(eq(artFile), any());
	}

	@Test public void load_artNotExists_saveNewArt() {
		when(artFile.exists()).thenReturn(false);
		assertThat(artProvider.load(track)).isSameAs(art);
		verify(dataStorage).saveFile(eq(artFile), any());
	}
}
