package com.edavtyan.materialplayer.components.player;

import com.edavtyan.materialplayer.components.audio_effects.amplifier.AmplifierFactory;
import com.edavtyan.materialplayer.components.audio_effects.bassboost.BassBoostFactory;
import com.edavtyan.materialplayer.components.audio_effects.surround.SurroundFactory;
import com.edavtyan.materialplayer.components.player.receivers.ReceiversFactory;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtFactory;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsFactory;
import com.edavtyan.materialplayer.utils.UtilsFactory;
import com.edavtyan.materialplayer.components.audio_effects.equalizer.EqualizerFactory;
import com.edavtyan.materialplayer.components.notification.PlayerNotificationCompatFactory;
import com.edavtyan.materialplayer.components.notification.PlayerNotificationFactory;
import com.edavtyan.materialplayer.components.notification.PlayerNotificationNougatFactory;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
		PlayerFactory.class,
		EqualizerFactory.class,
		AmplifierFactory.class,
		BassBoostFactory.class,
		SurroundFactory.class,
		ReceiversFactory.class,
		PlayerNotificationCompatFactory.class,
		PlayerNotificationNougatFactory.class,
		PlayerNotificationFactory.class,
		AlbumArtFactory.class,
		AdvancedSharedPrefsFactory.class,
		UtilsFactory.class})
public interface PlayerServiceComponent {
	void inject(PlayerService service);
}
