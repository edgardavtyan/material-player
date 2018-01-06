package com.edavtyan.materialplayer.components.detail.artist_detail;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.lib.lastfm.LastfmApi;
import com.edavtyan.materialplayer.lib.lastfm.LastfmArtistInfo;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;
import com.edavtyan.materialplayer.utils.WebClient;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArtistDetailImageLoaderTest extends BaseTest {
	private WebClient webClient;
	private LastfmApi lastfmApi;
	private TestableBitmapFactory bitmapFactory;
	private ArtistDetailImageFileStorage fileStorage;
	private ArtistDetailImageMemoryCache memoryCache;
	private ArtistDetailImageLoader imageLoader;

	@Override
	public void beforeEach() {
		super.beforeEach();
		webClient = mock(WebClient.class);
		lastfmApi = mock(LastfmApi.class);
		bitmapFactory = mock(TestableBitmapFactory.class);
		fileStorage = mock(ArtistDetailImageFileStorage.class);
		memoryCache = mock(ArtistDetailImageMemoryCache.class);
		imageLoader = new ArtistDetailImageLoader(
				webClient, lastfmApi, bitmapFactory, fileStorage, memoryCache);
	}

	@Test
	public void getImageFromApi_returnImageFromApiAndSaveItToFileAndMemoryCache() {
		LastfmArtistInfo artistInfo = new LastfmArtistInfo();
		artistInfo.setMegaImageUrl("url100");
		when(lastfmApi.getArtistInfo("artist100")).thenReturn(artistInfo);

		byte[] imageBytes = new byte[1];
		when(webClient.getBytes("url100")).thenReturn(imageBytes);

		Bitmap image = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		when(bitmapFactory.fromByteArray(imageBytes)).thenReturn(image);

		assertThat(imageLoader.getImageFromApi("artist100")).isSameAs(image);
		verify(fileStorage).saveBytes("artist100", imageBytes);
		verify(memoryCache).put("artist100", image);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void getImageFromApi_somethingThrowsException_returnNull() {
		when(lastfmApi.getArtistInfo("artist200")).thenThrow(RuntimeException.class);
		assertThat(imageLoader.getImageFromApi("artist200")).isNull();
	}

	@Test
	public void getImageFromCache_imageSavedInMemoryCache_getImageFromMemoryCacheAndNotSaveItInCache() {
		Bitmap image = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		when(memoryCache.exists("artist300")).thenReturn(true);
		when(memoryCache.get("artist300")).thenReturn(image);
		assertThat(imageLoader.getImageFromCache("artist300")).isSameAs(image);
		verify(fileStorage, never()).saveBytes(eq("artist300"), isA(byte[].class));
		verify(memoryCache, never()).put("artist300", image);
	}

	@Test
	public void getImageFromCache_imageSavedInFileSystem_getImageFromFileSystemAndSaveItInMemoryCache() {
		Bitmap image = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		when(memoryCache.exists("artist400")).thenReturn(false);
		when(fileStorage.exists("artist400")).thenReturn(true);
		when(fileStorage.load("artist400")).thenReturn(image);
		assertThat(imageLoader.getImageFromCache("artist400")).isSameAs(image);
		verify(fileStorage, never()).saveBytes(eq("artist400"), isA(byte[].class));
		verify(memoryCache).put("artist400", image);
	}

	@Test
	public void getImageFromCache_imageNotSavedInNeitherMemoryNorFileSystemCache_returnNull() {
		when(fileStorage.exists("artist500")).thenReturn(false);
		when(memoryCache.exists("artist500")).thenReturn(false);
		assertThat(imageLoader.getImageFromCache("artist500")).isNull();
	}
}
