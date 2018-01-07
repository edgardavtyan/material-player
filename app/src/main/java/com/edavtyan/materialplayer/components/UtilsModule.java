package com.edavtyan.materialplayer.components;

import android.content.Context;

import com.edavtyan.materialplayer.components.detail.lib.CompactDetailPref;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {
	@Provides
	@Singleton
	public Navigator provideNavigator(Context context, CompactDetailPref compactDetailPref) {
		return new Navigator(context, compactDetailPref);
	}
}
