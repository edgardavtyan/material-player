package com.edavtyan.materialplayer.player.effects.equalizer;

import com.edavtyan.materialplayer.lib.prefs.AdvancedGsonSharedPrefs;
import com.edavtyan.materialplayer.screens.audio_effects.presets.CustomPreset;
import com.edavtyan.materialplayer.screens.audio_effects.presets.PresetNameAlreadyExists;
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
		List<CustomPreset> customPresets = getPresetsFromPrefs();

		for (CustomPreset customPreset : customPresets) {
			if (customPreset.getName().equals(name)) {
				throw new PresetNameAlreadyExists(name);
			}
		}

		customPresets.add(new CustomPreset(name, gains));
		savePresetsToPrefs(customPresets);
	}

	public void deleteCustomPreset(int position) {
		List<CustomPreset> customPresets = getPresetsFromPrefs();
		customPresets.remove(position);
		savePresetsToPrefs(customPresets);
	}

	public List<String> getCustomPresets() {
		List<CustomPreset> customPresets = getPresetsFromPrefs();
		List<String> presetsNames = new ArrayList<>();
		for (int i = 0; i < customPresets.size(); i++) {
			presetsNames.add(customPresets.get(i).getName());
		}

		return presetsNames;
	}

	public CustomPreset getCustomPresetAtIndex(int presetIndex) {
		List<CustomPreset> customPresets = getPresetsFromPrefs();
		return customPresets.get(presetIndex);
	}

	public Equalizer.PresetType getCurrentPresetType() {
		return prefs.getEnum(PREF_TYPE, Equalizer.PresetType.BUILT_IN);
	}

	public void saveCurrentPresetType(Equalizer.PresetType type) {
		prefs.edit().putEnum(PREF_TYPE, type).apply();
	}

	private void savePresetsToPrefs(List<CustomPreset> customPresets) {
		prefs.edit().putListAsJson(PREF_CUSTOM, customPresets).apply();
	}

	private ArrayList<CustomPreset> getPresetsFromPrefs() {
		return prefs.getJsonAsList(PREF_CUSTOM, type, new ArrayList<>());
	}
}
