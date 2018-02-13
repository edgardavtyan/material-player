package com.edavtyan.materialplayer;

import android.content.Context;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.screens.detail.lib.CompactDetailPref;
import com.edavtyan.materialplayer.screens.lists.lib.CompactListPref;

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
}
