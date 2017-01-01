package com.edavtyan.materialplayer.components.player;

import android.content.Context;
import android.media.AudioManager;

import hugo.weaving.DebugLog;

public class AudioFocusManager implements AudioManager.OnAudioFocusChangeListener {
	private final Context context;
	private final PlayerMvp.Player player;
	private final AudioManager audioManager;
	private int currentState;

	public AudioFocusManager(Context context, PlayerMvp.Player player) {
		this.context = context;
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
			if (currentState == AudioManager.AUDIOFOCUS_LOSS) {
				onAudioFocusGain();
				currentState = AudioManager.AUDIOFOCUS_LOSS;
			} else if (currentState == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
				onAudioFocusGainAfterLossTransient();
				currentState = AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
			} else if (currentState == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
				onAudioFocusGainAfterTransientLossCanDuck();
				currentState = AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;
			}
		} else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
			onAudioFocusLoss();
		} else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
			onAudioFocusLossTransient();
		} else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
			onAudioFocusLossTransientCanDuck();
		}
	}

	@DebugLog
	private void onAudioFocusGain() {
		player.play();
	}

	@DebugLog
	private void onAudioFocusGainAfterLossTransient() {
		player.play();
	}

	@DebugLog
	private void onAudioFocusGainAfterTransientLossCanDuck() {
		player.restoreVolume();
	}

	@DebugLog
	private void onAudioFocusLoss() {
		player.pause();
	}

	@DebugLog
	private void onAudioFocusLossTransient() {
		player.pause();
	}

	@DebugLog
	private void onAudioFocusLossTransientCanDuck() {
		player.lowerVolume();
	}
}
