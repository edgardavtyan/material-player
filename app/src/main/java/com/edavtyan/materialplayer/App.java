package com.edavtyan.materialplayer;

import android.app.Activity;
import android.app.Application;

import com.edavtyan.materialplayer.components.SdkFactory;
import com.edavtyan.materialplayer.components.player.DaggerPlayerServiceComponent;
import com.edavtyan.materialplayer.components.player.PlayerModule;
import com.edavtyan.materialplayer.components.player.PlayerService;
import com.edavtyan.materialplayer.components.player.PlayerServiceComponent;
import com.edavtyan.materialplayer.lib.base.BaseFactory;

import lombok.Setter;

public class App extends Application {

	private @Setter BaseFactory baseFactory;
	private @Setter SdkFactory sdkFactory;

	private @Setter PlayerServiceComponent playerServiceComponent;

	public PlayerServiceComponent getPlayerServiceComponent(PlayerService service) {
		if (playerServiceComponent != null) {
			return playerServiceComponent;
		} else {
			return DaggerPlayerServiceComponent
					.builder()
					.playerModule(new PlayerModule(service))
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
