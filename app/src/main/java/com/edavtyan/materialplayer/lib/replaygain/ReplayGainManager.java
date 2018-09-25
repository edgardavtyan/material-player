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

	@SuppressWarnings({"FieldCanBeLocal", "Convert2Lambda"})
	private final ReplayGainPrefs.OnEnabledChanged onEnabledPrefChangedListener
			= new ReplayGainPrefs.OnEnabledChanged() {
		@Override
		public void onEnabledChanged(boolean enabled) {
			if (enabled) {
				apply();
			} else {
				disable();
			}
		}
	};

	@SuppressWarnings({"FieldCanBeLocal", "Convert2Lambda"})
	private final ReplayGainPrefs.OnAlbumUsedChanged onAlbumPrefChangedListener
			= new ReplayGainPrefs.OnAlbumUsedChanged() {
		@Override
		public void onAlbumUsedChanged(boolean albumGainUsed) {
			reapply();
		}
	};

	@SuppressWarnings({"FieldCanBeLocal", "Convert2Lambda"})
	private final ReplayGainPrefs.OnPreampChanged onPreampPrefChangedListener
			= new ReplayGainPrefs.OnPreampChanged() {
		@Override
		public void onPreampChanged(double preamp) {
			reapply();
		}
	};

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
			double rgGain = prefs.isAlbumGainUsed() ? rgData.getAlbumRG() : rgData.getTrackRG();
			amplifier.setGain(rgGain + prefs.getPreamp());
		}
	}

	private void reapply() {
		if (prefs.getEnabled()) {
			double rgGain = prefs.isAlbumGainUsed() ? rgData.getAlbumRG() : rgData.getTrackRG();
			amplifier.setGain(rgGain + prefs.getPreamp());
		}
	}

	private void disable() {
		amplifier.setGain(0);
	}
}
