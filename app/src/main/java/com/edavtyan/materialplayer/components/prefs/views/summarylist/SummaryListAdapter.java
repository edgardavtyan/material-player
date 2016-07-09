package com.edavtyan.materialplayer.components.prefs.views.summarylist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.prefs.views.customlist.ListPreferenceAdapter;
import com.edavtyan.materialplayer.components.prefs.views.customlist.ListPreferenceManager;

public class SummaryListAdapter
		extends ListPreferenceAdapter<SummaryListViewHolder>
		implements SummaryListViewHolder.OnHolderClickListener {
	public SummaryListAdapter(Context context, ListPreferenceManager manager) {
		super(context, manager);
	}

	//---

	@Override
	public SummaryListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater
				.from(context)
				.inflate(R.layout.listitem_pref_list_summary, parent, false);
		SummaryListViewHolder holder = new SummaryListViewHolder(itemView);
		holder.setOnHolderClickListener(this);
		return holder;
	}

	@Override
	public void onBindViewHolder(SummaryListViewHolder holder, int position) {
		holder.setTitle(manager.getEntries().get(position));
		holder.setSummary(manager.getSummaries().get(position));

		String selectedPref = manager.getSelectedPreference();
		String currentPref = manager.getEntries().get(position).toString();
		holder.setChecked(selectedPref.equals(currentPref));
	}

	@Override
	public int getItemCount() {
		return manager.getEntries().size();
	}

	@Override
	public void onHolderClick(int position) {
		raiseOnItemClickListener(position);
	}
}
