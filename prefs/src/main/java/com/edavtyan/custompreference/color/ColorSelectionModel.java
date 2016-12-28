package com.edavtyan.custompreference.color;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.edavtyan.custompreference.R;
import com.edavtyan.custompreference.base.BaseModel;
import com.edavtyan.custompreference.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

import lombok.Cleanup;
import lombok.Getter;

public class ColorSelectionModel extends BaseModel {
	private final @Getter String key;
	private final @Getter String title;
	private final @Getter String defaultValue;
	private final @Getter List<Integer> entries;
	private final @Getter List<String> values;

	public ColorSelectionModel(Context context, AttributeSet attributeSet) {
		super(context);

		@Cleanup("recycle")
		TypedArray attrs = context.obtainStyledAttributes(attributeSet, R.styleable.ColorSelectionPref);
		key = attrs.getString(R.styleable.ColorSelectionPref_cp_key);
		title = attrs.getString(R.styleable.ColorSelectionPref_cp_title);
		defaultValue = attrs.getString(R.styleable.ColorSelectionPref_cp_defaultValue);
		entries = getEntries(context, attrs);
		values = ArrayUtils.asStringList(attrs.getTextArray(R.styleable.ColorSelectionPref_cp_entryValues));
	}

	public int getSelectedPrefIndex() {
		return values.indexOf(sharedPrefs.getString(key, defaultValue));
	}

	public int getCurrentColor() {
		return entries.get(getSelectedPrefIndex());
	}

	public void savePref(int position) {
		sharedPrefs.edit().putString(key, values.get(position)).apply();
	}

	private List<Integer> getEntries(Context context, TypedArray attrs) {
		int entriesId = attrs.getResourceId(R.styleable.ColorSelectionPref_cp_entries, 0);
		int[] entriesArray = context.getResources().getIntArray(entriesId);
		List<Integer> entries = new ArrayList<>();
		for (int entry : entriesArray) entries.add(entry);
		return entries;
	}
}
