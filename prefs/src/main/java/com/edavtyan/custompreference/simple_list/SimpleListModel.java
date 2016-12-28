package com.edavtyan.custompreference.simple_list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.preference.PreferenceManager;
import android.util.AttributeSet;

import com.edavtyan.custompreference.R;
import com.edavtyan.custompreference.base.ListModel;

import java.util.Arrays;
import java.util.List;

import lombok.Cleanup;
import lombok.Getter;

public class SimpleListModel extends ListModel {
	private final SharedPreferences prefs;
	private final @Getter CharSequence key;
	private final @Getter CharSequence title;
	private final @Getter CharSequence summary;
	private final @Getter CharSequence defaultValue;
	private final @Getter List<CharSequence> entries;
	private final @Getter List<CharSequence> values;

	public SimpleListModel(Context context, AttributeSet attributeSet) {
		super(context);

		prefs = PreferenceManager.getDefaultSharedPreferences(context);

		@Cleanup("recycle")
		@SuppressLint("Recycle")
		TypedArray attrs = context.obtainStyledAttributes(
				attributeSet, R.styleable.SimpleListPreference);
		key = attrs.getString(R.styleable.SimpleListPreference_cp_key);
		title = attrs.getString(R.styleable.SimpleListPreference_cp_title);
		summary = attrs.getString(R.styleable.SimpleListPreference_cp_summary);
		defaultValue = attrs.getString(R.styleable.SimpleListPreference_cp_defaultValue);
		entries = Arrays.asList(attrs.getTextArray(R.styleable.SimpleListPreference_cp_entries));
		values = Arrays.asList(attrs.getTextArray(R.styleable.SimpleListPreference_cp_entryValues));
	}
}
