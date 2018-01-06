package com.edavtyan.materialplayer.lib.lastfm;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;
import com.edavtyan.materialplayer.utils.WebClient;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LastfmApiTests extends BaseTest {
	private static final String ARTIST_TITLE = "Ratatat";
	private static final String ARTIST_IMAGE_LARGE = "https://lastfm-img2.akamaized.net/i/u/174s/96516a40ea0440ea90e3a438a08ae7e1.png";
	private static final String ARTIST_IMAGE_XLARGE = "https://lastfm-img2.akamaized.net/i/u/300x300/96516a40ea0440ea90e3a438a08ae7e1.png";
	private static final String ARTIST_IMAGE_MEGA = "https://lastfm-img2.akamaized.net/i/u/300x300/96516a40ea0440ea90e3a438a08ae7e1.png";

	private WebClient webClient;
	private LastfmApi lastfmApi;
	private LastfmArtistInfoFileStorage fileStorage;

	@Override
	public void beforeEach() {
		super.beforeEach();
		webClient = spy(new WebClient());
		fileStorage = mock(LastfmArtistInfoFileStorage.class);
		String apiKey = context.getString(R.string.lastfm_api_key);
		lastfmApi = new LastfmApi(webClient, fileStorage, apiKey);
	}

	@Test
	public void getInfoFromApi_infoNotSavedLocally_returnInfoWithImageUrls() {
		LastfmArtistInfo info = lastfmApi.getArtistInfo(ARTIST_TITLE);
		assertThat(info.getLargeImageUrl()).isEqualTo(ARTIST_IMAGE_LARGE);
		assertThat(info.getExtraLargeImageUrl()).isEqualTo(ARTIST_IMAGE_XLARGE);
		assertThat(info.getMegaImageUrl()).isEqualTo(ARTIST_IMAGE_MEGA);
	}

	@Test
	public void getInfoFromApi_infoNotSavedLocally_savesInfoToFileSystem() {
		LastfmArtistInfo info = new LastfmArtistInfo();
		info.setLargeImageUrl(ARTIST_IMAGE_LARGE);
		info.setExtraLargeImageUrl(ARTIST_IMAGE_XLARGE);
		info.setMegaImageUrl(ARTIST_IMAGE_MEGA);

		lastfmApi.getArtistInfo(ARTIST_TITLE);
		verify(fileStorage).save(ARTIST_TITLE, info.toJson());
	}

	@Test
	public void getInfoFromApi_infoSavedLocally_getInfoFromFileSystem() {
		LastfmArtistInfo info = new LastfmArtistInfo();
		info.setLargeImageUrl("large");
		info.setExtraLargeImageUrl("xlarge");
		info.setMegaImageUrl("mega");
		when(fileStorage.load(ARTIST_TITLE)).thenReturn(info.toJson());
		when(fileStorage.exists(ARTIST_TITLE)).thenReturn(true);

		assertThat(lastfmApi.getArtistInfo(ARTIST_TITLE)).isEqualTo(info);
	}
}
