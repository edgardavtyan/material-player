package com.edavtyan.materialplayer.lib.base;

import android.content.Context;

import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.utils.ArtProvider2;

public abstract class BaseFactory {
	private final Context context;
	private Navigator navigator;
	private ArtProvider2 artProvider;

	public BaseFactory(Context context) {
		this.context = context;
	}

	public Context provideContext() {
		return context;
	}

	public Navigator provideNavigator() {
		if (navigator == null) navigator = new Navigator(context);
		return navigator;
	}

	public ArtProvider2 provideArtProvider() {
		if (artProvider == null)
			artProvider = new ArtProvider2();
		return artProvider;
	}
}
