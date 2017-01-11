package com.edavtyan.materialplayer.db;

import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AlbumTest extends BaseTest {
	private Album first;
	private Album second;

	@Override
	public void beforeEach() {
		super.beforeEach();
		first = createAlbum();
		second = createAlbum();
	}

	@SuppressWarnings("EqualsWithItself")
	@Test
	public void equals_sameObject_true() {
		assertThat(first.equals(first)).isTrue();
	}

	@Test
	public void equals_allFieldsEqual_true() {
		assertThat(first.equals(second)).isTrue();
	}

	@Test
	public void equals_idNotEqual_false() {
		second.setId(1);
		assertThat(first.equals(second)).isFalse();
	}

	@Test
	public void equals_firstTitleNotEqual_false() {
		first.setTitle("title_not_equal");
		assertThat(first.equals(second)).isFalse();
	}

	@Test
	public void equals_firstTitleNull_false() {
		first.setTitle(null);
		assertThat(first.equals(second)).isFalse();
	}

	@Test
	public void equals_secondTitleNull_false() {
		second.setTitle(null);
		assertThat(first.equals(second)).isFalse();
	}

	@Test
	public void equals_bothTitlesNull_true() {
		first.setTitle(null);
		second.setTitle(null);
		assertThat(first.equals(second)).isTrue();
	}

	@Test
	public void equals_firstArtistTitleNotEqual_false() {
		first.setArtistTitle("title_not_equal");
		assertThat(first.equals(second)).isFalse();
	}

	@Test
	public void equals_firstArtistTitleNull_false() {
		first.setArtistTitle(null);
		assertThat(first.equals(second)).isFalse();
	}

	@Test
	public void equals_secondArtistTitleNull_false() {
		second.setArtistTitle(null);
		assertThat(first.equals(second)).isFalse();
	}

	@Test
	public void equals_bothArtistTitlesNull_true() {
		first.setArtistTitle(null);
		second.setArtistTitle(null);
		assertThat(first.equals(second)).isTrue();
	}

	@Test
	public void equals_firstTracksCountNotEqual_false() {
		first.setTracksCount(0);
		assertThat(first.equals(second)).isFalse();
	}

	@Test
	public void equals_firstArtNotEqual_false() {
		first.setArt("title_not_equal");
		assertThat(first.equals(second)).isFalse();
	}

	@Test
	public void equals_firstArtNull_false() {
		first.setArt(null);
		assertThat(first.equals(second)).isFalse();
	}

	@Test
	public void equals_secondArtNull_false() {
		second.setArt(null);
		assertThat(first.equals(second)).isFalse();
	}

	@Test
	public void equals_bothArtsNull_true() {
		first.setArt(null);
		second.setArt(null);
		assertThat(first.equals(second)).isTrue();
	}

	@SuppressWarnings("ObjectEqualsNull")
	@Test
	public void equals_secondArtistNull_false() {
		assertThat(first.equals(null)).isFalse();
	}

	@SuppressWarnings("EqualsBetweenInconvertibleTypes")
	@Test
	public void equals_secondNotInstanceOfAlbum_false() {
		assertThat(first.equals("")).isFalse();
	}

	@Test
	public void hashCode_allFieldsEqual_equalHashCodes() {
		assertThat(first.hashCode()).isEqualTo(second.hashCode());
	}

	@Test
	public void hashCode_nullTitle_equalHashCodes() {
		first.setTitle(null);
		second.setTitle(null);
		assertThat(first.hashCode()).isEqualTo(second.hashCode());
	}

	@Test
	public void hashCode_nullArtistTitle_equalHashCodes() {
		first.setArtistTitle(null);
		second.setArtistTitle(null);
		assertThat(first.hashCode()).isEqualTo(second.hashCode());
	}

	@Test
	public void hashCode_nullArt_equalHashCodes() {
		first.setArt(null);
		second.setArt(null);
		assertThat(first.hashCode()).isEqualTo(second.hashCode());
	}

	@Test
	public void toString_formattedString() {
		String trackAsString =
				"Album(id=0, title=title, artistTitle=artistTitle, tracksCount=1, art=art)";
		assertThat(first.toString()).isEqualTo(trackAsString);
	}

	private Album createAlbum() {
		Album album = new Album();
		album.setId(0);
		album.setTitle("title");
		album.setArtistTitle("artistTitle");
		album.setTracksCount(1);
		album.setArt("art");
		return album;
	}
}
