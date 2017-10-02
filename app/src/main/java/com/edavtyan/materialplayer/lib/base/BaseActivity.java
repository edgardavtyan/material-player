package com.edavtyan.materialplayer.lib.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.modular.activity.ModularActivity;
import com.edavtyan.materialplayer.modular.activity.ActivityBaseMenuModule;
import com.edavtyan.materialplayer.modular.activity.ActivityThemeSwitchModule;
import com.edavtyan.materialplayer.utils.WindowUtils;

import butterknife.ButterKnife;

public abstract class BaseActivity extends ModularActivity {

	public abstract int getLayoutId();

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		App app = (App) getApplicationContext();

		BaseFactory factory = app.getBaseFactory(this);

		addModule(new ActivityThemeSwitchModule(this, factory.getPrefs(), factory.getThemeUtils()));
		addModule(new ActivityBaseMenuModule(this, factory.getNavigator()));

		setContentView(getLayoutId());

		ButterKnife.bind(this);

		if (Build.VERSION.SDK_INT == 19) {
			WindowUtils.makeStatusBarSemiTransparent(this);
		}
	}
}
