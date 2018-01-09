package com.edavtyan.materialplayer.utils;

import com.ed.libsutils.ColorUtils;

import org.junit.Test;

import static com.edavtyan.materialplayer.testlib.assertions.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class ColorUtilsTest {
	@Test
	public void constructor_private() throws NoSuchMethodException {
		assertThat(ColorUtils.class.getDeclaredConstructor()).isPrivate();
	}

	@Test
	public void intToFloatAlpha_intAlphaGreaterThan255_return1() {
		assertThat(ColorUtils.intToFloatAlpha(12345)).isEqualTo(1.0f);
	}

	@Test
	public void intToFloatAlpha_intAlphaLessThan255_returnConvertedValue() {
		assertThat(ColorUtils.intToFloatAlpha(123)).isEqualTo(0.48f, within(0.01f));
	}
}
