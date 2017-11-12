package com.edavtyan.materialplayer.components.audioeffects.models;

import com.edavtyan.materialplayer.lib.prefs.AdvancedGsonSharedPrefs;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EqualizerPresetsPrefs {
	private static final String PREF_BUILT_IN = "presets_built_in";
	private static final String PREF_CUSTOM = "presets_custom";
	private static final int DEFAULT_BUILT_IN = 3;

	private final AdvancedGsonSharedPrefs prefs;
	private final Type type;

	public EqualizerPresetsPrefs(AdvancedGsonSharedPrefs prefs) {
		this.prefs = prefs;
		this.type = new TypeToken<List<CustomPreset>>() {}.getType();
	}

	public void saveCurrentBuiltInPreset(int index) {
		prefs.edit().putInt(PREF_BUILT_IN, index).apply();
	}

	public int getCurrentBuiltInPreset() {
		return prefs.getInt(PREF_BUILT_IN, DEFAULT_BUILT_IN);
	}

	public void addNewCustomPreset(String name, int[] gains) throws PresetNameAlreadyExists {
		List<CustomPreset> customPresets = prefs.getJsonAsList(PREF_CUSTOM, type, new ArrayList<>());

		for (CustomPreset customPreset : customPresets) {
			if (customPreset.getName().equals(name)) {
				throw new PresetNameAlreadyExists(name);
			}
		}

		customPresets.add(new CustomPreset(name, gains));
		prefs.edit().putListAsJson(PREF_CUSTOM, customPresets).apply();
	}

	public List<String> getCustomPresetsNames() {
		List<CustomPreset> customPresets = prefs.getJsonAsList(PREF_CUSTOM, type, new ArrayList<>());
		List<String> presetsNames = new ArrayList<>();
		for (int i = 0; i < customPresets.size(); i++) {
			presetsNames.add(customPresets.get(i).getName());
		}

		return presetsNames;
	}

	public void deleteCustomPreset(int position) {
		List<CustomPreset> customPresets = prefs.getJsonAsList(PREF_CUSTOM, type, new ArrayList<>());
		customPresets.remove(position);
		prefs.edit().putListAsJson(PREF_CUSTOM, customPresets).apply();
	}

	public CustomPreset getCustomPresetAtIndex(int presetIndex) {
		List<CustomPreset> customPresets = prefs.getJsonAsList(PREF_CUSTOM, type, new ArrayList<>());
		return customPresets.get(presetIndex);
	}
}
