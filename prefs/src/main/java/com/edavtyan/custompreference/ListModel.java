package com.edavtyan.custompreference;

import android.content.Context;

import java.util.List;

public abstract class ListModel extends BaseModel {
	public ListModel(Context context) {
		super(context);
	}

	public abstract CharSequence getSummary();

	public abstract List<CharSequence> getEntries();

	public abstract List<CharSequence> getValues();

	public boolean getPrefSelectedAtIndex(int position) {
		return getValues().get(position).equals(getCurrentPreference());
	}

	public void savePref(CharSequence value) {
		sharedPrefs.edit().putString(getKey().toString(), value.toString()).apply();
	}
}
