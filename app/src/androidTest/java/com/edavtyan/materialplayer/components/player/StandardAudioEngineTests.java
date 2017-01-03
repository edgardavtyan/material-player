package com.edavtyan.materialplayer.components.player;

import android.media.MediaPlayer;

import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;
import org.mockito.InOrder;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StandardAudioEngineTests extends BaseTest {
	private StandardAudioEngine audioEngine;
	private MediaPlayer player;

	@Override public void beforeEach() {
		super.beforeEach();
		player = spy(new MediaPlayer());
		audioEngine = new StandardAudioEngine(player);
	}

	@Test public void getSessionId_notZero() {
		assertThat(audioEngine.getSessionId()).isNotZero();
	}

	@Test public void play_startPlayer() {
		audioEngine.play();
		verify(player).start();
	}

	@Test public void pause_pausePlayer() {
		audioEngine.pause();
		verify(player).pause();
	}

	@Test public void playPause_playerPlaying_pause() {
		when(player.isPlaying()).thenReturn(true);
		audioEngine.playPause();
		verify(player).pause();
	}

	@Test public void playPause_playerPaused_play() {
		when(player.isPlaying()).thenReturn(false);
		audioEngine.playPause();
		verify(player).start();
	}

	@Test public void playTrack_resetAndPreparePlayer() throws IOException {
		audioEngine.playTrack("track_path");
		InOrder inOrder = inOrder(player);
		inOrder.verify(player).reset();
		inOrder.verify(player).setDataSource("track_path");
		inOrder.verify(player).prepareAsync();
	}

	@Test public void setVolume_setPlayerVolume() {
		audioEngine.setVolume(0.3f);
		verify(player).setVolume(0.3f, 0.3f);
	}

	@Test public void getPosition_getPlayerPosition() {
		when(player.getCurrentPosition()).thenReturn(100);
		assertThat(audioEngine.getPosition()).isEqualTo(100);
	}

	@Test public void setPosition_seekPlayer() {
		audioEngine.setPosition(200);
		verify(player).seekTo(200);
	}

	@Test public void isPlaying_playerPlaying_returnTrue() {
		when(player.isPlaying()).thenReturn(true);
		assertThat(audioEngine.isPlaying()).isTrue();
	}

	@Test public void isPlaying_playerPaused_returnFalse() {
		when(player.isPlaying()).thenReturn(false);
		assertThat(audioEngine.isPlaying()).isFalse();
	}

	@Test public void onPrepared_startPlayer() {
		audioEngine.onPrepared(player);
		verify(player).start();
	}

	@Test public void onPrepared_listenerSet_raiseListener() {
		PlayerMvp.AudioEngine.OnPreparedListener listener = mock(PlayerMvp.AudioEngine.OnPreparedListener.class);
		audioEngine.setOnPreparedListener(listener);
		audioEngine.onPrepared(player);
		verify(listener).onPrepared();
	}

	@Test public void onPrepared_listenerNotSet_notThrowNPE() {
		try {
			audioEngine.onPrepared(player);
		} catch (NullPointerException e) {
			fail("Expected NPE to not be thrown");
		}
	}

	@Test public void onCompletion_listenerSet_raiseListener() {
		PlayerMvp.AudioEngine.OnCompletedListener listener = mock(PlayerMvp.AudioEngine.OnCompletedListener.class);
		audioEngine.setOnCompletedListener(listener);
		audioEngine.onCompletion(player);
		verify(listener).onCompleted();
	}

	@Test public void onCompletion_listenerNotSet_notThrowNPE() {
		try {
			audioEngine.onCompletion(player);
		} catch (NullPointerException e) {
			fail("Expected NPE to not be thrown");
		}
	}
}
