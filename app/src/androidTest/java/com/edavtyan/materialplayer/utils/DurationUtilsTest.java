package com.edavtyan.materialplayer.utils;

import com.ed.libsutils.utils.DurationUtils;

import org.junit.Test;

import static com.edavtyan.materialplayer.testlib.assertions.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class DurationUtilsTest {
	@Test
	public void constructor_private() throws Exception {
		assertThat(DurationUtils.class.getDeclaredConstructor()).isPrivate();
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
}
