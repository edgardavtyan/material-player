package com.edavtyan.materialplayer.components.prefs.views.customlist;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.preference.PreferenceManager;
import android.util.AttributeSet;

import com.edavtyan.materialplayer.R;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public abstract class ListPreferenceManager {
	protected final Context context;
	protected final SharedPreferences prefs;
	protected final @Getter String preferenceKey;
	protected final @Getter String defaultPreference;
	protected final @Getter List<CharSequence> entries;
	protected final @Getter List<CharSequence> summaries;
	protected final @Getter List<CharSequence> values;

	//---

	@SuppressWarnings("ResourceType")
	public ListPreferenceManager(Context context, AttributeSet attributeSet) {
		this.context = context;
		prefs = PreferenceManager.getDefaultSharedPreferences(context);

		TypedArray typedArray = null;
		try {
			typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.SummaryListPref);
			preferenceKey = typedArray.getString(R.styleable.SummaryListPref_key);
			defaultPreference = typedArray.getString(R.styleable.SummaryListPref_defaultValue);
			values = Arrays.asList(typedArray.getTextArray(R.styleable.SummaryListPref_values));
			entries = Arrays.asList(typedArray.getTextArray(R.styleable.SummaryListPref_entries));
			summaries = Arrays.asList(typedArray.getTextArray(R.styleable.SummaryListPref_summaries));

		} finally {
			if (typedArray != null) {
				typedArray.recycle();
			}
		}
	}

	//---

	public String getSelectedPreference() {
		return prefs.getString(preferenceKey, defaultPreference);
	}

	public void savePreference(String preference) {
		prefs.edit().putString(preferenceKey, preference).apply();
	}
}
