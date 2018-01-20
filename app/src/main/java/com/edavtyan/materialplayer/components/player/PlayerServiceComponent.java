package com.edavtyan.materialplayer.components.player;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.components.audio_effects.amplifier.AmplifierFactory;
import com.edavtyan.materialplayer.components.audio_effects.bassboost.BassBoostFactory;
import com.edavtyan.materialplayer.components.audio_effects.equalizer.EqualizerFactory;
import com.edavtyan.materialplayer.components.audio_effects.surround.SurroundFactory;
import com.edavtyan.materialplayer.components.notification.PlayerNotificationCompatFactory;
import com.edavtyan.materialplayer.components.notification.PlayerNotificationFactory;
import com.edavtyan.materialplayer.components.notification.PlayerNotificationNougatFactory;
import com.edavtyan.materialplayer.components.player.receivers.ReceiversFactory;

import dagger.Component;

@PlayerServiceScope
@Component(
		dependencies = AppComponent.class,
		modules = {
		PlayerFactory.class,
		EqualizerFactory.class,
		AmplifierFactory.class,
		BassBoostFactory.class,
		SurroundFactory.class,
		ReceiversFactory.class,
		PlayerNotificationCompatFactory.class,
		PlayerNotificationNougatFactory.class,
		PlayerNotificationFactory.class})
public interface PlayerServiceComponent {
	void inject(PlayerService service);
}
