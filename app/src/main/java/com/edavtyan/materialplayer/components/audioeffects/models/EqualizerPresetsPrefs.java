package com.edavtyan.materialplayer.components.audioeffects.models;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EqualizerPresetsPrefs {
	private static final String PREF_BUILT_IN = "presets_built_in";
	private static final String PREF_CUSTOM = "presets_custom";
	private static final int DEFAULT_BUILT_IN = 3;
	private static final String DEFAULT_CUSTOM = "";

	private final AdvancedSharedPrefs prefs;
	private final Gson gson;

	public EqualizerPresetsPrefs(AdvancedSharedPrefs prefs) {
		this.prefs = prefs;
		this.gson = new Gson();
	}

	public void saveCurrentBuiltInPreset(int index) {
		prefs.edit().putInt(PREF_BUILT_IN, index).apply();
	}

	public int getCurrentBuiltInPreset() {
		return prefs.getInt(PREF_BUILT_IN, DEFAULT_BUILT_IN);
	}

	public void addNewCustomPreset(String name, int bandsCount) {
		String customPresetsJson = prefs.getString(PREF_CUSTOM, DEFAULT_CUSTOM);
		List<CustomPreset> customPresets = gson.fromJson(customPresetsJson, new TypeToken<List<CustomPreset>>(){}.getType());

		if (customPresets == null) {
			customPresets = new ArrayList<>();
		}

		customPresets.add(new CustomPreset(name, bandsCount));
		String newCustomPresetsJson = gson.toJson(customPresets);
		prefs.edit().putString(PREF_CUSTOM, newCustomPresetsJson).apply();
	}

	public List<String> getCustomPresetsNames() {
		String customPresetsJson = prefs.getString(PREF_CUSTOM, DEFAULT_CUSTOM);
		CustomPreset[] customPresets = gson.fromJson(customPresetsJson, CustomPreset[].class);
		String[] presetsNames = new String[customPresets.length];
		for (int i = 0; i < customPresets.length; i++) {
			presetsNames[i] = customPresets[i].getName();
		}

		return Arrays.asList(presetsNames);
	}
}
