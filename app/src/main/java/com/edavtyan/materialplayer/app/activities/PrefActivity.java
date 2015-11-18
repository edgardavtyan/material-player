package com.edavtyan.materialplayer.app.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.utils.ThemeUtils;

public class PrefActivity
        extends PreferenceActivity
        implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(ThemeUtils.fromRes(this));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pref_toolbar);
        addPreferencesFromResource(R.xml.prefs_main);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.prefs_title);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        ThemeUtils.setTheme(this, key);
    }
}
