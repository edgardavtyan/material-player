package com.edavtyan.materialplayer.base;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Window;
import android.view.WindowManager;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.modular.activity.ModularActivity;

public abstract class BaseActivity extends ModularActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
	}

	@Override
	public Resources.Theme getTheme() {
		Resources.Theme theme = super.getTheme();

		switch (((App) getApplication()).getAppTheme()) {
		case COLORED:
			theme.applyStyle(getThemeNormal(), true);
			break;
		case WHITE:
			theme.applyStyle(getThemeNormal(), true);
			break;
		case BLACK:
			theme.applyStyle(getThemeDark(), true);
			break;
		}

		return theme;
	}

	@StyleRes
	protected int getThemeNormal() {
		return R.style.AppTheme;
	}

	@StyleRes
	protected int getThemeDark() {
		return R.style.AppTheme_Transparent;
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