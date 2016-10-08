package com.edavtyan.materialplayer.components.player2;

import com.edavtyan.materialplayer.NowPlayingNotification;
import com.edavtyan.materialplayer.components.audioeffects.models.Amplifier;
import com.edavtyan.materialplayer.components.audioeffects.models.BassBoost;
import com.edavtyan.materialplayer.components.audioeffects.models.Surround;
import com.edavtyan.materialplayer.components.audioeffects.models.equalizer.Equalizer;
import com.edavtyan.materialplayer.lib.BaseTest;
import com.h6ah4i.android.media.opensl.OpenSLMediaPlayerContext.Parameters;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class PlayerFactoryTest extends BaseTest {
	private PlayerFactory playerFactory;

	@Override public void beforeEach() {
		super.beforeEach();
		Parameters params = mock(Parameters.class);
		playerFactory = new PlayerFactory(context, params);
	}

	@Test public void providePlayer_returnSamePlayerEveryTime() {
		runOnUiThread(() -> {
			PlayerMvp.Player firstPlayer = playerFactory.providePlayer();
			PlayerMvp.Player secondPlayer = playerFactory.providePlayer();
			assertThat(firstPlayer).isSameAs(secondPlayer);
		});
	}

	@Test public void provideEqualizer_returnSameEqualizerEveryTime() {
		runOnUiThread(() -> {
			Equalizer firstEqualizer = playerFactory.provideEqualizer();
			Equalizer secondEqualizer = playerFactory.provideEqualizer();
			assertThat(firstEqualizer).isSameAs(secondEqualizer);
		});
	}

	@Test public void provideAmplifier_returnSameAmplifierEveryTime() {
		runOnUiThread(() -> {
			Amplifier firstAmplifier = playerFactory.provideAmplifier();
			Amplifier secondAmplifier = playerFactory.provideAmplifier();
			assertThat(firstAmplifier).isSameAs(secondAmplifier);
		});
	}

	@Test public void provideBassBoost_returnSameBassBoostEveryTime() {
		runOnUiThread(() -> {
			BassBoost firstBassBoost = playerFactory.provideBassBoost();
			BassBoost secondBassBoost = playerFactory.provideBassBoost();
			assertThat(firstBassBoost).isSameAs(secondBassBoost);
		});
	}

	@Test public void provideSurround_returnSameSurroundEveryTime() {
		runOnUiThread(() -> {
			Surround firstSurround = playerFactory.provideSurround();
			Surround secondSurround = playerFactory.provideSurround();
			assertThat(firstSurround).isSameAs(secondSurround);
		});
	}

	@Test public void provideNotification_returnSameNotificationEveryTime() {
		runOnUiThread(() -> {
			NowPlayingNotification firstNotification = playerFactory.provideNotification();
			NowPlayingNotification secondNotification = playerFactory.provideNotification();
			assertThat(firstNotification).isSameAs(secondNotification);
		});
	}
}
