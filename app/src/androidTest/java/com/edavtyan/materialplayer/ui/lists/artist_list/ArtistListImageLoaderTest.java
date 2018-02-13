package com.edavtyan.materialplayer.ui.lists.artist_list;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.lib.lastfm.LastfmApi;
import com.edavtyan.materialplayer.lib.lastfm.LastfmArtistInfo;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;
import com.edavtyan.materialplayer.utils.WebClient;

import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArtistListImageLoaderTest extends BaseTest {
	ArtistListImageLoader imageLoader;
	private LastfmApi lastfmApi;
	private TestableBitmapFactory bitmapFactory;
	private ArtistListImageMemoryCache memoryCache;
	private ArtistListImageFileStorage fileStorage;
	private WebClient webClient;

	@Override
	public void beforeEach() {
		super.beforeEach();
		webClient = mock(WebClient.class);
		bitmapFactory = mock(TestableBitmapFactory.class);
		lastfmApi = mock(LastfmApi.class);
		fileStorage = mock(ArtistListImageFileStorage.class);
		memoryCache = mock(ArtistListImageMemoryCache.class);
		imageLoader = new ArtistListImageLoader(webClient, bitmapFactory, lastfmApi, fileStorage, memoryCache);
	}

	@Test
	public void getImageFromFileSystemOrApi_imageSavedInFileAndMemoryCache_returnImageFromFileAndNotPutInMemoryCache() {
		Bitmap art = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		when(fileStorage.exists("file100")).thenReturn(true);
		when(fileStorage.load("file100")).thenReturn(art);
		when(memoryCache.exists("file100")).thenReturn(true);

		assertThat(imageLoader.getImageFromFileSystemOrApi("file100")).isSameAs(art);
		verify(memoryCache, never()).put("file100", art);
	}

	@Test
	public void getImageFromFileSystemOrApi_imageSavedInFileAndNotInMemoryCache_returnImageFromFileAndPutInMemoryCache() {
		Bitmap art = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		when(fileStorage.exists("file200")).thenReturn(true);
		when(fileStorage.load("file200")).thenReturn(art);
		when(memoryCache.exists("file200")).thenReturn(false);

		assertThat(imageLoader.getImageFromFileSystemOrApi("file200")).isSameAs(art);
		verify(memoryCache).put("file200", art);
	}

	@Test
	public void getImageFromFileSystemOrApi_imageNotSavedInAnyCache_returnImageFromApiAndSaveInFileAndMemoryCache() {
		when(fileStorage.exists("file300")).thenReturn(false);
		when(memoryCache.exists("file300")).thenReturn(false);

		LastfmArtistInfo artistInfo = new LastfmArtistInfo();
		artistInfo.setLargeImageUrl("url300");
		when(lastfmApi.getArtistInfo("file300")).thenReturn(artistInfo);

		byte[] artistImageBytes = new byte[1];
		when(webClient.getBytes("url300")).thenReturn(artistImageBytes);

		Bitmap image = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		when(bitmapFactory.fromByteArray(artistImageBytes)).thenReturn(image);

		assertThat(imageLoader.getImageFromFileSystemOrApi("file300")).isSameAs(image);
		verify(fileStorage).saveBytes("file300", artistImageBytes);
		verify(memoryCache).put("file300", image);
	}

	@Test
	@Ignore
	public void getImageFromFileSystemOrApi_somethingThrowsException_returnNull() {
		when(fileStorage.exists("file400")).thenThrow(RuntimeException.class);
		assertThat(imageLoader.getImageFromFileSystemOrApi("file400")).isNull();
	}

	@Test
	public void getImageFromMemoryCache_imageSavedInCache_returnImageFromCache() {
		Bitmap art = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		when(memoryCache.get("file500")).thenReturn(art);
		when(memoryCache.exists("file500")).thenReturn(true);
		assertThat(imageLoader.getImageFromMemoryCache("file500")).isSameAs(art);
	}

	@Test
	public void getImageFromMemoryCache_imageNotSavedInCache_returnNull() {
		when(memoryCache.exists("file600")).thenReturn(false);
		assertThat(imageLoader.getImageFromMemoryCache("file600")).isNull();
	}
}
