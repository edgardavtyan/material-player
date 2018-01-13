package com.edavtyan.materialplayer.modular.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public abstract class ModularActivity extends AppCompatActivity {
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

	protected void addModule(ActivityModule module) {
		modules.add(module);
		module.onCreate();
	}
}
