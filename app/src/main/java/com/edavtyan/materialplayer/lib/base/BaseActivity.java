package com.edavtyan.materialplayer.lib.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ed.libsutils.WindowUtils;
import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.lib.theme.ThemeSwitchModule;
import com.edavtyan.materialplayer.modular.activity.ModularActivity;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityBaseMenuModule;

import butterknife.ButterKnife;

public abstract class BaseActivity
		extends ModularActivity {

	public abstract int getLayoutId();

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		BaseFactory factory = ((App) getApplication()).getBaseFactory(this);
		addModule(new ActivityBaseMenuModule(factory.getNavigator(), factory.createMenuInflater(this)));
		addModule(new ThemeSwitchModule(this, this, factory.getPrefs()));

		setContentView(getLayoutId());

		ButterKnife.bind(this);

		if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
			WindowUtils.makeStatusBarSemiTransparent(this);
		}
	}
}
