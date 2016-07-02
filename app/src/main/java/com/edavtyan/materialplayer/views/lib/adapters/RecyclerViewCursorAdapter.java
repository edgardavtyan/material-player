package com.edavtyan.materialplayer.views.lib.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;

public abstract class RecyclerViewCursorAdapter<VH extends RecyclerView.ViewHolder>
		extends RecyclerView.Adapter<VH> {
	protected final Context context;
	protected Cursor cursor;

	public RecyclerViewCursorAdapter(Context context, Cursor cursor) {
		this.context = context;
		this.cursor = cursor;
	}

	/*
	 * RecyclerView.Adapter
	 */

	@Override
	public void onBindViewHolder(VH holder, int position) {
		cursor.moveToPosition(position);
		String a = "123456";
	}

	@Override
	public int getItemCount() {
		if (cursor == null) return 0;
		return cursor.getCount();
	}

	/*
	 * Public methods
	 */

	public void swapCursor(Cursor newCursor) {
		if (cursor == newCursor) return;
		cursor = newCursor;
		notifyDataSetChanged();
	}
}
