package com.edavtyan.materialplayer.utils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringsTest {
	@Test public void join_joinArrayWithSeparator() {
		assertThat(Strings.join(new int[] {1,2,3,4,5}, ",")).isEqualTo("1,2,3,4,5");
	}
}
