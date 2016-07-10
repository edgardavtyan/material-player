package com.edavtyan.materialplayer.components.prefs.views.customlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import lombok.Setter;

public abstract class ListPreferenceAdapter<VH extends RecyclerView.ViewHolder>
		extends RecyclerView.Adapter<VH> {
	protected final Context context;
	protected final ListPreferenceManager manager;
	protected @Setter OnItemClickListener onItemClickListener;

	//---

	public interface OnItemClickListener {
		void onPreferenceSelected(int position);
	}

	//---

	public ListPreferenceAdapter(Context context, ListPreferenceManager manager) {
		this.context = context;
		this.manager = manager;
	}

	//---

	protected void raiseOnItemClickListener(int position) {
		if (onItemClickListener != null) {
			onItemClickListener.onPreferenceSelected(position);
		}
	}
}
