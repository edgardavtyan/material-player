package com.edavtyan.materialplayer.components.prefs.views.summarylist;

import android.content.Context;
import android.util.AttributeSet;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.prefs.views.customlist.ListPreference;
import com.edavtyan.materialplayer.components.prefs.views.customlist.ListPreferenceAdapter;
import com.edavtyan.materialplayer.components.prefs.views.customlist.ListPreferenceManager;

public class SummaryListPreference extends ListPreference {
	public SummaryListPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SummaryListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	//---

	@Override
	public ListPreferenceAdapter getAdapter(ListPreferenceManager manager) {
		return new SummaryListAdapter(getContext(), manager);
	}

	@Override
	public ListPreferenceManager getManager(AttributeSet attributeSet) {
		return new SummaryListManager(getContext(), attributeSet);
	}

	@Override
	public int getLayoutId() {
		return R.layout.pref_list_summary;
	}
}
