package com.edavtyan.custompreference.checkbox;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.preference.PreferenceManager;
import android.util.AttributeSet;

import com.edavtyan.custompreference.R;

import lombok.Cleanup;
import lombok.Getter;

public class CheckboxPreferenceModel {
	private final SharedPreferences prefs;
	private final @Getter String key;
	private final @Getter String title;
	private final @Getter Boolean defaultValue;

	public CheckboxPreferenceModel(Context context, AttributeSet attributeSet) {
		prefs = PreferenceManager.getDefaultSharedPreferences(context);

		@Cleanup("recycle")
		TypedArray attrs = context.obtainStyledAttributes(attributeSet, R.styleable.CheckboxPref);
		key = attrs.getString(R.styleable.CheckboxPref_cp_key);
		title = attrs.getString(R.styleable.CheckboxPref_cp_title);
		defaultValue = attrs.getBoolean(R.styleable.CheckboxPref_cp_defaultValue, false);
	}

	public void toggleChecked() {
		boolean currentPref = getChecked();
		prefs.edit().putBoolean(key, !currentPref).apply();
	}

	public boolean getChecked() {
		return prefs.getBoolean(key, defaultValue);
	}
}
