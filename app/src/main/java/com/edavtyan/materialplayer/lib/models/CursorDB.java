package com.edavtyan.materialplayer.lib.models;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;

public abstract class CursorDB {
	protected final Context context;
	protected final ContentResolver resolver;
	protected Cursor cursor;

	/*
	 * Constructors
	 */

	public CursorDB(Context context) {
		this.context = context;
		resolver = context.getContentResolver();
	}

	/*
	 * Public methods
	 */

	public void swapCursor(Cursor newCursor) {
		cursor = newCursor;
	}

	public int getCount() {
		if (cursor == null) return 0;
		return cursor.getCount();
	}
}
