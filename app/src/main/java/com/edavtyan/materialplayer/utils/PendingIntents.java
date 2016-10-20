package com.edavtyan.materialplayer.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;


public class PendingIntents {
	private final Context context;

	public PendingIntents(Context context) {
		this.context = context;
	}

	public PendingIntent getActivity(Class<? extends AppCompatActivity> activityClass) {
		Intent intent = new Intent(context, activityClass);
		intent.setAction(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		return PendingIntent.getActivity(context, 0, intent, 0);
	}
}
