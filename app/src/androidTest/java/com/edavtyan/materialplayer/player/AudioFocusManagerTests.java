package com.edavtyan.materialplayer.player;

import android.content.Context;
import android.media.AudioManager;

import com.edavtyan.materialplayer.player.managers.AudioFocusManager;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class AudioFocusManagerTests extends BaseTest {
	private AudioManager audioManager;
	private Player player;
	private AudioFocusManager audioFocusManager;

	@Override
	public void beforeEach() {
		super.beforeEach();
		audioManager = mock(AudioManager.class);
		when(context.getSystemService(Context.AUDIO_SERVICE)).thenReturn(audioManager);
		player = mock(Player.class);
		audioFocusManager = new AudioFocusManager(context, player);
	}

	@Test
	public void requestAudioFocus_callAudioManager() {
		audioFocusManager.requestFocus();
		verify(audioManager).requestAudioFocus(
				audioFocusManager, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
	}

	@Test
	public void dropAudioFocus_callAudioManager() {
		audioFocusManager.dropFocus();
		verify(audioManager).abandonAudioFocus(audioFocusManager);
	}

	@Test
	public void onAudioFocusChange_gainAfterLossTransientAndPlayerWasPlaying_playPlayer() {
		when(player.isPlaying()).thenReturn(true);
		audioFocusManager.onAudioFocusChange(AudioManager.AUDIOFOCUS_LOSS_TRANSIENT);
		audioFocusManager.onAudioFocusChange(AudioManager.AUDIOFOCUS_GAIN);
		verify(player).play();
	}

	@Test
	public void onAudioFocusChange_gainAfterLossTransientAndPlayerWasPaused_notPlayPlayer() {
		when(player.isPlaying()).thenReturn(false);
		audioFocusManager.onAudioFocusChange(AudioManager.AUDIOFOCUS_LOSS_TRANSIENT);
		audioFocusManager.onAudioFocusChange(AudioManager.AUDIOFOCUS_GAIN);
		verify(player, never()).play();
	}

	@Test
	public void onAudioFocusChange_gainAfterLossTransientCanDuck_restoreVolume() {
		audioFocusManager.onAudioFocusChange(AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK);
		audioFocusManager.onAudioFocusChange(AudioManager.AUDIOFOCUS_GAIN);
		verify(player).restoreVolume();
	}

	@Test
	public void onAudioFocusChange_lossAndPlayerWasPlaying_pausePlayer() {
		when(player.isPlaying()).thenReturn(true);
		audioFocusManager.onAudioFocusChange(AudioManager.AUDIOFOCUS_LOSS);
		verify(player).pause();
	}

	@Test
	public void onAudioFocusChange_lossTransient_pausePlayer() {
		audioFocusManager.onAudioFocusChange(AudioManager.AUDIOFOCUS_LOSS_TRANSIENT);
		verify(player).pause();
	}

	@Test
	public void onAudioFocusChange_lossTransientCanDuck_lowerVolume() {
		audioFocusManager.onAudioFocusChange(AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK);
		verify(player).lowerVolume();
	}

	@Test
	public void onAudioFocusChanged_otherFocus_notCallPlayer() {
		audioFocusManager.onAudioFocusChange(-100);
		verifyZeroInteractions(player);
	}
}
