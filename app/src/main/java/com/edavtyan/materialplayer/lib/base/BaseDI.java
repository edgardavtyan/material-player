package com.edavtyan.materialplayer.lib.base;

import android.content.Context;

import com.edavtyan.materialplayer.components.Navigator;

public abstract class BaseDI {
	private final Context context;
	private Navigator navigator;

	public BaseDI(Context context) {
		this.context = context;
	}

	public Navigator provideNavigator() {
		if (navigator == null) navigator = new Navigator(context);
		return navigator;
	}
}
