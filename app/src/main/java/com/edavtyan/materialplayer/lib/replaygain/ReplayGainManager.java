package com.edavtyan.materialplayer.lib.replaygain;

import com.edavtyan.materialplayer.player.Player;
import com.edavtyan.materialplayer.player.effects.amplifier.Amplifier;

public class ReplayGainManager {
	private final Player player;
	private final Amplifier amplifier;
	private final ReplayGainReader rgReader;
	private final ReplayGainPrefs prefs;

	private ReplayGainData rgGain;

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
	private final ReplayGainPrefs.OnAlbumUsedChanged onAlbumPrefChangedListener = albumGainUsed -> {
		if ((this).prefs.getEnabled()) {
			if ((this).prefs.isAlbumGainUsed()) {
				(this).amplifier.setGain(rgGain.getAlbumRG() + (this).prefs.getPreamp());
			} else {
				(this).amplifier.setGain(rgGain.getTrackRG() + (this).prefs.getPreamp());
			}
		}
	};

	@SuppressWarnings("FieldCanBeLocal")
	private final ReplayGainPrefs.OnPreampChanged onPreampPrefChangedListener = preamp -> {
		if ((this).prefs.getEnabled()) {
			if ((this).prefs.isAlbumGainUsed()) {
				(this).amplifier.setGain(rgGain.getAlbumRG() + preamp);
			} else {
				(this).amplifier.setGain(rgGain.getTrackRG() + preamp);
			}
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
		this.prefs.setOnAlbumUsedChangedListener(onAlbumPrefChangedListener);
	}

	private void apply() {
		if (!prefs.getEnabled()) {
			return;
		}

		rgGain = rgReader.read(player.getCurrentTrack().getPath());

		if (prefs.isAlbumGainUsed()) {
			amplifier.setGain(rgGain.getAlbumRG() + prefs.getPreamp());
		} else {
			amplifier.setGain(rgGain.getTrackRG() + prefs.getPreamp());
		}
	}

	private void disable() {
		amplifier.setGain(0);
	}
}
