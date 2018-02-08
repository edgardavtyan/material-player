package com.edavtyan.materialplayer;

import android.content.Context;

import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.db.DbFactory;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtFactory;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer.lib.lastfm.LastFmFactory;
import com.edavtyan.materialplayer.lib.lastfm.LastfmApi;
import com.edavtyan.materialplayer.lib.prefs.AdvancedGsonSharedPrefs;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsFactory;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer.screens.Navigator;
import com.edavtyan.materialplayer.screens.SdkFactory;
import com.edavtyan.materialplayer.screens.detail.lib.CompactDetailPref;
import com.edavtyan.materialplayer.screens.lists.lib.CompactListPref;
import com.edavtyan.materialplayer.screens.main.CompactMainScreenPref;
import com.edavtyan.materialplayer.transition.SharedTransitionDaggerModule;
import com.edavtyan.materialplayer.transition.SharedTransitionsManager;
import com.edavtyan.materialplayer.utils.PendingIntents;
import com.edavtyan.materialplayer.utils.UtilsFactory;
import com.edavtyan.materialplayer.utils.WebClient;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
		AppFactory.class,
		AdvancedSharedPrefsFactory.class,
		UtilsFactory.class,
		LastFmFactory.class,
		DbFactory.class,
		AlbumArtFactory.class,
		CompactPrefsModule.class,
		SharedTransitionDaggerModule.class})
public interface AppComponent {
	Context context();
	SdkFactory sdkFactory();
	LastfmApi lastFmApi();
	AdvancedSharedPrefs advancedPrefs();
	AdvancedGsonSharedPrefs advancedGsonPrefs();
	Navigator navigator();
	WebClient webClient();
	TestableBitmapFactory testableBitmapFactory();
	PendingIntents pendingIntents();
	ArtistDB artistDB();
	AlbumDB albumDB();
	TrackDB trackDB();
	AlbumArtProvider albumArtProvider();
	CompactMainScreenPref compactMainScreenPref();
	CompactListPref compactListPref();
	CompactDetailPref compactDetailPref();
	Gson gson();
	SharedTransitionsManager sharedTransitionsManager();
}
