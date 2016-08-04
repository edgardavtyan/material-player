package com.edavtyan.materialplayer;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(AndroidJUnit4.class)
public class TestUnit {
	@Test
	public void test() {
		assertThat(2 + 2).isEqualTo(4);
	}
}
