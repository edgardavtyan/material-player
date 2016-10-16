package com.edavtyan.materialplayer.utils;

import com.edavtyan.materialplayer.db.Track;
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
	private ArtProvider2 artProvider;
	private Track track;
	private File artFile;

	@Override public void beforeEach() {
		super.beforeEach();

		track = new Track();
		track.setAlbumId(1);

		artFile = mock(File.class);

		dataStorage = mock(DataStorage.class);
		when(dataStorage.getArtFile(1)).thenReturn(artFile);

		tagReader = mock(MusicTagReader.class);
		artProvider = new ArtProvider2(dataStorage, tagReader);
	}

	@Test public void load_artExists_notSaveNewArt() {
		when(artFile.exists()).thenReturn(true);
		assertThat(artProvider.load(track)).isSameAs(artFile);
		verify(dataStorage, never()).saveFile(eq(artFile), any());
	}

	@Test public void load_artNotExists_saveNewArt() {
		byte[] artBytes = new byte[0];
		when(artFile.exists()).thenReturn(false);
		when(tagReader.getAlbumArtBytes()).thenReturn(artBytes);

		assertThat(artProvider.load(track)).isSameAs(artFile);
		verify(dataStorage).saveFile(artFile, artBytes);
	}
}
