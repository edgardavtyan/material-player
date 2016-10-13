package com.edavtyan.materialplayer.db;

import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TrackTest extends BaseTest {
	private Track first;
	private Track second;

	@Override public void beforeEach() {
		super.beforeEach();
		first = createTrack();
		second = createTrack();
	}

	@Test public void equals_allFieldsEqual_true() {
		assertThat(first.equals(second)).isTrue();
	}

	@Test public void equals_idNotEqual_false() {
		second.setId(1);
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_trackNotEqual_false() {
		second.setTrack(2);
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_titleNotEqual_false() {
		second.setTitle("second");
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_firstTitleNull_false() {
		first.setTitle(null);
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_secondTitleNull_false() {
		second.setTitle(null);
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_bothTitlesNull_true() {
		first.setTitle(null);
		second.setTitle(null);
		assertThat(first.equals(second)).isTrue();
	}

	@Test public void equals_pathNotEqual_false() {
		first.setPath("path_first");
		second.setPath("path_second");
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_firstPathNull_false() {
		first.setPath(null);
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_secondPathNull_false() {
		second.setPath(null);
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_bothPathsNull_true() {
		first.setPath(null);
		second.setPath(null);
		assertThat(first.equals(second)).isTrue();
	}

	@Test public void equals_dateNotEqual_false() {
		first.setDateModified(1L);
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_durationNotEqual_false() {
		first.setDuration(1L);
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_artistIdNotEqual_false() {
		first.setArtistId(1);
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_artistTitleNotEqual_false() {
		first.setArtistTitle("second");
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_firstArtistTitleNull_false() {
		first.setArtistTitle(null);
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_secondArtistTitleNull_false() {
		second.setArtistTitle(null);
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_bothArtistTitlesNull_true() {
		first.setArtistTitle(null);
		second.setArtistTitle(null);
		assertThat(first.equals(second)).isTrue();
	}

	@Test public void equals_albumIdNotEqual_false() {
		first.setAlbumId(1);
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_albumTitleNotEqual_false() {
		first.setAlbumTitle("second");
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_firstAlbumTitleNull_false() {
		first.setAlbumTitle(null);
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_secondAlbumTitleNull_false() {
		second.setAlbumTitle(null);
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_bothAlbumTitlesNull_true() {
		first.setAlbumTitle(null);
		second.setAlbumTitle(null);
		assertThat(first.equals(second)).isTrue();
	}

	@Test public void equals_queueIndexNotEqual_false() {
		first.setQueueIndex(1);
		assertThat(first.equals(second)).isFalse();
	}

	@SuppressWarnings("ObjectEqualsNull")
	@Test public void equals_firstArtistNull_false() {
		assertThat(second.equals(null)).isFalse();
	}

	@Test public void toString_formattedString() {
		String trackAsString =
				"Track("
				+ "id=0, "
				+ "track=1, "
				+ "title=title, "
				+ "path=path, "
				+ "dateModified=2, "
				+ "duration=3, "
				+ "artistId=4, "
				+ "artistTitle=artist, "
				+ "albumId=5, "
				+ "albumTitle=album, "
				+ "queueIndex=6"
				+ ")";
		assertThat(first.toString()).isEqualTo(trackAsString);
	}

	@Test public void hashCode_equalTracks_equalHashCodes() {
		assertThat(first.hashCode()).isEqualTo(second.hashCode());
	}

	@Test public void hashCode_nullTitle_equalHashCodes() {
		first.setTitle(null);
		second.setTitle(null);
		assertThat(first.hashCode()).isEqualTo(second.hashCode());
	}

	@Test public void hashCode_nullPath_equalHashCodes() {
		first.setPath(null);
		second.setPath(null);
		assertThat(first.hashCode()).isEqualTo(second.hashCode());
	}

	@Test public void hashCode_nullArtistTitle_equalHashCodes() {
		first.setArtistTitle(null);
		second.setArtistTitle(null);
		assertThat(first.hashCode()).isEqualTo(second.hashCode());
	}

	@Test public void hashCode_nullAlbumTitle_equalHashCode() {
		first.setAlbumTitle(null);
		second.setAlbumTitle(null);
		assertThat(first.hashCode()).isEqualTo(second.hashCode());
	}

	private Track createTrack() {
		Track first = new Track();
		first.setId(0);
		first.setTrack(1);
		first.setTitle("title");
		first.setPath("path");
		first.setDateModified(2L);
		first.setDuration(3L);
		first.setArtistId(4);
		first.setArtistTitle("artist");
		first.setAlbumId(5);
		first.setAlbumTitle("album");
		first.setQueueIndex(6);
		return first;
	}
}
