package com.edavtyan.materialplayer.modular.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import com.edavtyan.materialplayer.lib.testable.TestableActivity;

import java.util.ArrayList;

public class ModularActivity extends TestableActivity {
	private ArrayList<ActivityModule> modules = new ArrayList<>();

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onResume() {
		super.onResume();

		for (ActivityModule module : modules) {
			module.onCreate();
		}
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
	}
}
