package com.edavtyan.materialplayer.service;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.notification.PlayerNotificationCompatDIModule;
import com.edavtyan.materialplayer.notification.PlayerNotificationNougatDIModule;
import com.edavtyan.materialplayer.player.effects.amplifier.AmplifierFactory;
import com.edavtyan.materialplayer.player.effects.bassboost.BassBoostFactory;
import com.edavtyan.materialplayer.player.effects.equalizer.EqualizerFactory;
import com.edavtyan.materialplayer.player.effects.surround.SurroundFactory;
import com.edavtyan.materialplayer.notification.PlayerNotificationFactory;
import com.edavtyan.materialplayer.service.receivers.ReceiversFactory;
import com.edavtyan.materialplayer.player.PlayerDIModule;

import dagger.Component;

@PlayerServiceScope
@Component(
		dependencies = AppDIComponent.class,
		modules = {
		PlayerDIModule.class,
		EqualizerFactory.class,
		AmplifierFactory.class,
		BassBoostFactory.class,
		SurroundFactory.class,
		ReceiversFactory.class,
		PlayerNotificationCompatDIModule.class,
		PlayerNotificationNougatDIModule.class,
		PlayerNotificationFactory.class})
public interface PlayerServiceComponent {
	void inject(PlayerService service);
}
