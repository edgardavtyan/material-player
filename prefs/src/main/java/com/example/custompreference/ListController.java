package com.example.custompreference;

import android.content.Context;

import java.util.List;

public abstract class ListController extends SummaryController {

	public ListController(Context context) {
		super(context);
	}


	public abstract List<CharSequence> getEntries();
	public abstract List<CharSequence> getValues();


	public boolean getPrefSelectedAtIndex(int position) {
		return getValues().get(position).equals(getCurrentPreference());
	}

	public void savePref(CharSequence value) {
		prefs.edit().putString(getKey().toString(), value.toString()).apply();
	}
}
