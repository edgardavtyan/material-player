package com.edavtyan.materialplayer.components.prefs.views.summarylist;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.R;

import lombok.Setter;

public class SummaryListAdapter
		extends RecyclerView.Adapter<SummaryListViewHolder>
		implements SummaryListViewHolder.OnHolderClickListener {
	private final Context context;
	private final SummaryListAttributes attrs;
	private final SharedPreferences prefs;
	private final String prefKey;
	private final String preFDefault;
	private @Setter OnItemSelectedListener onItemSelectedListener;

	//---

	public interface OnItemSelectedListener {
		void onItemSelected();
	}

	//---

	public SummaryListAdapter(Context context, SummaryListAttributes attributes) {
		this.context = context;
		this.attrs = attributes;
		this.prefs = PreferenceManager.getDefaultSharedPreferences(context);
		prefKey = context.getString(R.string.pref_audio_engine_key);
		preFDefault = context.getString(R.string.pref_audio_engine_default);
	}

	//---

	public String getSelectedItem() {
		return prefs.getString(prefKey, preFDefault);
	}

	//---

	@Override
	public SummaryListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater
				.from(context)
				.inflate(R.layout.listitem_pref_list_summary, parent, false);

		SummaryListViewHolder holder = new SummaryListViewHolder(view);
		holder.setOnHolderClickListener(this);
		return holder;
	}

	@Override
	public int getItemCount() {
		return attrs.getEntries().size();
	}

	@Override
	public void onBindViewHolder(SummaryListViewHolder holder, int position) {
		holder.setTitle(attrs.getEntries().get(position));
		holder.setSummary(attrs.getSummaries().get(position));
		holder.setChecked(true);

		String selectedPref = prefs.getString(prefKey, preFDefault);
		holder.setChecked(position == attrs.getValues().indexOf(selectedPref));
	}

	@Override
	public void onHolderClick(SummaryListViewHolder holder) {
		CharSequence selectedPref = attrs.getValues().get(holder.getAdapterPosition());
		prefs.edit().putString(prefKey, selectedPref.toString()).apply();
		if (onItemSelectedListener != null) {
			onItemSelectedListener.onItemSelected();
		}
	}
}
