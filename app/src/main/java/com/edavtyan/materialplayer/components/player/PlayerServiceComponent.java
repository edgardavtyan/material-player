package com.edavtyan.materialplayer.components.player;

import com.edavtyan.materialplayer.utils.UtilsModule;
import com.edavtyan.materialplayer.components.audio_effects.amplifier.AmplifierModule;
import com.edavtyan.materialplayer.components.audio_effects.bassboost.BassBoostModule;
import com.edavtyan.materialplayer.components.audio_effects.equalizer.EqualizerModule;
import com.edavtyan.materialplayer.components.audio_effects.surround.SurroundModule;
import com.edavtyan.materialplayer.components.notification.PlayerNotificationCompatModule;
import com.edavtyan.materialplayer.components.notification.PlayerNotificationModule;
import com.edavtyan.materialplayer.components.notification.PlayerNotificationNougatModule;
import com.edavtyan.materialplayer.components.player.receivers.ReceiversModule;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtModule;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
		PlayerModule.class,
		EqualizerModule.class,
		AmplifierModule.class,
		BassBoostModule.class,
		SurroundModule.class,
		ReceiversModule.class,
		PlayerNotificationCompatModule.class,
		PlayerNotificationNougatModule.class,
		PlayerNotificationModule.class,
		AlbumArtModule.class,
		AdvancedSharedPrefsModule.class,
		UtilsModule.class})
public interface PlayerServiceComponent {
	void inject(PlayerService service);
}
