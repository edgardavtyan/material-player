package com.edavtyan.materialplayer.modular;

import android.view.Menu;
import android.view.MenuItem;

import com.edavtyan.materialplayer.lib.testable.TestableActivity;

import java.util.ArrayList;

public class ModularActivity extends TestableActivity {
	private ArrayList<ActivityModule> modules = new ArrayList<>();

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return modules.get(modules.size() - 1).onCreateOptionsMenu(menu);
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
