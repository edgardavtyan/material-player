package com.edavtyan.custompreference;

import java.util.List;

public abstract class ListController<TPreference extends ListPreference>
		extends SummaryController<TPreference> {

	public ListController(TPreference preference) {
		super(preference);
	}


	public abstract List<CharSequence> getEntries();
	public abstract List<CharSequence> getValues();


	public boolean getPrefSelectedAtIndex(int position) {
		return getValues().get(position).equals(getCurrentPreference());
	}

	public void savePref(CharSequence value) {
		sharedPrefs.edit().putString(getKey().toString(), value.toString()).apply();
	}
}
