package com.edavtyan.materialplayer.components.player2;

import com.edavtyan.materialplayer.components.player2.PlayerMvp.AudioEngine.OnPreparedListener;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;
import com.h6ah4i.android.media.IBasicMediaPlayer;

import org.junit.Test;
import org.mockito.InOrder;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OpenSLAudioEngineTest extends BaseTest {
	private OpenSLAudioEngine audioEngine;
	private IBasicMediaPlayer baseEngine;

	@Override public void beforeEach() {
		super.beforeEach();
		baseEngine = mock(IBasicMediaPlayer.class);
		audioEngine = new OpenSLAudioEngine(baseEngine);
	}

	@Test public void play_startBaseEngine() {
		audioEngine.play();
		verify(baseEngine).start();
	}

	@Test public void pause_pauseBaseEngine() {
		audioEngine.pause();
		verify(baseEngine).pause();
	}

	@Test public void playPause_baseEnginePlaying_pauseBaseEngine() {
		when(baseEngine.isPlaying()).thenReturn(true);
		audioEngine.playPause();
		verify(baseEngine).pause();
	}

	@Test public void playPause_baseEnginePaused_startBaseEngine() {
		when(baseEngine.isPlaying()).thenReturn(false);
		audioEngine.playPause();
		verify(baseEngine).start();
	}

	@Test public void playTrack_setBaseEngineSourceAndPlay() throws IOException {
		audioEngine.playTrack("path");

		InOrder order = inOrder(baseEngine);
		order.verify(baseEngine).setDataSource("path");
		order.verify(baseEngine).prepare();
		order.verify(baseEngine).start();
	}

	@Test public void playTrack_isPlaying_resetBaseEngineAndPlay() throws IOException {
		when(baseEngine.isPlaying()).thenReturn(true);
		audioEngine.playTrack("path");

		InOrder order = inOrder(baseEngine);
		order.verify(baseEngine).reset();
		order.verify(baseEngine).setDataSource("path");
		order.verify(baseEngine).prepare();
		order.verify(baseEngine).start();
	}

	@Test public void playTrack_callOnPreparedListener() {
		OnPreparedListener listener = mock(OnPreparedListener.class);
		audioEngine.setOnPreparedListener(listener);
		audioEngine.playTrack("path");
		verify(listener).onPrepared();
	}

	@Test public void getPosition_returnBaseEnginePosition() {
		when(baseEngine.getCurrentPosition()).thenReturn(1);
		assertThat(audioEngine.getPosition()).isEqualTo(1);
	}

	@Test public void setPosition_seekBaseEngine() {
		audioEngine.setPosition(2);
		verify(baseEngine).seekTo(2);
	}

	@Test public void isPlaying_returnFromBaseEngine() {
		when(baseEngine.isPlaying()).thenReturn(true);
		assertThat(audioEngine.isPlaying()).isTrue();
	}
}
