package com.edavtyan.materialplayer.utils;

import android.graphics.Color;

import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomColorsTest extends BaseTest {
	@Test public void fade_alphaMoreThat255_fadeTo255() {
		CustomColor color = new CustomColor(Color.parseColor("#33aabbcc"));
		assertThat(color.fade(400)).isEqualTo(Color.parseColor("#ffaabbcc"));
	}

	@Test public void fade_alphaLessThat255_fadeToGivenAlpha() {
		CustomColor color = new CustomColor(Color.parseColor("#33aabbcc"));
		assertThat(color.fade(100)).isEqualTo(Color.parseColor("#64aabbcc"));
	}
}
