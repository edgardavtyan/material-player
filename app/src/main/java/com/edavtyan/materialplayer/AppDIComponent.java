package com.edavtyan.materialplayer;

import android.content.Context;

import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.db.DbDIModule;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtDIModule;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer.lib.lastfm.LastFmDIModule;
import com.edavtyan.materialplayer.lib.lastfm.LastfmApi;
import com.edavtyan.materialplayer.lib.playlist.models.PlaylistModelsFactory;
import com.edavtyan.materialplayer.lib.prefs.AdvancedGsonSharedPrefs;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsDIModule;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer.transition.SharedTransitionDIModule;
import com.edavtyan.materialplayer.transition.SharedTransitionsManager;
import com.edavtyan.materialplayer.ui.Navigator;
import com.edavtyan.materialplayer.ui.SdkFactory;
import com.edavtyan.materialplayer.lib.playlist.models.PlaylistManager;
import com.edavtyan.materialplayer.utils.PendingIntents;
import com.edavtyan.materialplayer.utils.UtilsDIModule;
import com.edavtyan.materialplayer.utils.WebClient;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
		AppDIModule.class,
		AdvancedSharedPrefsDIModule.class,
		UtilsDIModule.class,
		LastFmDIModule.class,
		DbDIModule.class,
		AlbumArtDIModule.class,
		SharedTransitionDIModule.class,
		PlaylistModelsFactory.class})
public interface AppDIComponent {
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
	Gson gson();
	SharedTransitionsManager sharedTransitionsManager();
	PlaylistManager playlistManager();
}
