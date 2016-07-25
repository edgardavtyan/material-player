package com.example.custompreference;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import java.util.Arrays;
import java.util.List;

import lombok.Cleanup;
import lombok.Getter;

public class ColorSelectionController extends DialogController<ColorSelectionPreference> {
	private final @Getter CharSequence key;
	private final @Getter CharSequence title;
	private final @Getter CharSequence defaultValue;
	private final @Getter List<CharSequence> entries;
	private final @Getter List<CharSequence> values;


	public ColorSelectionController(ColorSelectionPreference prefView, AttributeSet attributeSet) {
		super(prefView);

		@Cleanup("recycle")
		@SuppressLint("recycle")
		TypedArray attrs = prefView.context.obtainStyledAttributes(
				attributeSet, R.styleable.ColorSelectionPreference);
		key = attrs.getText(R.styleable.ColorSelectionPreference_cp_key);
		title = attrs.getText(R.styleable.ColorSelectionPreference_cp_title);
		defaultValue = attrs.getText(R.styleable.ColorSelectionPreference_cp_defaultValue);
		entries = Arrays.asList(attrs.getTextArray(R.styleable.ColorSelectionPreference_cp_entries));
		values = Arrays.asList(attrs.getTextArray(R.styleable.ColorSelectionPreference_cp_entryValues));
	}
}
