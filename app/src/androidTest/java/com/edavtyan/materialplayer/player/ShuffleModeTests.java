package com.edavtyan.materialplayer.player;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ShuffleModeTests {
	@Test
	public void valueOf_returnCorrectMode() {
		assertThat(ShuffleMode.valueOf("DISABLED")).isEqualTo(ShuffleMode.DISABLED);
		assertThat(ShuffleMode.valueOf("ENABLED")).isEqualTo(ShuffleMode.ENABLED);
	}
}
