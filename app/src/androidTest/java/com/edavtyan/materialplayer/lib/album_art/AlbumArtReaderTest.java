package com.edavtyan.materialplayer.lib.album_art;

import android.media.MediaMetadataRetriever;

import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class AlbumArtReaderTest extends BaseTest {
	@Rule public final TemporaryFolder temporaryFolder = new TemporaryFolder();

	private AlbumArtReader musicTagReader;

	@Override public void beforeEach() {
		super.beforeEach();
		musicTagReader = new AlbumArtReader(new MediaMetadataRetriever());
	}

	@Test public void getAlbumArtBytes_fileWithArt_correctBytes() throws IOException {
		byte[] artBytes = getResourceAsByteArray("art.png");
		File mp3File = copyResourceToFileSystem("with-art.mp3", temporaryFolder.newFile());
		assertThat(musicTagReader.getAlbumArtBytes(mp3File.getAbsolutePath())).isEqualTo(artBytes);
	}

	@Test public void getAlbumArtBytes_fileWithoutArt_null() throws IOException {
		File mp3File = copyResourceToFileSystem("without-art.mp3", temporaryFolder.newFile());
		assertThat(musicTagReader.getAlbumArtBytes(mp3File.getAbsolutePath())).isNull();
	}
}
