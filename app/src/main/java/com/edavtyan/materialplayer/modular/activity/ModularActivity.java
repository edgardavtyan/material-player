package com.edavtyan.materialplayer.modular.activity;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.edavtyan.materialplayer.lib.theme.ThemeableScreen;
import com.edavtyan.materialplayer.modular.universal_view.UniversalViewModule;

import java.util.ArrayList;

public abstract class ModularActivity extends AppCompatActivity implements ThemeableScreen {
	private final ArrayList<UniversalViewModule> modules = new ArrayList<>();

	private @Nullable Bundle savedInstanceState;

	@Override
	@CallSuper
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.savedInstanceState = savedInstanceState;
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

	protected void callModulesOnBackPressed() {
		for (UniversalViewModule module : modules) {
			if (module instanceof ActivityModule) {
				((ActivityModule) module).onBackPressed();
			}
		}
	}

	protected void addModule(UniversalViewModule module) {
		modules.add(module);
		module.onCreate(savedInstanceState);
	}
}
