package com.edavtyan.materialplayer.lib.replaygain;

import android.support.annotation.Nullable;

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

	public ReplayGainManager(
			Amplifier amplifier,
			ReplayGainReader rgReader,
			ReplayGainPrefs prefs) {
		this.amplifier = amplifier;
		this.rgReader = rgReader;
		this.prefs = prefs;
		this.prefs.setOnEnabledChangedListener(this::onEnabledPrefChanged);
		this.prefs.setOnPreampChangedListener(this::onPreampPrefChanged);
		this.prefs.setOnAlbumUsedChangedListener(this::onAlbumPrefChanged);
	}

	private void onEnabledPrefChanged(boolean enabled) {
		if (enabled) {
			apply();
		} else {
			disable();
		}
	}

	private void onPreampPrefChanged() {
		reapply();
	}

	private void onAlbumPrefChanged() {
		reapply();
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

			if (getRgGain() != null) {
				amplifier.setGain(getRgGain() + prefs.getPreamp());
			}
		}
	}

	private void reapply() {
		if (prefs.getEnabled() && getRgGain() != null) {
			amplifier.setGain(getRgGain() + prefs.getPreamp());
		}
	}

	private void disable() {
		amplifier.setGain(0);
	}

	@Nullable
	private Double getRgGain() {
		if (prefs.isAlbumGainUsed() && rgData.getAlbumRG() != null) {
			return rgData.getAlbumRG();
		} else if (rgData.getTrackRG() != null) {
			return rgData.getTrackRG();
		} else {
			return null;
		}
	}
}
