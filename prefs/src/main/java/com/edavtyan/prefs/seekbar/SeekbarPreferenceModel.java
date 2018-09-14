package com.edavtyan.prefs.seekbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.preference.PreferenceManager;
import android.util.AttributeSet;

import com.edavtyan.prefs.R;

import lombok.Cleanup;
import lombok.Getter;

public class SeekbarPreferenceModel {
	private final SharedPreferences prefs;
	private final @Getter String key;
	private final @Getter String title;
	private final @Getter String format;
	private final @Getter float multiplier;
	private final @Getter int defaultValue;
	private final @Getter int maxValue;

	public SeekbarPreferenceModel(Context context, AttributeSet attributeSet) {
		prefs = PreferenceManager.getDefaultSharedPreferences(context);

		@Cleanup("recycle")
		@SuppressLint("Recycle")
		TypedArray attrs = context.obtainStyledAttributes(attributeSet, R.styleable.SeekbarPref);
		key = attrs.getString(R.styleable.SeekbarPref_cp_key);
		title = attrs.getString(R.styleable.SeekbarPref_cp_title);
		format = attrs.getString(R.styleable.SeekbarPref_cp_format);
		multiplier = attrs.getFloat(R.styleable.SeekbarPref_cp_multiplier, 1);
		defaultValue = attrs.getInt(R.styleable.SeekbarPref_cp_defaultValue, 0);
		maxValue = attrs.getInt(R.styleable.SeekbarPref_cp_maxValue, 100);
	}

	public void setValue(int value) {
		prefs.edit().putInt(key, value).apply();
	}

	public int getSeek() {
		return prefs.getInt(key, defaultValue);
	}
}
