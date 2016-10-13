package com.edavtyan.materialplayer.lib.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class TestDBHelper extends SQLiteOpenHelper {
	public TestDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public abstract String getTableName();

	@Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion != newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + getTableName());
			onCreate(db);
		}
	}

	public Cursor query(String[] projection, String selection, String[] args, String order) {
		return getWritableDatabase().query(getTableName(), projection, selection, args, null, null, order);
	}

	public void reset() {
		getWritableDatabase().delete(getTableName(), null, null);
	}
}
