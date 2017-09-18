package com.edavtyan.materialplayer.modular.activity;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;

public class ActivityBaseMenuModule extends ActivityModule {
	private final AppCompatActivity activity;
	private final Navigator navigator;

	public ActivityBaseMenuModule(AppCompatActivity activity, Navigator navigator) {
		this.activity = activity;
		this.navigator = navigator;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = new MenuInflater(activity);
		menuInflater.inflate(R.menu.menu_base, menu);
		return true;
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
