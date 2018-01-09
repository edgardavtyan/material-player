package com.edavtyan.materialplayer.components;

import android.content.Context;

import com.edavtyan.materialplayer.components.detail.lib.CompactDetailPref;
import com.edavtyan.materialplayer.components.lists.lib.CompactListPref;
import com.edavtyan.materialplayer.components.main.CompactMainScreenPref;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CompactPrefsModule {
	@Provides
	@Singleton
	public CompactListPref provideCompactListPref(Context context, AdvancedSharedPrefs prefs) {
		return new CompactListPref(context, prefs);
	}

	@Provides
	@Singleton
	public CompactDetailPref provideCompactDetailPref(Context context, AdvancedSharedPrefs prefs) {
		return new CompactDetailPref(context, prefs);
	}

	@Provides
	@Singleton
	public CompactMainScreenPref provideCompactMainScreenPref(Context context, AdvancedSharedPrefs prefs) {
		return new CompactMainScreenPref(context, prefs);
	}
}
