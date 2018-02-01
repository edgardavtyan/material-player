package com.edavtyan.materialplayer.modular.activity;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.lib.theme.ThemeableScreen;
import com.edavtyan.materialplayer.modular.universal_view.UniversalViewModule;

import java.util.ArrayList;

public abstract class ModularActivity extends AppCompatActivity implements ThemeableScreen {
	private final ArrayList<UniversalViewModule> modules = new ArrayList<>();

	@Override
	@CallSuper
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
	}

	@Override
	@CallSuper
	public void onStart() {
		super.onStart();

		for (UniversalViewModule module : modules) {
			module.onStart();
		}
	}

	@Override
	@CallSuper
	public void onStop() {
		super.onStop();

		for (UniversalViewModule module : modules) {
			module.onStop();
		}
	}

	@Override
	@CallSuper
	public boolean onCreateOptionsMenu(Menu menu) {
		for (UniversalViewModule module : modules) {
			module.onCreateOptionsMenu(menu);
		}

		return true;
	}

	@Override
	@CallSuper
	public boolean onOptionsItemSelected(MenuItem item) {
		for (UniversalViewModule module : modules) {
			module.onOptionsItemSelected(item);
		}

		return false;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		callModulesOnBackPressed();
	}

	@Override
	@CallSuper
	public void onThemeChanged(ThemeColors colors) {
		for (UniversalViewModule module : modules) {
			module.onThemeChanged(colors);
		}
	}

	protected void callModulesOnBackPressed() {
		for (UniversalViewModule module : modules) {
			if (module instanceof ActivityModule) {
				((ActivityModule)module).onBackPressed();
			}
		}
	}

	protected void addModule(UniversalViewModule module) {
		modules.add(module);
		module.onCreate();
	}
}
