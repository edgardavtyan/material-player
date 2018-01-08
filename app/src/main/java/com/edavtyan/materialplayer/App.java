package com.edavtyan.materialplayer;

import android.app.Activity;
import android.app.Application;

import com.edavtyan.materialplayer.components.SdkFactory;
import com.edavtyan.materialplayer.components.UtilsModule;
import com.edavtyan.materialplayer.components.audio_effects.amplifier.AmplifierModule;
import com.edavtyan.materialplayer.components.audio_effects.bassboost.BassBoostModule;
import com.edavtyan.materialplayer.components.audio_effects.equalizer.EqualizerModule;
import com.edavtyan.materialplayer.components.audio_effects.surround.SurroundModule;
import com.edavtyan.materialplayer.components.notification.PlayerNotificationCompatModule;
import com.edavtyan.materialplayer.components.notification.PlayerNotificationModule;
import com.edavtyan.materialplayer.components.notification.PlayerNotificationNougatModule;
import com.edavtyan.materialplayer.components.player.DaggerPlayerServiceComponent;
import com.edavtyan.materialplayer.components.player.PlayerModule;
import com.edavtyan.materialplayer.components.player.PlayerService;
import com.edavtyan.materialplayer.components.player.PlayerServiceComponent;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtModule;
import com.edavtyan.materialplayer.lib.base.BaseFactory;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsModule;

import lombok.Setter;

public class App extends Application {

	private @Setter BaseFactory baseFactory;
	private @Setter SdkFactory sdkFactory;

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
}
