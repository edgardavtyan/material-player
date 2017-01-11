package com.edavtyan.materialplayer.utils;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static com.edavtyan.materialplayer.testlib.asertions.ConstructorAssert.assertThatConstructor;
import static org.assertj.core.api.Assertions.assertThat;

public class DurationUtilsTest {
	@Test
	public void constructor_private() throws Exception {
		assertThatConstructor(DurationUtils.class.getDeclaredConstructor()).isPrivate();
	}

	@Test
	public void toStringUntilHours_withHoursAndDoubleDigit_returnFormattedString() {
		assertThat(DurationUtils.toStringUntilHours(49491000)).isEqualTo("13:44:51");
	}

	@Test
	public void toStringUntilHours_withHoursAndSingleDigit_padWithZeros() {
		assertThat(DurationUtils.toStringUntilHours(18429000)).isEqualTo("05:07:09");
	}

	@Test
	public void toStringUntilHours_noHoursAndDoubleDigit_returnFormattedStringWithoutHours() {
		assertThat(DurationUtils.toStringUntilHours(2155000)).isEqualTo("35:55");
	}

	@Test
	public void toStringUntilHours_noHoursAndSingleDigit_padWithZerosWithoutHours() {
		assertThat(DurationUtils.toStringUntilHours(369000)).isEqualTo("06:09");
	}

	private <T> void testConstructorPrivate(Constructor<T> constructor) throws Exception {
		constructor.setAccessible(true);
		constructor.newInstance();
		assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();
	}
}
