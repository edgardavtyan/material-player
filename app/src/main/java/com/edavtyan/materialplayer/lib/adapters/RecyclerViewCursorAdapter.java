package com.edavtyan.materialplayer.lib.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.lib.models.CursorDB;

public abstract class RecyclerViewCursorAdapter<VH extends RecyclerView.ViewHolder>
		extends RecyclerView.Adapter<VH> {
	protected final Context context;

	public RecyclerViewCursorAdapter(Context context) {
		this.context = context;
	}

	/*
	 * RecyclerView.Adapter
	 */

	@Override
	public int getItemCount() {
		return getDB().getCount();
	}

	/*
	 * Public methods
	 */

	public void swapCursor(Cursor newCursor) {
		getDB().swapCursor(newCursor);
		notifyDataSetChanged();
	}

	/*
	 * Abstract methods
	 */

	public abstract CursorDB getDB();
}
