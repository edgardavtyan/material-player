package com.edavtyan.materialplayer.utils;

import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class DataStorageTest extends BaseTest {
	private DataStorage dataStorage;

	@Rule public TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Override public void beforeEach() {
		super.beforeEach();
		dataStorage = new DataStorage();
	}

	@SuppressWarnings("ResultOfMethodCallIgnored")
	@Test public void saveFile_writeDataToFile() throws IOException {
		File file = temporaryFolder.newFile();
		byte[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 22, 33, 44, 55, 66, 77, 88, 99};

		dataStorage.saveFile(file, data);

		byte[] newBuffer = new byte[data.length];
		FileInputStream inputStream = new FileInputStream(file);
		inputStream.read(newBuffer, 0, newBuffer.length);
		inputStream.close();

		assertThat(newBuffer).isEqualTo(data);
	}

	@Test public void getArtFile_returnCorrectPath() {
		assertThat(dataStorage.getArtFile(3).getAbsolutePath()).isEqualTo(DataStorage.DIR_ART + "/3");
	}
}
