package com.edavtyan.materialplayer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.testlib.tests.BaseTest;
import com.edavtyan.materialplayer.ui.audio_effects.AudioEffectsActivity;
import com.edavtyan.materialplayer.ui.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.ui.now_playing_queue.NowPlayingQueueActivity;
import com.edavtyan.materialplayer.ui.prefs.PrefActivity;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static com.ed.libsutils.assertj.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NavigatorTest extends BaseTest {
	private AppCompatActivity activity;
	private Navigator navigator;
	private ArgumentCaptor<Intent> intentCaptor;

	@Override
	public void beforeEach() {
		super.beforeEach();
		activity = mock(AppCompatActivity.class);
		navigator = new Navigator(activity);
		intentCaptor = ArgumentCaptor.forClass(Intent.class);
	}

	@Test
	public void gotoNowPlaying_startNowPlayingActivityWithCorrectParameters() {
		navigator.gotoNowPlaying(activity, new Bundle());

		verify(activity).startActivity(intentCaptor.capture());

		assertThat(intentCaptor.getValue()).hasClass(NowPlayingActivity.class);
	}

	@Test
	public void gotoNowPlayingQueue_startPlaylistActivity() {
		navigator.gotoNowPlayingQueue(activity);

		verify(activity).startActivity(intentCaptor.capture());

		assertThat(intentCaptor.getValue()).hasClass(NowPlayingQueueActivity.class);
	}

	@Test
	public void gotoAudioEffects_startAudioEffectsActivity() {
		navigator.gotoAudioEffects();
		verify(activity).startActivity(intentCaptor.capture());
		assertThat(intentCaptor.getValue()).hasClass(AudioEffectsActivity.class);
	}

	@Test
	public void gotoSetting_startSettingActivity() {
		navigator.gotoSettings();
		verify(activity).startActivity(intentCaptor.capture());
		assertThat(intentCaptor.getValue()).hasClass(PrefActivity.class);
	}
}
