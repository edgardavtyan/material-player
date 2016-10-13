package com.edavtyan.materialplayer.components.player2;

import com.edavtyan.materialplayer.components.audioeffects.models.Amplifier;
import com.edavtyan.materialplayer.components.audioeffects.models.BassBoost;
import com.edavtyan.materialplayer.components.audioeffects.models.Surround;
import com.edavtyan.materialplayer.components.audioeffects.models.equalizer.Equalizer;
import com.edavtyan.materialplayer.lib.BaseTest;
import com.edavtyan.materialplayer.lib.rules.UiThreadRule;

import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerFactoryTest extends BaseTest {
	@Rule public UiThreadRule uiThreadRule = new UiThreadRule();

	private PlayerFactory playerFactory;

	@Override public void beforeEach() {
		super.beforeEach();
		playerFactory = new PlayerFactory(context);
	}

	@Test public void providePlayer_returnSamePlayerEveryTime() {
		PlayerMvp.Player firstPlayer = playerFactory.providePlayer();
		PlayerMvp.Player secondPlayer = playerFactory.providePlayer();
		assertThat(firstPlayer).isSameAs(secondPlayer);
	}

	@Test public void provideEqualizer_returnSameEqualizerEveryTime() {
		Equalizer firstEqualizer = playerFactory.provideEqualizer();
		Equalizer secondEqualizer = playerFactory.provideEqualizer();
		assertThat(firstEqualizer).isSameAs(secondEqualizer);
	}

	@Test public void provideAmplifier_returnSameAmplifierEveryTime() {
		Amplifier firstAmplifier = playerFactory.provideAmplifier();
		Amplifier secondAmplifier = playerFactory.provideAmplifier();
		assertThat(firstAmplifier).isSameAs(secondAmplifier);
	}

	@Test public void provideBassBoost_returnSameBassBoostEveryTime() {
		BassBoost firstBassBoost = playerFactory.provideBassBoost();
		BassBoost secondBassBoost = playerFactory.provideBassBoost();
		assertThat(firstBassBoost).isSameAs(secondBassBoost);
	}

	@Test public void provideSurround_returnSameSurroundEveryTime() {
		Surround firstSurround = playerFactory.provideSurround();
		Surround secondSurround = playerFactory.provideSurround();
		assertThat(firstSurround).isSameAs(secondSurround);
	}
}
