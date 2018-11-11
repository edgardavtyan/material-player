package com.edavtyan.materialplayer;

import android.app.Application;

import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.service.DaggerPlayerServiceComponent;
import com.edavtyan.materialplayer.service.PlayerServiceComponent;

import javax.inject.Inject;

import lombok.Setter;

public class App extends Application {
	private @Setter PlayerServiceComponent playerServiceComponent;
	private @Setter AppDIComponent appComponent;

	@Inject ThemeColors colors;

	@Override
	public void onCreate() {
		super.onCreate();
	}

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
