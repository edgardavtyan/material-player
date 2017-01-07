package com.edavtyan.materialplayer.lib.lastfm;

import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LastfmArtistInfoTests extends BaseTest {
	@Test
	public void toJson_returnCorrectJson() {
		String json = "{\"large\":\"0\",\"xlarge\":\"1\",\"mega\":\"2\"}";
		LastfmArtistInfo info = new LastfmArtistInfo();
		info.setLargeImageUrl("0");
		info.setExtraLargeImageUrl("1");
		info.setMegaImageUrl("2");
		assertThat(info.toJson()).isEqualTo(json);
	}

	@Test
	public void fromJson_formCorrectInfo() {
		String json = "{\"large\":\"0\",\"xlarge\":\"1\",\"mega\":\"2\"}";
		LastfmArtistInfo info = LastfmArtistInfo.fromJson(json);
		assertThat(info.getLargeImageUrl()).isEqualTo("0");
		assertThat(info.getExtraLargeImageUrl()).isEqualTo("1");
		assertThat(info.getMegaImageUrl()).isEqualTo("2");
	}
}
