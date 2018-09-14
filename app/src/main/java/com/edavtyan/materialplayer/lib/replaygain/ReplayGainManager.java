package com.edavtyan.materialplayer.lib.replaygain;

import com.edavtyan.materialplayer.lib.prefs.BooleanPref;
import com.edavtyan.materialplayer.player.Player;
import com.edavtyan.materialplayer.player.effects.amplifier.Amplifier;

public class ReplayGainManager {
	private final Player player;
	private final ReplayGainPreampPref preampPref;
	private final Amplifier amplifier;
	private final ReplayGainReader rgReader;
	private final ReplayGainEnabledPref enabledPref;

	private double rgGain;

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

	@SuppressWarnings("FieldCanBeLocal")
	private final ReplayGainPreampPref.OnPreampChanged onPreampPrefChangedListener
			= new ReplayGainPreampPref.OnPreampChanged() {
		@Override
		public void onPreampChanged(double preamp) {
			if (enabledPref.isEnabled()) {
				amplifier.setGain(rgGain + preamp);
			}
		}
	};

	public ReplayGainManager(
			Player player,
			Amplifier amplifier,
			ReplayGainReader rgReader,
			ReplayGainEnabledPref enabledPref,
			ReplayGainPreampPref preampPref) {
		this.player = player;
		this.player.setOnNewTrackListener(onNewTrackListener);
		this.amplifier = amplifier;
		this.rgReader = rgReader;
		this.enabledPref = enabledPref;
		this.enabledPref.setOnPrefChangedListener(onEnabledPrefChangedListener);
		this.preampPref = preampPref;
		this.preampPref.setOnPreampChangedListener(onPreampPrefChangedListener);
	}

	private void apply() {
		if (!enabledPref.isEnabled()) {
			return;
		}

		try {
			rgGain = rgReader.read(player.getCurrentTrack().getPath());
			amplifier.setGain(rgGain + preampPref.getPreamp());
		} catch (ReplayGainNotFoundException e) {
			amplifier.setGain(0);
		}
	}

	private void disable() {
		amplifier.setGain(0);
	}
}
