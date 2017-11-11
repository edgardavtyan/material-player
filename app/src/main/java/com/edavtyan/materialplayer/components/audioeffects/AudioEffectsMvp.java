package com.edavtyan.materialplayer.components.audioeffects;

import com.edavtyan.materialplayer.components.audioeffects.models.Amplifier;
import com.edavtyan.materialplayer.components.audioeffects.models.BassBoost;
import com.edavtyan.materialplayer.components.audioeffects.models.Equalizer;
import com.edavtyan.materialplayer.components.audioeffects.models.Surround;
import com.edavtyan.materialplayer.components.audioeffects.views.EqualizerBandView;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

import java.util.List;

@SuppressWarnings("unused")
public interface AudioEffectsMvp {
	interface Model {
		void setOnServiceConnectedListener(ModelServiceModule.OnServiceConnectedListener listener);
		void init();
		void close();
		boolean isConnected();
		Equalizer getEqualizer();
		BassBoost getBassBoost();
		Surround getSurround();
		Amplifier getAmplifier();
	}

	interface View {
		void setEqualizerEnabled(boolean enabled);
		void setEqualizerBands(int count, int gainLimit, int[] frequencies, int[] gains);
		void setEqualizerPresets(List<String> presets);
		void setEquqlizerPresetAsCustomNew();
		void initBassBoost(int max, int strength);
		void initSurround(int max, int strength);
		void initAmplifier(int max, int gain);
		void setCurrentEqualizerPreset(int presetIndex);
	}

	interface Presenter extends ModelServiceModule.OnServiceConnectedListener {
		void onCreate();
		void onDestroy();
		void onEqualizerEnabledChanged(boolean enabled);
		void onEqualizerBandChanged(EqualizerBandView band);
		void onEqualizerBandStopTracking();
		void onPresetSelected(int position);
		void onBassBoostStrengthChanged(int strength);
		void onBassBoostStrengthStopChanging();
		void onSurroundStrengthChanged(int progress);
		void onSurroundStrengthStopChanging();
		void onAmplifierStrengthChanged(int gain);
		void onAmplifierStrengthStopChanging();
	}
}
