package com.edavtyan.materialplayer.utils;

import android.util.Log;

import java.util.Locale;

public class Logger {
	public static void d(Object caller, String message, Object... params) {
		Log.d(caller.getClass().getSimpleName(), String.format(Locale.getDefault(), message, params));
	}
}
