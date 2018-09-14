package com.edavtyan.materialplayer.lib.replaygain;

import com.edavtyan.materialplayer.player.Player;
import com.edavtyan.materialplayer.player.effects.amplifier.Amplifier;

public class ReplayGainManager {
	private final Player player;
	private final Amplifier amplifier;
	private final ReplayGainReader rgReader;
	private final ReplayGainPrefs prefs;

	private double rgGain;

	@SuppressWarnings("FieldCanBeLocal")
	private final Player.OnNewTrackListener onNewTrackListener = this::apply;

	@SuppressWarnings("FieldCanBeLocal")
	private final ReplayGainPrefs.OnEnabledChanged onEnabledPrefChangedListener = enabled -> {
		if (enabled) {
			apply();
		} else {
			disable();
		}
	};

	@SuppressWarnings("FieldCanBeLocal")
	private final ReplayGainPrefs.OnPreampChanged onPreampPrefChangedListener = preamp -> {
		if ((this).prefs.getEnabled()) {
			(this).amplifier.setGain(rgGain + preamp);
		}
	};

	public ReplayGainManager(
			Player player,
			Amplifier amplifier,
			ReplayGainReader rgReader,
			ReplayGainPrefs prefs) {
		this.player = player;
		this.player.setOnNewTrackListener(onNewTrackListener);
		this.amplifier = amplifier;
		this.rgReader = rgReader;
		this.prefs = prefs;
		this.prefs.setOnEnabledChangedListener(onEnabledPrefChangedListener);
		this.prefs.setOnPreampChangedListener(onPreampPrefChangedListener);
	}

	private void apply() {
		if (!prefs.getEnabled()) {
			return;
		}

		try {
			rgGain = rgReader.read(player.getCurrentTrack().getPath());
			amplifier.setGain(rgGain + prefs.getPreamp());
		} catch (ReplayGainNotFoundException e) {
			amplifier.setGain(0);
		}
	}

	private void disable() {
		amplifier.setGain(0);
	}
}
