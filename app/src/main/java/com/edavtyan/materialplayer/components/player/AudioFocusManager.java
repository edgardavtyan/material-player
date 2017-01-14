package com.edavtyan.materialplayer.components.player;

import android.content.Context;
import android.media.AudioManager;

public class AudioFocusManager implements AudioManager.OnAudioFocusChangeListener {

	private final PlayerMvp.Player player;
	private final AudioManager audioManager;
	private boolean isPlaying;
	private int currentFocus;

	public AudioFocusManager(Context context, PlayerMvp.Player player) {
		this.player = player;
		audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
	}

	public void requestFocus() {
		audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
	}

	public void dropFocus() {
		audioManager.abandonAudioFocus(this);
	}

	@Override
	public void onAudioFocusChange(int focusChange) {
		if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
			if (currentFocus == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
				onAudioFocusGainAfterTransientLoss();
			} else if (currentFocus == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
				onAudioFocusGainAfterTransientLossCanDuck();
			}

			currentFocus = AudioManager.AUDIOFOCUS_GAIN;
			isPlaying = player.isPlaying();
		} else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
			onAudioFocusLoss();
			currentFocus = AudioManager.AUDIOFOCUS_LOSS;
		} else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
			onAudioFocusLossTransient();
			currentFocus = AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
		} else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
			onAudioFocusLossTransientCanDuck();
			currentFocus = AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;
		}
	}

	private void onAudioFocusGainAfterTransientLoss() {
		if (isPlaying) {
			player.play();
			isPlaying = true;
		}
	}

	private void onAudioFocusGainAfterTransientLossCanDuck() {
		player.restoreVolume();
	}

	private void onAudioFocusLoss() {
		if (isPlaying) {
			player.pause();
			isPlaying = false;
		}
	}

	private void onAudioFocusLossTransient() {
		player.pause();
	}

	private void onAudioFocusLossTransientCanDuck() {
		player.lowerVolume();
	}
}
