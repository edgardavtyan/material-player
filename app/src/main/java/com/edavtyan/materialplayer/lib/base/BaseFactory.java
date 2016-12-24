package com.edavtyan.materialplayer.lib.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaMetadataRetriever;
import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer.utils.ArtProvider2;
import com.edavtyan.materialplayer.utils.DataStorage;
import com.edavtyan.materialplayer.utils.ThemeUtils;
import com.edavtyan.materialplayer.utils.tag.MusicTagReader;
import com.edavtyan.materialplayer.utils.tag.VanillaMusicTagReader;

public class BaseFactory {
	private final Context context;
	private Navigator navigator;
	private ArtProvider2 artProvider;
	private TestableBitmapFactory bitmapFactory;
	private DataStorage dataStorage;
	private MusicTagReader musicTagReader;
	private ThemeUtils themeUtils;
	private AdvancedSharedPrefs prefs;
	private SharedPreferences basePrefs;

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
			artProvider = new ArtProvider2(provideDataStorage(), provideMusicTagReader());
		return artProvider;
	}

	public MusicTagReader provideMusicTagReader() {
		if (musicTagReader == null)
			musicTagReader = new VanillaMusicTagReader(new MediaMetadataRetriever());
		return musicTagReader;
	}

	public DataStorage provideDataStorage() {
		if (dataStorage == null)
			dataStorage = new DataStorage();
		return dataStorage;
	}

	public TestableBitmapFactory provideBitmapFactory() {
		if (bitmapFactory == null)
			bitmapFactory = new TestableBitmapFactory();
		return bitmapFactory;
	}

	public ThemeUtils provideThemeUtils() {
		if (themeUtils == null)
			themeUtils = new ThemeUtils(providePrefs());
		return themeUtils;
	}

	public AdvancedSharedPrefs providePrefs() {
		if (prefs == null)
			prefs = new AdvancedSharedPrefs(provideBasePrefs());
		return prefs;
	}

	public SharedPreferences provideBasePrefs() {
		if (basePrefs == null)
			basePrefs = PreferenceManager.getDefaultSharedPreferences(provideContext());
		return basePrefs;
	}
}
