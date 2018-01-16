package com.edavtyan.materialplayer.modular.activity.modules;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.modular.activity.ActivityModule;

public class ActivityBaseMenuModule extends ActivityModule {
	private final Activity activity;
	private final Navigator navigator;

	public ActivityBaseMenuModule(Activity activity, Navigator navigator) {
		this.activity = activity;
		this.navigator = navigator;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu) {
		activity.getMenuInflater().inflate(R.menu.menu_base, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_effects:
			navigator.gotoAudioEffects();
			return true;
		case R.id.menu_settings:
			navigator.gotoSettings();
			return true;
		case R.id.menu_search:
			navigator.gotoSearch();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
