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
	protected @Getter final String preferenceKey;
	protected @Getter final String defaultPreference;
	protected @Getter final List<CharSequence> entries;
	protected @Getter final List<CharSequence> summaries;
	protected @Getter final List<CharSequence> values;
	
	//---

	@SuppressWarnings("ResourceType")
	public ListPreferenceManager(Context context, AttributeSet attributeSet) {
		this.context = context;
		prefs = PreferenceManager.getDefaultSharedPreferences(context);

		int[] attrs = new int[] {
				android.R.attr.key,
				android.R.attr.defaultValue,
				android.R.attr.entryValues,
				R.attr.summaries,
				android.R.attr.entries,
		};
		TypedArray typedArray = null;
		try {
			typedArray = context.obtainStyledAttributes(attributeSet, attrs);
			preferenceKey = typedArray.getString(0);
			defaultPreference = typedArray.getString(1);
			values = Arrays.asList(typedArray.getTextArray(2));
			summaries = Arrays.asList(typedArray.getTextArray(3));
			entries = Arrays.asList(typedArray.getTextArray(4));
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
