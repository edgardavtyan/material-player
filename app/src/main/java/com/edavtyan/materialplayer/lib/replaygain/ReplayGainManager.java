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
		rgData = rgReader.read(currentTrack.getPath());
		applyGain();
	}

	private void applyGain() {
		if (!prefs.isEnabled()) {
			amplifier.setGain(0);
			return;
		}

		double gainWithPreamp = getRgGain() + prefs.getPreamp();
		if (prefs.isLimiterEnabled()) {
			amplifier.setGain(Math.min(gainWithPreamp, getRgPeak()));
		} else {
			amplifier.setGain(gainWithPreamp);
		}
	}

	private double getRgPeak() {
		if (rgData.getTrackPeak() != null) {
			double peakGain = (1 - rgData.getTrackPeak()) * 89 / 10;
			return Math.min(peakGain, 0);
		} else {
			return 0;
		}
	}

	private Double getRgGain() {
		Double gain = prefs.isAlbumGainUsed() ? rgData.getAlbumRG() : rgData.getTrackRG();
		return gain == null ? 0 : gain;
	}
}
