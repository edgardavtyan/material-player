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
		this.prefs.setOnLimitChangedListener(this::onLimitChanged);
	}

	private void onEnabledPrefChanged(boolean enabled) {
		if (enabled) {
			loadGain();
		} else {
			disable();
		}
	}

	private void onPreampPrefChanged() {
		applyGain();
	}

	private void onAlbumPrefChanged() {
		applyGain();
	}

	private void onLimitChanged() {
		applyGain();
	}

	@Override
	public void onPlayerPluginConnected(Player player) {
		if (player.hasData()) {
			currentTrack = player.getCurrentTrack();
			loadGain();
		}
	}

	@Override
	public void onNewTrack(Track track) {
		currentTrack = track;
		loadGain();
	}

	private void loadGain() {
		if (prefs.getEnabled()) {
			rgData = rgReader.read(currentTrack.getPath());
			applyGain();
		}
	}

	private void applyGain() {
		if (getRgGain() != null) {
			double gainWithPreamp = getRgGain() + prefs.getPreamp();
			if (prefs.isLimiterEnabled() && rgData.getTrackPeak() != null) {
				amplifier.setGain(Math.min(getRgGain(), getPeakRg()));
			} else {
				amplifier.setGain(gainWithPreamp);
			}
		}
	}

	private double getPeakRg() {
		if (rgData.getTrackPeak() != null && rgData.getTrackPeak() > 1) {
			return (1 - rgData.getTrackPeak()) * 89 / 10;
		} else {
			return 0;
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
