package com.edavtyan.materialplayer.app.activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.edavtyan.materialplayer.app.R;

public class PrefActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs_main);
    }
}
