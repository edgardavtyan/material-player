package com.edavtyan.materialplayer.app.views.lib.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.views.audioeffects.AudioEffectsActivity;
import com.edavtyan.materialplayer.app.views.PrefActivity;
import com.edavtyan.materialplayer.app.utils.ThemeUtils;

public class BaseActivity
		extends AppCompatActivity
		implements SharedPreferences.OnSharedPreferenceChangeListener {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		setTheme(ThemeUtils.fromRes(this));
		super.onCreate(savedInstanceState);

		PreferenceManager
				.getDefaultSharedPreferences(this)
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
			startActivity(new Intent(this, AudioEffectsActivity.class));
			return true;

		case R.id.menu_settings:
			startActivity(new Intent(this, PrefActivity.class));
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		ThemeUtils.setTheme(this, key);
	}
}
