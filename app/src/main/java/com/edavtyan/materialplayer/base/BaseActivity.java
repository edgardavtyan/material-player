package com.edavtyan.materialplayer.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.modular.activity.ModularActivity;

public abstract class BaseActivity extends ModularActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ThemeColors theme = new ThemeColors(this);
		setTheme(theme.getThemeRes());
	}

	public void disableTouchEvents() {
		getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
				WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
	}

	public void enableTouchEvents() {
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
	}
}