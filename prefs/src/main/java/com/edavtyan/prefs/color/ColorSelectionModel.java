package com.edavtyan.prefs.color;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.preference.PreferenceManager;
import android.util.AttributeSet;

import com.edavtyan.prefs.R;
import com.edavtyan.prefs.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

import lombok.Cleanup;
import lombok.Getter;

public class ColorSelectionModel {
	private final SharedPreferences prefs;

	private final @Getter String key;
	private final @Getter String title;
	private final @Getter int defaultValue;
	private final @Getter List<Integer> entries;
	private final @Getter List<Integer> values;

	public ColorSelectionModel(Context context, AttributeSet attributeSet) {
		prefs = PreferenceManager.getDefaultSharedPreferences(context);

		@Cleanup("recycle")
		TypedArray attrs = context.obtainStyledAttributes(attributeSet, R.styleable.ColorSelectionPreference);
		key = attrs.getString(R.styleable.ColorSelectionPreference_cp_key);
		title = attrs.getString(R.styleable.ColorSelectionPreference_cp_title);
		defaultValue = attrs.getInt(R.styleable.ColorSelectionPreference_cp_defaultValue, 0);
		entries = getEntries(context, attrs);

		int valuesId = attrs.getResourceId(R.styleable.ColorSelectionPreference_cp_entryValues, 0);
		values = ArrayUtils.asIntList(context.getResources().getIntArray(valuesId));
	}

	public int getSelectedPrefIndex() {
		return values.indexOf(prefs.getInt(key, defaultValue));
	}

	public int getCurrentColor() {
		return entries.get(getSelectedPrefIndex());
	}

	public void savePref(int position) {
		prefs.edit().putInt(key, values.get(position)).apply();
	}

	private List<Integer> getEntries(Context context, TypedArray attrs) {
		int entriesId = attrs.getResourceId(R.styleable.ColorSelectionPreference_cp_entries, 0);
		int[] entriesArray = context.getResources().getIntArray(entriesId);
		List<Integer> entries = new ArrayList<>();
		for (int entry : entriesArray) entries.add(entry);
		return entries;
	}
}
