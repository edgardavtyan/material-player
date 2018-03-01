package com.edavtyan.materialplayer.db;

public class CursorIsNullException extends IllegalStateException {
	public CursorIsNullException() {
		super("Cursor returned by content provider is null");
	}
}
