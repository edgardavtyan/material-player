package com.edavtyan.materialplayer.lib.base;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.audioeffects.AudioEffectsActivity2;
import com.edavtyan.materialplayer.components.prefs.PrefActivity;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.utils.ThemeUtils;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public abstract class BaseActivity
		extends AppCompatActivity
		implements SharedPreferences.OnSharedPreferenceChangeListener {

	private ThemeUtils themeUtils;

	public abstract int getLayoutId();

	@SuppressWarnings("unchecked")
	public <T> T findView(int id) {
		return (T) findViewById(id);
	}

	/* AppCompatActivity */

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SharedPreferences basePrefs = PreferenceManager.getDefaultSharedPreferences(this);
		AdvancedSharedPrefs prefs = new AdvancedSharedPrefs(basePrefs);
		themeUtils = new ThemeUtils(prefs);
		themeUtils.setTheme(this);

		setContentView(getLayoutId());

		getDefaultSharedPreferences(this)
				.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.menu_base, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_effects:
			startActivity(new Intent(this, AudioEffectsActivity2.class));
			return true;

		case R.id.menu_settings:
			startActivity(new Intent(this, PrefActivity.class));
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	/* SharedPreferences.OnSharedPreferenceChangeListener */

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		themeUtils.setThemeAndRecreate(this, key);
	}
}
