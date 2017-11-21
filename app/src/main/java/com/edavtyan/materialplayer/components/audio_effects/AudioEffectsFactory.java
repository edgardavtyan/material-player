package com.edavtyan.materialplayer.components.audio_effects;

import android.content.Context;
import android.media.audiofx.LoudnessEnhancer;
import android.media.audiofx.Virtualizer;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.edavtyan.materialplayer.components.audio_effects.amplifier.Amplifier;
import com.edavtyan.materialplayer.components.audio_effects.amplifier.AmplifierPrefs;
import com.edavtyan.materialplayer.components.audio_effects.amplifier.StandardAmplifier;
import com.edavtyan.materialplayer.components.audio_effects.bassboost.BassBoost;
import com.edavtyan.materialplayer.components.audio_effects.bassboost.BassBoostPrefs;
import com.edavtyan.materialplayer.components.audio_effects.bassboost.StandardBassBoost;
import com.edavtyan.materialplayer.components.audio_effects.equalizer.Equalizer;
import com.edavtyan.materialplayer.components.audio_effects.equalizer.EqualizerBase;
import com.edavtyan.materialplayer.components.audio_effects.equalizer.EqualizerPrefs;
import com.edavtyan.materialplayer.components.audio_effects.equalizer.StandardEqualizer;
import com.edavtyan.materialplayer.components.audio_effects.equalizer.StandardEqualizerBase;
import com.edavtyan.materialplayer.components.audio_effects.equalizer.presets.PresetsPrefs;
import com.edavtyan.materialplayer.components.audio_effects.surround.StandardSurround;
import com.edavtyan.materialplayer.components.audio_effects.surround.Surround;
import com.edavtyan.materialplayer.components.audio_effects.surround.SurroundPrefs;
import com.edavtyan.materialplayer.components.player.Player;
import com.edavtyan.materialplayer.lib.base.BaseFactory;

public class AudioEffectsFactory extends BaseFactory {
	private final Player player;

	private BassBoost bassBoost;
	private BassBoostPrefs bassBoostPrefs;
	private Equalizer equalizer;
	private EqualizerBase equalizerBase;
	private EqualizerPrefs equalizerPrefs;
	private PresetsPrefs presetsPrefs;
	private Surround surround;
	private SurroundPrefs surroundPrefs;
	private Amplifier amplifier;

	public AudioEffectsFactory(Context context, Player player) {
		super(context);
		this.player = player;
	}

	public Equalizer getEqualizer() {
		if (equalizer == null)
			equalizer = new StandardEqualizer(
					getEqualizerBase(),
					getEqualizerPrefs(),
					getPresetsPrefs());
		return equalizer;
	}

	public EqualizerBase getEqualizerBase() {
		if (equalizerBase == null)
			equalizerBase = new StandardEqualizerBase(
					new android.media.audiofx.Equalizer(0, player.getSessionId()));
		return equalizerBase;
	}

	public EqualizerPrefs getEqualizerPrefs() {
		if (equalizerPrefs == null)
			equalizerPrefs = new EqualizerPrefs(getPrefs());
		return equalizerPrefs;
	}

	public PresetsPrefs getPresetsPrefs() {
		if (presetsPrefs == null)
			presetsPrefs = new PresetsPrefs(getAdvancedGsonSharedPrefs());
		return presetsPrefs;
	}

	public BassBoost getBassBoost() {
		if (bassBoost == null)
			bassBoost = new StandardBassBoost(
					new android.media.audiofx.BassBoost(0, player.getSessionId()),
					getBassBoostPrefs());
		return bassBoost;
	}

	public BassBoostPrefs getBassBoostPrefs() {
		if (bassBoostPrefs == null)
			bassBoostPrefs = new BassBoostPrefs(getPrefs());
		return bassBoostPrefs;
	}

	public Surround getSurround() {
		if (surround == null)
			surround = new StandardSurround(
					new Virtualizer(0, player.getSessionId()),
					getSurroundPrefs());
		return surround;
	}

	public SurroundPrefs getSurroundPrefs() {
		if (surroundPrefs == null)
			surroundPrefs = new SurroundPrefs(getPrefs());
		return surroundPrefs;
	}

	@RequiresApi(api = Build.VERSION_CODES.KITKAT)
	public Amplifier getAmplifier() {
		if (amplifier == null)
			amplifier = new StandardAmplifier(
					new LoudnessEnhancer(player.getSessionId()),
					new AmplifierPrefs(getPrefs()));
		return amplifier;
	}
}
