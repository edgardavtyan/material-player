package com.edavtyan.materialplayer.components.player.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;

import com.edavtyan.materialplayer.components.player.Player;
import com.edavtyan.materialplayer.lib.base.BaseFactory;

public class ReceiversFactory extends BaseFactory {
	private final Player player;

	private CloseReceiver closeReceiver;
	private SkipToNextReceiver skipToNextReceiver;
	private SkipToPreviousReceiver skipToPreviousReceiver;
	private PlayPauseReceiver playPauseReceiver;
	private AudioBecomingNoisyReceiver audioBecomingNoisyReceiver;
	private MediaButtonReceiver mediaButtonReceiver;
	private HeadphonesConnectedReceiver headphonesConnectedReceiver;
	private PlayOnHeadsetPluggedPref playOnHeadsetPluggedPref;

	public ReceiversFactory(Context context, Player player) {
		super(context);
		this.player = player;
	}

	public BroadcastReceiver getCloseReceiver() {
		if (closeReceiver == null)
			closeReceiver = new CloseReceiver();
		return closeReceiver;
	}

	public SkipToNextReceiver getSkipToNextReceiver() {
		if (skipToNextReceiver == null)
			skipToNextReceiver = new SkipToNextReceiver(player);
		return skipToNextReceiver;
	}

	public SkipToPreviousReceiver getSkipToPreviousReceiver() {
		if (skipToPreviousReceiver == null)
			skipToPreviousReceiver = new SkipToPreviousReceiver(player);
		return skipToPreviousReceiver;
	}

	public PlayPauseReceiver getPlayPauseReceiver() {
		if (playPauseReceiver == null)
			playPauseReceiver = new PlayPauseReceiver(player);
		return playPauseReceiver;
	}

	public MediaButtonReceiver getMediaButtonReceiver() {
		if (mediaButtonReceiver == null)
			mediaButtonReceiver = new MediaButtonReceiver(player);
		return mediaButtonReceiver;
	}

	public AudioBecomingNoisyReceiver getAudioBecomingNoisyReceiver() {
		if (audioBecomingNoisyReceiver == null)
			audioBecomingNoisyReceiver = new AudioBecomingNoisyReceiver(player);
		return audioBecomingNoisyReceiver;
	}

	public HeadphonesConnectedReceiver getHeadphonesConnectedReceiver() {
		if (headphonesConnectedReceiver == null)
			headphonesConnectedReceiver = new HeadphonesConnectedReceiver(
					player,
					getPlayOnHeadsetPluggedPref());
		return headphonesConnectedReceiver;
	}

	public PlayOnHeadsetPluggedPref getPlayOnHeadsetPluggedPref() {
		if (playOnHeadsetPluggedPref == null)
			playOnHeadsetPluggedPref = new PlayOnHeadsetPluggedPref(getContext(), getPrefs());
		return playOnHeadsetPluggedPref;
	}
}
