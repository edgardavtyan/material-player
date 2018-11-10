package com.edavtyan.materialplayer.base;

import com.edavtyan.materialplayer.R;

public abstract class BaseActivityTransparent extends BaseActivity {
	@Override
	protected int getThemeNormal() {
		return R.style.AppTheme_Transparent;
	}

	@Override
	protected int getThemeDark() {
		return R.style.AppTheme_Dark_Transparent;
	}
}