package com.edavtyan.materialplayer;

import android.app.Application;
import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.lib.theme.Theme;
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

	public Theme getAppTheme() {
		String themeStr = PreferenceManager.getDefaultSharedPreferences(this)
										   .getString(getString(R.string.pref_colorsMain_key), "Colored");

		if (themeStr.equals("Colored")) return Theme.COLORED;
		if (themeStr.equals("White")) return Theme.WHITE;
		if (themeStr.equals("Black")) return Theme.BLACK;
		return Theme.COLORED;
	}
}
