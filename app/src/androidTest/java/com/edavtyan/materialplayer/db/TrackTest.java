package com.edavtyan.materialplayer.db;

import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TrackTest extends BaseTest {
	private Track first;
	private Track second;

	@Override public void beforeEach() {
		super.beforeEach();
		first = new Track();
		second = new Track();
	}

	@Test public void equals_equalTracks_true() {
		assertThat(createFirstTrack().equals(createSecondTrack())).isTrue();
	}

	@Test public void equals_idNotEqual_false() {
		first.setId(0);
		second.setId(1);
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_trackNotEqual_false() {
		first.setTrack(0);
		second.setTrack(1);
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_titleNotEqual_false() {
		first.setTitle("first");
		second.setTitle("second");
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_titleIsNull_false() {
		second.setTitle("first");
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_pathNotEqual_false() {
		first.setPath("path_first");
		second.setPath("path_second");
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_pathIsNull_false() {
		second.setPath("path_first");
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_dateNotEqual_false() {
		first.setDateModified(0L);
		first.setDateModified(1L);
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_durationNotEqual_false() {
		first.setDuration(0L);
		first.setDuration(1L);
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_artistIdNotEqual_false() {
		first.setArtistId(0);
		first.setArtistId(1);
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_artistTitleNotEqual_false() {
		first.setArtistTitle("first");
		first.setArtistTitle("second");
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_artistTitleIsNull_false() {
		second.setArtistTitle("first");
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_albumIdNotEqual_false() {
		first.setAlbumId(0);
		first.setAlbumId(1);
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_albumTitleNotEqual_false() {
		first.setAlbumTitle("first");
		first.setAlbumTitle("second");
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_albumTitleIsNull_false() {
		second.setAlbumTitle("first");
		assertThat(first.equals(second)).isFalse();
	}

	@Test public void equals_queueIndexNotEqual_false() {
		first.setQueueIndex(0);
		first.setQueueIndex(1);
		assertThat(first.equals(second)).isFalse();
	}

	@SuppressWarnings("ObjectEqualsNull")
	@Test public void equals_null_false() {
		assertThat(second.equals(null)).isFalse();
	}

	@Test public void toString_returnFormattedString() {
		first = createFirstTrack();
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

	@Test public void hashCode_equalTracks_equalHashCode() {
		assertThat(first.hashCode()).isEqualTo(second.hashCode());
	}

	@Test public void hashCode_nullTitle_equalHashCode() {
		first = createFirstTrack();
		first.setTitle(null);
		second = createSecondTrack();
		second.setTitle(null);
		assertThat(first.hashCode()).isEqualTo(second.hashCode());
	}

	@Test public void hashCode_nullPath_equalHashCode() {
		first = createFirstTrack();
		first.setPath(null);
		second = createSecondTrack();
		second.setPath(null);
		assertThat(first.hashCode()).isEqualTo(second.hashCode());
	}

	@Test public void hashCode_nullArtistTitle_equalHashCode() {
		first = createFirstTrack();
		first.setArtistTitle(null);
		second = createSecondTrack();
		second.setArtistTitle(null);
		assertThat(first.hashCode()).isEqualTo(second.hashCode());
	}

	@Test public void hashCode_nullAlbumTitle_equalHashCode() {
		first = createFirstTrack();
		first.setAlbumTitle(null);
		second = createSecondTrack();
		second.setAlbumTitle(null);
		assertThat(first.hashCode()).isEqualTo(second.hashCode());
	}

	private Track createFirstTrack() {
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

	private Track createSecondTrack() {
		Track second = new Track();
		second.setId(0);
		second.setTrack(1);
		second.setTitle("title");
		second.setPath("path");
		second.setDateModified(2L);
		second.setDuration(3L);
		second.setArtistId(4);
		second.setArtistTitle("artist");
		second.setAlbumId(5);
		second.setAlbumTitle("album");
		second.setQueueIndex(6);
		return second;
	}
}
