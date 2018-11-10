package com.edavtyan.materialplayer.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;

public abstract class BaseActivityTransparent extends BaseActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(((App)getApplication()).getThemeTranslucentRes());
	}
}