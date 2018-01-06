package com.edavtyan.materialplayer.modular.activity.modules;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.modular.activity.ActivityModule;

public class ActivityBaseMenuModule extends ActivityModule {
	private final Navigator navigator;
	private final MenuInflater menuInflater;

	public ActivityBaseMenuModule(Navigator navigator, MenuInflater menuInflater) {
		this.navigator = navigator;
		this.menuInflater = menuInflater;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu) {
		menuInflater.inflate(R.menu.menu_base, menu);
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
