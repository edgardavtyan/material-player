package com.edavtyan.materialplayer.modular.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import com.edavtyan.materialplayer.lib.theme.ThemeActivity;
import com.edavtyan.materialplayer.utils.ThemeColors;

import java.util.ArrayList;

public abstract class ModularActivity extends ThemeActivity {
	private final ArrayList<ActivityModule> modules = new ArrayList<>();

	@Override
	protected void onPostCreate(@Nullable Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
	}

	@Override
	public void onStart() {
		super.onStart();

		for (ActivityModule module : modules) {
			module.onStart();
		}
	}

	@Override
	public void onStop() {
		super.onStop();

		for (ActivityModule module : modules) {
			module.onStop();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		for (ActivityModule module : modules) {
			module.onCreateOptionsMenu(menu);
		}

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		for (ActivityModule module : modules) {
			module.onOptionsItemSelected(item);
		}

		return false;
	}

	@Override
	public void onThemeChanged(ThemeColors colors) {
		for (ActivityModule module : modules) {
			module.onThemeChanged(colors);
		}
	}

	protected void addModule(ActivityModule module) {
		modules.add(module);
		module.onCreate();
	}
}
