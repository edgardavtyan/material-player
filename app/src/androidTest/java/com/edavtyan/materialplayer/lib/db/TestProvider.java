package com.edavtyan.materialplayer.lib.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class TestProvider<TDB extends TestDBHelper> extends ContentProvider {
	private final Class<TDB> dbClass;
	protected TDB db;

	public TestProvider(Class<TDB> dbClass) {
		this.dbClass = dbClass;
	}

	@SuppressWarnings("unchecked")
	@Override public boolean onCreate() {
		try {
			db = (TDB) dbClass.getDeclaredConstructors()[0].newInstance(getContext());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	@Nullable
	@Override public Cursor query(@NonNull Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		return db.query(projection, selection, selectionArgs, sortOrder);
	}

	@Nullable
	@Override public String getType(@NonNull Uri uri) {
		return null;
	}

	@Nullable
	@Override public Uri insert(@NonNull Uri uri, ContentValues values) {
		return null;
	}

	@Override public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		return 0;
	}
}
