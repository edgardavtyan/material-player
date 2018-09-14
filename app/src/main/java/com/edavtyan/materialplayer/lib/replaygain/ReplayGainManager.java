package com.edavtyan.materialplayer.lib.replaygain;

import com.edavtyan.materialplayer.lib.prefs.BooleanPref;
import com.edavtyan.materialplayer.player.Player;
import com.edavtyan.materialplayer.player.effects.amplifier.Amplifier;

public class ReplayGainManager {
	private final Player player;
	private final Amplifier amplifier;
	private final ReplayGainReader rgReader;
	private final ReplayGainEnabledPref enabledPref;

	@SuppressWarnings("FieldCanBeLocal")
	private final Player.OnNewTrackListener onNewTrackListener = this::apply;

	@SuppressWarnings("FieldCanBeLocal")
	private final BooleanPref.OnPrefChanged onEnabledPrefChangedListener = enabled -> {
		if (enabled) {
			apply();
		} else {
			disable();
		}
	};

	public ReplayGainManager(
			Player player,
			Amplifier amplifier,
			ReplayGainReader rgReader,
			ReplayGainEnabledPref enabledPref) {
		this.player = player;
		this.player.setOnNewTrackListener(onNewTrackListener);
		this.amplifier = amplifier;
		this.rgReader = rgReader;
		this.enabledPref = enabledPref;
		this.enabledPref.setOnPrefChangedListener(onEnabledPrefChangedListener);
	}

	private void apply() {
		if (!enabledPref.isEnabled()) {
			return;
		}

		try {
			amplifier.setGain(rgReader.read(player.getCurrentTrack().getPath()));
		} catch (ReplayGainNotFoundException e) {
			amplifier.setGain(0);
		}
	}

	private void disable() {
		amplifier.setGain(0);
	}
}
