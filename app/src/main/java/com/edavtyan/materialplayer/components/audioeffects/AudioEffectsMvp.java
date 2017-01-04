package com.edavtyan.materialplayer.components.audioeffects;

import android.content.ServiceConnection;

import com.edavtyan.materialplayer.components.audioeffects.models.Amplifier;
import com.edavtyan.materialplayer.components.audioeffects.models.BassBoost;
import com.edavtyan.materialplayer.components.audioeffects.models.Equalizer;
import com.edavtyan.materialplayer.components.audioeffects.models.Surround;
import com.edavtyan.materialplayer.components.audioeffects.views.EqualizerBandView;

@SuppressWarnings("unused")
public interface AudioEffectsMvp {
	interface Model extends ServiceConnection {
		interface ServiceConnectionListener {
			void onServiceConnected();
		}

		void setOnServiceConnectedListener(ServiceConnectionListener onServiceConnectedListener);
		void init();
		void close();
		Equalizer getEqualizer();
		BassBoost getBassBoost();
		Surround getSurround();
		Amplifier getAmplifier();
	}

	interface View {
		void setEqualizerEnabled(boolean enabled);
		void setEqualizerBands(int count, int gainLimit, int[] frequencies, int[] gains);
		void initBassBoost(int max, int strength);
		void initSurround(int max, int strength);
		void initAmplifier(int max, int gain);
	}

	interface Presenter extends Model.ServiceConnectionListener {
		void onCreate();
		void onDestroy();
		void onEqualizerEnabledChanged(boolean enabled);
		void onEqualizerBandChanged(EqualizerBandView band);
		void onEqualizerBandStopTracking();
		void onBassBoostStrengthChanged(int strength);
		void onBassBoostStrengthStopChanging();
		void onSurroundStrengthChanged(int progress);
		void onSurroundStrengthStopChanging();
		void onAmplifierStrengthChanged(int gain);
		void onAmplifierStrengthStopChanging();
	}
}
