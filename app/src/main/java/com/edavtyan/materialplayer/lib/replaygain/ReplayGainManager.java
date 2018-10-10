package com.edavtyan.materialplayer.lib.replaygain;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.player.Player;
import com.edavtyan.materialplayer.player.PlayerPlugin;
import com.edavtyan.materialplayer.player.effects.amplifier.Amplifier;

public class ReplayGainManager implements PlayerPlugin {
	private final Amplifier amplifier;
	private final ReplayGainReader rgReader;
	private final ReplayGainPrefs prefs;

	private ReplayGainData rgData;
	private Track currentTrack;

	@SuppressWarnings("FieldCanBeLocal")
	private final ReplayGainPrefs.OnEnabledChanged onEnabledPrefChangedListener = enabled -> {
		if (enabled) {
			apply();
		} else {
			disable();
		}
	};

	@SuppressWarnings("FieldCanBeLocal")
	private final ReplayGainPrefs.OnAlbumUsedChanged onAlbumPrefChangedListener = this::reapply;

	@SuppressWarnings("FieldCanBeLocal")
	private final ReplayGainPrefs.OnPreampChanged onPreampPrefChangedListener = this::reapply;

	public ReplayGainManager(
			Amplifier amplifier,
			ReplayGainReader rgReader,
			ReplayGainPrefs prefs) {
		this.amplifier = amplifier;
		this.rgReader = rgReader;
		this.prefs = prefs;
		this.prefs.setOnEnabledChangedListener(onEnabledPrefChangedListener);
		this.prefs.setOnPreampChangedListener(onPreampPrefChangedListener);
		this.prefs.setOnAlbumUsedChangedListener(onAlbumPrefChangedListener);
	}

	@Override
	public void onPlayerPluginConnected(Player player) {
		if (player.hasData()) {
			currentTrack = player.getCurrentTrack();
			apply();
		}
	}

	@Override
	public void onNewTrack(Track track) {
		currentTrack = track;
		apply();
	}

	private void apply() {
		if (prefs.getEnabled()) {
			rgData = rgReader.read(currentTrack.getPath());
			amplifier.setGain(getRgGain() + prefs.getPreamp());
		}
	}

	private void reapply() {
		if (prefs.getEnabled()) {
			amplifier.setGain(getRgGain() + prefs.getPreamp());
		}
	}

	private void disable() {
		amplifier.setGain(0);
	}

	private double getRgGain() {
		if (prefs.isAlbumGainUsed() && rgData.getAlbumRG() != null) {
			return rgData.getAlbumRG();
		} else if (rgData.getTrackRG() != null) {
			return rgData.getTrackRG();
		} else {
			return 0;
		}
	}
}
