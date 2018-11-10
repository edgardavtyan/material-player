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
		getAppComponent().inject(this);
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

	public int getThemeRes() {
		if (colors.getThemeStr().equals("Colored")) {
			return colors.getTheme2();
		}

		if (colors.getThemeStr().equals("White")) {
			return R.style.AppTheme;
		}

		if (colors.getThemeStr().equals("Black")) {
			return R.style.AppTheme_Dark;
		}

		return colors.getTheme2();
	}

	public int getThemeTranslucentRes() {
		if (colors.getThemeStr().equals("Colored")) {
			return colors.getTheme2translucent();
		}

		if (colors.getThemeStr().equals("White")) {
			return R.style.AppTheme_Translucent;
		}

		if (colors.getThemeStr().equals("Black")) {
			return R.style.AppTheme_Dark_Translucent;
		}

		return colors.getTheme2();
	}
}
