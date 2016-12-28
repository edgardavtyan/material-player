package com.edavtyan.materialplayer.components;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.components.album_detail.AlbumDetailActivity;
import com.edavtyan.materialplayer.components.artist_detail.ArtistDetailActivity;
import com.edavtyan.materialplayer.components.audioeffects.AudioEffectsActivity;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.components.now_playing_queue.NowPlayingQueueActivity;
import com.edavtyan.materialplayer.components.prefs.PrefActivity;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static com.edavtyan.materialplayer.testlib.asertions.IntentAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NavigatorTest extends BaseTest {
	private AppCompatActivity activity;
	private Navigator navigator;
	private ArgumentCaptor<Intent> intentCaptor;

	@Override public void beforeEach() {
		super.beforeEach();
		activity = mock(AppCompatActivity.class);
		navigator = new Navigator(activity);
		intentCaptor = ArgumentCaptor.forClass(Intent.class);
	}

	@Test public void gotoArtistDetail_startActivityWithCorrectParameters() {
		navigator.gotoArtistDetail("title");

		verify(activity).startActivity(intentCaptor.capture());

		assertThat(intentCaptor.getValue())
				.classEqualTo(ArtistDetailActivity.class)
				.hasExtra(ArtistDetailActivity.EXTRA_ARTIST_TITLE, "title");
	}

	@Test public void gotoAlbumDetail_startAlbumDetailActivityWithCorrectParameters() {
		navigator.gotoAlbumDetail(7);

		verify(activity).startActivity(intentCaptor.capture());

		assertThat(intentCaptor.getValue())
				.classEqualTo(AlbumDetailActivity.class)
				.hasExtra(AlbumDetailActivity.EXTRA_ALBUM_ID, "7");
	}

	@Test public void gotoNowPlaying_startNowPlayingActivityWithCorrectParameters() {
		navigator.gotoNowPlaying();

		verify(activity).startActivity(intentCaptor.capture());

		assertThat(intentCaptor.getValue())
				.classEqualTo(NowPlayingActivity.class);
	}

	@Test public void gotoNowPlayingQueue_startPlaylistActivity() {
		navigator.gotoNowPlayingQueue();

		verify(activity).startActivity(intentCaptor.capture());

		assertThat(intentCaptor.getValue())
				.classEqualTo(NowPlayingQueueActivity.class);
	}

	@Test public void gotoAudioEffects_startAudioEffectsActivity() {
		navigator.gotoAudioEffects();
		verify(activity).startActivity(intentCaptor.capture());
		assertThat(intentCaptor.getValue())
				.classEqualTo(AudioEffectsActivity.class);
	}

	@Test public void gotoSetting_startSettingActivity() {
		navigator.gotoSettings();
		verify(activity).startActivity(intentCaptor.capture());
		assertThat(intentCaptor.getValue())
				.classEqualTo(PrefActivity.class);
	}
}
