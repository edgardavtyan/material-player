package com.edavtyan.materialplayer.lib.replaygain;

import com.edavtyan.materialplayer.player.Player;
import com.edavtyan.materialplayer.player.effects.amplifier.Amplifier;

public class ReplayGainManager {
	private final Player player;
	private final Amplifier amplifier;
	private final ReplayGainReader rgReader;
	private final ReplayGainPrefs prefs;

	private ReplayGainData rgData;

	@SuppressWarnings({"FieldCanBeLocal", "Convert2Lambda", "Anonymous2MethodRef"})
	private final Player.OnNewTrackListener onNewTrackListener
			= new Player.OnNewTrackListener() {
		@Override
		public void onNewTrack() {
			apply();
		}
	};

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
		apply();
	}

	private void apply() {
		if (prefs.getEnabled()) {
			rgData = rgReader.read(player.getCurrentTrack().getPath());
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
