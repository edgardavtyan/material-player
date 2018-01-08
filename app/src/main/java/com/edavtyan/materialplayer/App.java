package com.edavtyan.materialplayer;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.edavtyan.materialplayer.components.SdkFactory;
import com.edavtyan.materialplayer.components.UtilsModule;
import com.edavtyan.materialplayer.components.audio_effects.amplifier.AmplifierModule;
import com.edavtyan.materialplayer.components.audio_effects.bassboost.BassBoostModule;
import com.edavtyan.materialplayer.components.audio_effects.equalizer.EqualizerModule;
import com.edavtyan.materialplayer.components.audio_effects.surround.SurroundModule;
import com.edavtyan.materialplayer.components.main.MainActivity;
import com.edavtyan.materialplayer.components.main.MainFactory;
import com.edavtyan.materialplayer.components.notification.PlayerNotificationCompatModule;
import com.edavtyan.materialplayer.components.notification.PlayerNotificationModule;
import com.edavtyan.materialplayer.components.notification.PlayerNotificationNougatModule;
import com.edavtyan.materialplayer.components.player.DaggerPlayerServiceComponent;
import com.edavtyan.materialplayer.components.player.PlayerModule;
import com.edavtyan.materialplayer.components.player.PlayerService;
import com.edavtyan.materialplayer.components.player.PlayerServiceComponent;
import com.edavtyan.materialplayer.components.search.album.SearchAlbumFactory;
import com.edavtyan.materialplayer.components.search.album.SearchAlbumFragment;
import com.edavtyan.materialplayer.components.search.artist.SearchArtistFactory;
import com.edavtyan.materialplayer.components.search.artist.SearchArtistFragment;
import com.edavtyan.materialplayer.components.search.tracks.SearchTrackFactory;
import com.edavtyan.materialplayer.components.search.tracks.SearchTrackFragment;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtModule;
import com.edavtyan.materialplayer.lib.base.BaseFactory;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsModule;

import lombok.Setter;

public class App extends Application {

	private @Setter BaseFactory baseFactory;
	private @Setter SdkFactory sdkFactory;
	private @Setter MainFactory mainFactory;
	private @Setter SearchArtistFactory searchArtistFactory;
	private @Setter SearchAlbumFactory searchAlbumFactory;
	private @Setter SearchTrackFactory searchTrackFactory;

	private @Setter PlayerServiceComponent playerServiceComponent;

	@Override
	public void onCreate() {
		super.onCreate();
	}

	public PlayerServiceComponent getPlayerServiceComponent(PlayerService service) {
		if (playerServiceComponent != null) {
			return playerServiceComponent;
		} else {
			return DaggerPlayerServiceComponent
					.builder()
					.playerModule(new PlayerModule(service))
					.equalizerModule(new EqualizerModule())
					.amplifierModule(new AmplifierModule())
					.bassBoostModule(new BassBoostModule())
					.surroundModule(new SurroundModule())
					.playerNotificationCompatModule(new PlayerNotificationCompatModule())
					.playerNotificationNougatModule(new PlayerNotificationNougatModule())
					.playerNotificationModule(new PlayerNotificationModule())
					.albumArtModule(new AlbumArtModule())
					.advancedSharedPrefsModule(new AdvancedSharedPrefsModule())
					.utilsModule(new UtilsModule())
					.build();
		}
	}

	public BaseFactory getBaseFactory(Activity activity) {
		return (baseFactory == null)
				? new BaseFactory(activity)
				: baseFactory;
	}

	public SdkFactory getSdkFactory() {
		return (sdkFactory == null)
				? new SdkFactory()
				: sdkFactory;
	}

	public SearchArtistFactory getSearchArtistFactory(Context context, SearchArtistFragment view) {
		return (searchArtistFactory == null)
				? new SearchArtistFactory(context, view)
				: searchArtistFactory;
	}

	public SearchAlbumFactory getSearchAlbumFactory(Context context, SearchAlbumFragment view) {
		return (searchAlbumFactory == null)
				? new SearchAlbumFactory(context, view)
				: searchAlbumFactory;
	}

	public SearchTrackFactory getSearchTrackFactory(Context context, SearchTrackFragment view) {
		return (searchTrackFactory == null)
				? new SearchTrackFactory(context, view)
				: searchTrackFactory;
	}

	public MainFactory getMainFactory(MainActivity activity) {
		return (mainFactory == null) ? new MainFactory(activity) : mainFactory;
	}
}
