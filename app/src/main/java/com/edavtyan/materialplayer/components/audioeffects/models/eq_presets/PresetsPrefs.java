package com.edavtyan.materialplayer.components.audioeffects.models.eq_presets;

import com.edavtyan.materialplayer.components.audioeffects.models.Equalizer;
import com.edavtyan.materialplayer.lib.prefs.AdvancedGsonSharedPrefs;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PresetsPrefs {
	private static final String PREF_INDEX = "presets_built_in";
	private static final String PREF_CUSTOM = "presets_custom";
	private static final String PREF_TYPE = "presets_type";
	private static final int DEFAULT_INDEX = 0;

	private final AdvancedGsonSharedPrefs prefs;
	private final Type type;

	public PresetsPrefs(AdvancedGsonSharedPrefs prefs) {
		this.prefs = prefs;
		this.type = new TypeToken<List<CustomPreset>>() {}.getType();
	}

	public void saveCurrentPresetIndex(int index) {
		prefs.edit().putInt(PREF_INDEX, index).apply();
	}

	public int getCurrentPresetIndex() {
		return prefs.getInt(PREF_INDEX, DEFAULT_INDEX);
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

	public void deleteCustomPreset(int position) {
		List<CustomPreset> customPresets = prefs.getJsonAsList(PREF_CUSTOM, type, new ArrayList<>());
		customPresets.remove(position);
		prefs.edit().putListAsJson(PREF_CUSTOM, customPresets).apply();
	}

	public List<String> getCustomPresets() {
		List<CustomPreset> customPresets = prefs.getJsonAsList(PREF_CUSTOM, type, new ArrayList<>());
		List<String> presetsNames = new ArrayList<>();
		for (int i = 0; i < customPresets.size(); i++) {
			presetsNames.add(customPresets.get(i).getName());
		}

		return presetsNames;
	}

	public CustomPreset getCustomPresetAtIndex(int presetIndex) {
		List<CustomPreset> customPresets = prefs.getJsonAsList(PREF_CUSTOM, type, new ArrayList<>());
		return customPresets.get(presetIndex);
	}

	public Equalizer.PresetType getCurrentPresetType() {
		return prefs.getEnum(PREF_TYPE, Equalizer.PresetType.BUILT_IN);
	}

	public void saveCurrentPresetType(Equalizer.PresetType type) {
		prefs.edit().putEnum(PREF_TYPE, type).apply();
	}
}
