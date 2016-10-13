package com.edavtyan.materialplayer.db;

import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArtistTest extends BaseTest {
	private Artist first;
	private Artist second;

	@Override public void beforeEach() {
		super.beforeEach();
		first = createArtist();
		second = createArtist();
	}

	@Test public void equals_equalArtists_true() {
		assertThat(first.equals(second)).isTrue();
	}

	@Test public void equals_idNotEqual_false() {
		first.setId(1);
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_titleNotEqual_false() {
		first.setTitle("second");
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_titleIsNull_false() {
		first.setTitle(null);
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_bothTitlesNull_true() {
		first.setTitle(null);
		second.setTitle(null);
		assertThat(first.equals(second)).isTrue();
	}

	@Test public void equals_albumsCountNotEqual_false() {
		first.setAlbumsCount(2);
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_tracksCountNotEqual_false() {
		first.setTracksCount(3);
		assertThat(first.equals(second)).isFalse();
	}

	@SuppressWarnings("EqualsBetweenInconvertibleTypes")
	@Test public void equals_notInstanceOfArtist_false() {
		String string = "";
		assertThat(first.equals(string)).isFalse();
	}

	@Test public void hashCode_equalArtists_equalHashCodes() {
		assertThat(first.hashCode()).isEqualTo(second.hashCode());
	}

	@Test public void hashCode_nullTitle_equalHashCodes() {
		first.setTitle(null);
		second.setTitle(null);
		assertThat(first.hashCode()).isEqualTo(second.hashCode());
	}

	@Test public void toString_returnFormattedString() {
		String artistAsString = "Artist(id=0, title=first, albumsCount=1, tracksCount=2)";
		assertThat(first.toString()).isEqualTo(artistAsString);
	}

	private Artist createArtist() {
		Artist artist = new Artist();
		artist.setId(0);
		artist.setTitle("first");
		artist.setAlbumsCount(1);
		artist.setTracksCount(2);
		return artist;
	}
}
