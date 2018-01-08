package com.edavtyan.materialplayer.lib.base;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaMetadataRetriever;
import android.preference.PreferenceManager;
import android.view.MenuInflater;

import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.detail.lib.CompactDetailPref;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtFileStorage;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtMemoryCache;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtReader;
import com.edavtyan.materialplayer.lib.prefs.AdvancedGsonSharedPrefs;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.utils.ThemeUtils;
import com.edavtyan.materialplayer.utils.WebClient;
import com.google.gson.Gson;

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
	private AdvancedGsonSharedPrefs advancedGsonSharedPrefs;
	private SharedPreferences basePrefs;
	private CompactDetailPref compactDetailPref;
	private ModelServiceModule modelServiceModule;
	private Gson gson;

	public BaseFactory(Context context) {
		this.context = context;
	}

	public Context getContext() {
		return context;
	}

	public Navigator getNavigator() {
		if (navigator == null) navigator = new Navigator(context);
		return navigator;
	}

	public AlbumArtProvider getArtProvider() {
		if (albumArtProvider == null)
			albumArtProvider = new AlbumArtProvider(
					getArtFileStorage(),
					getArtMemoryCache(),
					getMusicTagReader(),
					getBitmapFactory());
		return albumArtProvider;
	}

	public AlbumArtReader getMusicTagReader() {
		if (albumArtReader == null)
			albumArtReader = new AlbumArtReader(new MediaMetadataRetriever());
		return albumArtReader;
	}

	public AlbumArtMemoryCache getArtMemoryCache() {
		if (memoryCache == null)
			memoryCache = new AlbumArtMemoryCache();
		return memoryCache;
	}

	public AlbumArtFileStorage getArtFileStorage() {
		if (dataStorage == null)
			dataStorage = new AlbumArtFileStorage(getContext());
		return dataStorage;
	}

	public WebClient getWebClient() {
		if (webClient == null)
			webClient = new WebClient();
		return webClient;
	}

	public TestableBitmapFactory getBitmapFactory() {
		if (bitmapFactory == null)
			bitmapFactory = new TestableBitmapFactory();
		return bitmapFactory;
	}

	public ThemeUtils getThemeUtils() {
		if (themeUtils == null)
			themeUtils = new ThemeUtils(getPrefs());
		return themeUtils;
	}

	public AdvancedSharedPrefs getPrefs() {
		if (prefs == null)
			prefs = new AdvancedSharedPrefs(getBasePrefs());
		return prefs;
	}

	public AdvancedGsonSharedPrefs getAdvancedGsonSharedPrefs() {
		if (advancedGsonSharedPrefs == null)
			advancedGsonSharedPrefs = new AdvancedGsonSharedPrefs(getBasePrefs(), getGson());
		return advancedGsonSharedPrefs;
	}

	public SharedPreferences getBasePrefs() {
		if (basePrefs == null)
			basePrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
		return basePrefs;
	}

	public CompactDetailPref getCompactDetailPref() {
		if (compactDetailPref == null)
			compactDetailPref = new CompactDetailPref(getContext());
		return compactDetailPref;
	}

	public ModelServiceModule getModelServiceModule() {
		if (modelServiceModule == null)
			modelServiceModule = new ModelServiceModule(getContext());
		return modelServiceModule;
	}

	public Gson getGson() {
		if (gson == null)
			gson = new Gson();
		return gson;
	}

	public MenuInflater createMenuInflater(Activity activity) {
		return new MenuInflater(activity);
	}
}
