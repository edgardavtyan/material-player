package com.edavtyan.materialplayer.service;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.player.effects.amplifier.AmplifierFactory;
import com.edavtyan.materialplayer.player.effects.bassboost.BassBoostFactory;
import com.edavtyan.materialplayer.player.effects.equalizer.EqualizerFactory;
import com.edavtyan.materialplayer.player.effects.surround.SurroundFactory;
import com.edavtyan.materialplayer.notification.PlayerNotificationCompatFactory;
import com.edavtyan.materialplayer.notification.PlayerNotificationFactory;
import com.edavtyan.materialplayer.notification.PlayerNotificationNougatFactory;
import com.edavtyan.materialplayer.service.receivers.ReceiversFactory;
import com.edavtyan.materialplayer.player.PlayerFactory;

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
