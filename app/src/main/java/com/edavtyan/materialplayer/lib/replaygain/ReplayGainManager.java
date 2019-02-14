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

	public ReplayGainManager(
			Amplifier amplifier,
			ReplayGainReader rgReader,
			ReplayGainPrefs prefs) {
		this.amplifier = amplifier;
		this.rgReader = rgReader;
		this.prefs = prefs;
		this.prefs.setOnPrefChangedListener(this::applyGain);
	}

	@Override
	public void onPlayerPluginConnected(Player player) {
		if (player.hasData()) {
			loadGain(player.getCurrentTrack());
		}
	}

	@Override
	public void onNewTrack(Track track) {
		loadGain(track);
	}

	private void loadGain(Track track) {
		rgData = rgReader.read(track.getPath());
		applyGain();
	}

	private void applyGain() {
		double gainWithPreamp = getRgGain() + getPreamp();
		if (prefs.isLimiterEnabled()) {
			amplifier.setGain(Math.min(gainWithPreamp, getRgPeak()));
		} else {
			amplifier.setGain(gainWithPreamp);
		}
	}

	private double getRgPeak() {
		if (!prefs.isLimiterEnabled() || rgData.getTrackPeak() == null) {
			return 0;
		}

		double peakGain = (1 - rgData.getTrackPeak()) * 89 / 10;
		return Math.min(peakGain, 0);
	}

	private double getRgGain() {
		if (!prefs.isEnabled()) {
			return 0;
		}

		if (prefs.isAlbumGainUsed()) {
			if (rgData.getAlbumRG() != null) return rgData.getAlbumRG();
			if (rgData.getTrackRG() != null) return rgData.getTrackRG();
			return 0;
		} else {
			if (rgData.getTrackRG() != null) return rgData.getTrackRG();
			return 0;
		}
	}

	private double getPreamp() {
		return prefs.isEnabled() ? prefs.getPreamp() : 0;
	}
}
