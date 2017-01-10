package com.edavtyan.materialplayer.lib.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaMetadataRetriever;
import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtFileStorage;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtMemoryCache;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtReader;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer.utils.ThemeUtils;
import com.edavtyan.materialplayer.utils.WebClient;

public class BaseFactory {
	private final Context context;
	private Navigator navigator;
	private AlbumArtProvider albumArtProvider;
	private TestableBitmapFactory bitmapFactory;
	private AlbumArtFileStorage dataStorage;
	private AlbumArtMemoryCache memoryCache;
	private WebClient webClient;
	private AlbumArtReader albumArtReader;
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

	public AlbumArtProvider provideArtProvider() {
		if (albumArtProvider == null)
			albumArtProvider = new AlbumArtProvider(
					provideArtFileStorage(),
					provideArtMemoryCache(),
					provideMusicTagReader(),
					provideBitmapFactory());
		return albumArtProvider;
	}

	public AlbumArtReader provideMusicTagReader() {
		if (albumArtReader == null)
			albumArtReader = new AlbumArtReader(new MediaMetadataRetriever());
		return albumArtReader;
	}

	public AlbumArtMemoryCache provideArtMemoryCache() {
		if (memoryCache == null)
			memoryCache = new AlbumArtMemoryCache();
		return memoryCache;
	}

	public AlbumArtFileStorage provideArtFileStorage() {
		if (dataStorage == null)
			dataStorage = new AlbumArtFileStorage();
		return dataStorage;
	}

	public WebClient providerWebClient() {
		if (webClient == null)
			webClient = new WebClient();
		return webClient;
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
