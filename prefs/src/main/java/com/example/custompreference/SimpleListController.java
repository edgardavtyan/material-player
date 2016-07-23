package com.example.custompreference;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import java.util.Arrays;
import java.util.List;

import lombok.Cleanup;
import lombok.Getter;

public class SimpleListController extends ListController<SimpleListPreference> {
	private final @Getter CharSequence key;
	private final @Getter CharSequence title;
	private final @Getter CharSequence summary;
	private final @Getter CharSequence defaultValue;
	private final @Getter List<CharSequence> entries;
	private final @Getter List<CharSequence> values;


	public SimpleListController(SimpleListPreference preference, AttributeSet attributeSet) {
		super(preference);

		@Cleanup("recycle")
		@SuppressLint("Recycle")
		TypedArray attrs = preference.context.obtainStyledAttributes(
				attributeSet, R.styleable.SimpleListPreference);
		key = attrs.getString(R.styleable.SimpleListPreference_cp_key);
		title = attrs.getString(R.styleable.SimpleListPreference_cp_title);
		summary = attrs.getString(R.styleable.SimpleListPreference_cp_summary);
		defaultValue = attrs.getString(R.styleable.SimpleListPreference_cp_defaultValue);
		entries = Arrays.asList(attrs.getTextArray(R.styleable.SimpleListPreference_cp_entries));
		values = Arrays.asList(attrs.getTextArray(R.styleable.SimpleListPreference_cp_entryValues));
	}
}
