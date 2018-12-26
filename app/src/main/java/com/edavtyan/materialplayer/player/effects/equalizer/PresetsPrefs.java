package com.edavtyan.materialplayer.player.effects.equalizer;

import com.edavtyan.materialplayer.lib.prefs.AdvancedGsonSharedPrefs;
import com.edavtyan.materialplayer.ui.audio_effects.presets.CustomPreset;
import com.edavtyan.materialplayer.ui.audio_effects.presets.PresetNameAlreadyExists;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public abstract class PresetsPrefs {
	private static final int DEFAULT_INDEX = 0;

	private final String indexKey;
	private final String customKey;
	private final String typeKey;

	private final AdvancedGsonSharedPrefs prefs;
	private final Type type;

	public PresetsPrefs(AdvancedGsonSharedPrefs prefs, String indexKey, String customKey, String typeKey) {
		this.indexKey = indexKey;
		this.customKey = customKey;
		this.typeKey = typeKey;
		this.prefs = prefs;
		this.type = new TypeToken<List<CustomPreset>>() {}.getType();
	}

	public void saveCurrentPresetIndex(int index) {
		prefs.edit().putInt(indexKey, index).apply();
	}

	public int getCurrentPresetIndex() {
		return prefs.getInt(indexKey, DEFAULT_INDEX);
	}

	public void addNewCustomPreset(String name, int[] gains) throws PresetNameAlreadyExists {
		addOrOverwriteCustomPreset(name, gains, false);
	}

	public void addAndOverwriteCustomPreset(String name, int[] gains) {
		try {
			addOrOverwriteCustomPreset(name, gains, true);
		} catch (PresetNameAlreadyExists ignore) {
		}
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
		return prefs.getEnum(typeKey, Equalizer.PresetType.BUILT_IN);
	}

	public void saveCurrentPresetType(Equalizer.PresetType type) {
		prefs.edit().putEnum(typeKey, type).apply();
	}

	private void savePresetsToPrefs(List<CustomPreset> customPresets) {
		prefs.edit().putListAsJson(customKey, customPresets).apply();
	}

	private ArrayList<CustomPreset> getPresetsFromPrefs() {
		return prefs.getJsonAsList(customKey, type, new ArrayList<>());
	}

	public void addOrOverwriteCustomPreset(String name, int[] gains, boolean overwrite)
	throws PresetNameAlreadyExists {
		List<CustomPreset> customPresets = getPresetsFromPrefs();

		for (CustomPreset customPreset : customPresets) {
			if (customPreset.getName().equals(name)) {
				if (overwrite) {
					customPresets.remove(customPreset);
					customPresets.add(new CustomPreset(name, gains));
					savePresetsToPrefs(customPresets);
					return;
				} else {
					throw new PresetNameAlreadyExists(name);
				}
			}
		}

		customPresets.add(new CustomPreset(name, gains));
		savePresetsToPrefs(customPresets);
	}
}
