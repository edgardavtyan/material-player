package com.edavtyan.materialplayer.testlib.assertions;

import android.support.annotation.DrawableRes;

import com.edavtyan.materialplayer.lib.testable.TestableImageButton;

import org.assertj.core.api.AbstractAssert;

public class TestableImageButtonAssert
		extends AbstractAssert<TestableImageButtonAssert, TestableImageButton> {

	private final ImageViewAssert imageViewAssert;

	public TestableImageButtonAssert(TestableImageButton actual) {
		super(actual, TestableImageButtonAssert.class);
		imageViewAssert = new ImageViewAssert(actual);
	}

	public TestableImageButtonAssert hasColorFilter(int expectedColor) {
		String errorMessage = "\nExpected color filter to be\n<%s (%s)>\nbut was\n<%s (%s)>\n";
		String expectedColorHex = colorIntToHex(expectedColor);
		String actualColorHex = colorIntToHex(actual.getColorFilterColor());
		if (expectedColor != actual.getColorFilterColor())
			failWithMessage(
					errorMessage,
					expectedColor, expectedColorHex,
					actual.getColorFilterColor(), actualColorHex);
		return this;
	}

	@SuppressWarnings("UnusedReturnValue")
	public TestableImageButtonAssert hasImageResource(@DrawableRes int drawableId) {
		imageViewAssert.hasImageResource(drawableId);
		return this;
	}

	private String colorIntToHex(int color) {
		return String.format("#%06X", 0xFFFFFF & color).toLowerCase();
	}
}
