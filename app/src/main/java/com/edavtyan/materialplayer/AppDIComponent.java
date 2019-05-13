package com.edavtyan.materialplayer;

import android.content.Context;

import com.edavtyan.materialplayer.db.DbDIModule;
import com.edavtyan.materialplayer.db.MediaDB;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtDIModule;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer.lib.lyrics.LyricsDIModule;
import com.edavtyan.materialplayer.lib.lyrics.LyricsProvider;
import com.edavtyan.materialplayer.lib.music_api.MusicApi;
import com.edavtyan.materialplayer.lib.music_api.MusicApiDIModule;
import com.edavtyan.materialplayer.lib.playlist.models.PlaylistManager;
import com.edavtyan.materialplayer.lib.playlist.models.PlaylistModelsDIModule;
import com.edavtyan.materialplayer.lib.prefs.AdvancedGsonSharedPrefs;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsDIModule;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer.lib.transition.SharedTransitionDIModule;
import com.edavtyan.materialplayer.lib.transition.SharedTransitionsManager;
import com.edavtyan.materialplayer.ui.Navigator;
import com.edavtyan.materialplayer.ui.SdkFactory;
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
		MusicApiDIModule.class,
		LyricsDIModule.class,
		DbDIModule.class,
		AlbumArtDIModule.class,
		SharedTransitionDIModule.class,
		PlaylistModelsDIModule.class})
public interface AppDIComponent {
	Context context();
	SdkFactory sdkFactory();
	MusicApi musicAPi();
	LyricsProvider lyricsProvider();
	AdvancedSharedPrefs advancedPrefs();
	AdvancedGsonSharedPrefs advancedGsonPrefs();
	Navigator navigator();
	WebClient webClient();
	TestableBitmapFactory testableBitmapFactory();
	PendingIntents pendingIntents();
	MediaDB mediaDB();
	AlbumArtProvider albumArtProvider();
	Gson gson();
	SharedTransitionsManager sharedTransitionsManager();
	PlaylistManager playlistManager();
}
