package com.edavtyan.materialplayer.utils.tag;

import android.media.MediaMetadataRetriever;

import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class VanillaMusicTagReaderTest extends BaseTest {
	@Rule public TemporaryFolder temporaryFolder = new TemporaryFolder();

	private VanillaMusicTagReader musicTagReader;

	@Override public void beforeEach() {
		super.beforeEach();
		musicTagReader = new VanillaMusicTagReader(new MediaMetadataRetriever());
	}

	@Test public void getAlbumArtBytes_fileWithArt_correctBytes() throws IOException {
		byte[] artBytes = getResourceAsByteArray("art.png");
		File mp3File = copyResourceToFileSystem("with-art.mp3", temporaryFolder.newFile());
		musicTagReader.setDataSource(mp3File.getAbsolutePath());
		assertThat(musicTagReader.getAlbumArtBytes()).isEqualTo(artBytes);
	}

	@Test public void getAlbumArtBytes_fileWithoutArt_null() throws IOException {
		File mp3File = copyResourceToFileSystem("without-art.mp3", temporaryFolder.newFile());
		musicTagReader.setDataSource(mp3File.getAbsolutePath());
		assertThat(musicTagReader.getAlbumArtBytes()).isNull();
	}
}
