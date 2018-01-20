package com.edavtyan.materialplayer.player;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RepeatModeTests {
	@Test
	public void valueOf_returnCorrectMode() {
		assertThat(RepeatMode.valueOf("DISABLED")).isEqualTo(RepeatMode.DISABLED);
		assertThat(RepeatMode.valueOf("REPEAT_ONE")).isEqualTo(RepeatMode.REPEAT_ONE);
		assertThat(RepeatMode.valueOf("REPEAT_ALL")).isEqualTo(RepeatMode.REPEAT_ALL);
	}
}
