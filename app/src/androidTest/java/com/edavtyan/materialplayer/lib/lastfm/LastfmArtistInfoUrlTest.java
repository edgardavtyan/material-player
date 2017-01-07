package com.edavtyan.materialplayer.lib.lastfm;

import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LastfmArtistInfoUrlTest extends BaseTest {
	@Test
	public void build_returnCorrectUrl() {
		String url =
				"http://ws.audioscrobbler.com/2.0/" +
				"?method=artist.getinfo" +
				"&artist=title" +
				"&api_key=123" +
				"&format=json";
		assertThat(LastfmArtistInfoUrl.build("123", "title")).isEqualTo(url);
	}
}
