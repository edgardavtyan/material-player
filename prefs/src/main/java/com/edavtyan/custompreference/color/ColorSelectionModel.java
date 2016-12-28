package com.edavtyan.custompreference.color;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.edavtyan.custompreference.R;
import com.edavtyan.custompreference.base.BaseModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Cleanup;
import lombok.Getter;

public class ColorSelectionModel extends BaseModel {
	private final @Getter CharSequence key;
	private final @Getter CharSequence title;
	private final @Getter CharSequence defaultValue;
	private final @Getter List<Integer> entries;
	private final @Getter List<CharSequence> values;

	public ColorSelectionModel(Context context, AttributeSet attributeSet) {
		super(context);

		@Cleanup("recycle")
		@SuppressLint("recycle")
		TypedArray attrs = context.obtainStyledAttributes(
				attributeSet, R.styleable.ColorSelectionPreference);
		key = attrs.getText(R.styleable.ColorSelectionPreference_cp_key);
		title = attrs.getText(R.styleable.ColorSelectionPreference_cp_title);
		defaultValue = attrs.getText(R.styleable.ColorSelectionPreference_cp_defaultValue);
		entries = getEntries(context, attrs);
		values = Arrays.asList(attrs.getTextArray(R.styleable.ColorSelectionPreference_cp_entryValues));
	}

	public int getSelectedPrefIndex() {
		return values.indexOf(sharedPrefs.getString(key.toString(), defaultValue.toString()));
	}

	public int getCurrentColor() {
		return entries.get(getSelectedPrefIndex());
	}

	public void savePref(int position) {
		sharedPrefs.edit().putString(key.toString(), values.get(position).toString()).apply();
	}

	private List<Integer> getEntries(Context context, TypedArray attrs) {
		int entriesId = attrs.getResourceId(R.styleable.ColorSelectionPreference_cp_entries, 0);
		int[] entriesArray = context.getResources().getIntArray(entriesId);
		List<Integer> entries = new ArrayList<>();
		for (int entry : entriesArray) entries.add(entry);
		return entries;
	}
}
