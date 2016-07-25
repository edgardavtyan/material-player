package com.example.custompreference;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Cleanup;
import lombok.Getter;

public class ColorSelectionController extends DialogController<ColorSelectionPreference> {
	private final @Getter CharSequence key;
	private final @Getter CharSequence title;
	private final @Getter CharSequence defaultValue;
	private final @Getter List<Integer> entries;
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
		values = Arrays.asList(attrs.getTextArray(R.styleable.ColorSelectionPreference_cp_entryValues));

		int entriesId = attrs.getResourceId(R.styleable.ColorSelectionPreference_cp_entryValues, 0);
		int[] entriesArray = prefView.context.getResources().getIntArray(entriesId);
		entries = new ArrayList<>();
		for (int entry : entriesArray) entries.add(entry);
	}
}
