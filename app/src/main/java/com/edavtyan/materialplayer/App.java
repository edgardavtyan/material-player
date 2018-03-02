package com.edavtyan.materialplayer;

import android.app.Application;

import com.edavtyan.materialplayer.service.DaggerPlayerServiceComponent;
import com.edavtyan.materialplayer.service.PlayerServiceComponent;

import lombok.Setter;

public class App extends Application {
	private @Setter PlayerServiceComponent playerServiceComponent;
	private @Setter AppDIComponent appComponent;

	@SuppressWarnings("ConstantConditions")
	public PlayerServiceComponent getPlayerServiceComponent() {
		if (playerServiceComponent != null) {
			return playerServiceComponent;
		} else {
			return DaggerPlayerServiceComponent
					.builder()
					.appDIComponent(getAppComponent())
					.build();
		}
	}

	public AppDIComponent getAppComponent() {
		if (appComponent == null) {
			appComponent = DaggerAppDIComponent
					.builder()
					.appDIModule(new AppDIModule(this))
					.build();
		}

		return appComponent;
	}
}
