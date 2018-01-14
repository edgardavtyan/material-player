package com.edavtyan.materialplayer.modular.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.lib.theme.ThemeModularScreen;
import com.edavtyan.materialplayer.modular.universal_view.UniversalViewModule;

import java.util.ArrayList;

public abstract class ModularActivity extends AppCompatActivity implements ThemeModularScreen {
	private final ArrayList<UniversalViewModule> modules = new ArrayList<>();

	@Override
	protected void onPostCreate(@Nullable Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
	}

	@Override
	public void onStart() {
		super.onStart();

		for (UniversalViewModule module : modules) {
			module.onStart();
		}
	}

	@Override
	public void onStop() {
		super.onStop();

		for (UniversalViewModule module : modules) {
			module.onStop();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		for (UniversalViewModule module : modules) {
			module.onCreateOptionsMenu(menu);
		}

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		for (UniversalViewModule module : modules) {
			module.onOptionsItemSelected(item);
		}

		return false;
	}

	@Override
	public void onThemeChanged(ThemeColors colors) {
		for (UniversalViewModule module : modules) {
			module.onThemeChanged(colors);
		}
	}

	protected void addModule(UniversalViewModule module) {
		modules.add(module);
		module.onCreate();
	}
}
