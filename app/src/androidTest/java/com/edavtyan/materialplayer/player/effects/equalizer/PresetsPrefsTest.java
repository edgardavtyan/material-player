package com.edavtyan.materialplayer.player.effects.equalizer;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.lib.prefs.AdvancedGsonSharedPrefs;
import com.edavtyan.materialplayer.ui.audio_effects.presets.CustomPreset;
import com.edavtyan.materialplayer.ui.audio_effects.presets.PresetNameAlreadyExists;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class PresetsPrefsTest extends BaseTest {
	private static final String PREF_INDEX = "presets_built_in";
	private static final String PREF_CUSTOM = "presets_custom";
	private static final String PREF_TYPE = "presets_type";
	private static final int DEFAULT_INDEX = 0;

	private PresetsPrefs presetsPrefs;
	private AdvancedGsonSharedPrefs gsonPrefs;
	private SharedPreferences basePrefs;
	private Type type;

	@Override
	public void beforeEach() {
		super.beforeEach();
		basePrefs = PreferenceManager.getDefaultSharedPreferences(context);
		Gson gson = new Gson();
		gsonPrefs = new AdvancedGsonSharedPrefs(basePrefs, gson);
		type = new TypeToken<List<CustomPreset>>() {}.getType();
		presetsPrefs = new PresetsPrefs(gsonPrefs);
	}

	@Override
	public void afterEach() {
		super.afterEach();
		basePrefs.edit().clear().apply();
	}

	@Test
	public void saveCurrentPresetIndex_saveIndexToPrefs() {
		presetsPrefs.saveCurrentPresetIndex(100);
		assertThat(basePrefs.getInt(PREF_INDEX, -1)).isEqualTo(100);
	}

	@Test
	public void getCurrentPresetIndex_getIndexFromPrefs() {
		basePrefs.edit().putInt(PREF_INDEX, 200).apply();
		assertThat(presetsPrefs.getCurrentPresetIndex()).isEqualTo(200);
	}

	@Test
	public void addNewCustomPreset_presetDoesNotExist_addNewPresetToPrefs()
	throws PresetNameAlreadyExists {
		presetsPrefs.addNewCustomPreset("preset_300", new int[]{1, 2, 3, 4, 5});
		List<CustomPreset> customPresets = gsonPrefs.getJsonAsList(PREF_CUSTOM, type, new ArrayList<>());
		assertThat(customPresets).hasSize(1);
		assertThat(customPresets.get(0).getName()).isEqualTo("preset_300");
		assertThat(customPresets.get(0).getGains()).containsExactly(1, 2, 3, 4, 5);
	}

	@Test
	public void addNewCustomPreset_presetAlreadyExists_throwException()
	throws PresetNameAlreadyExists {
		try {
			presetsPrefs.addNewCustomPreset("preset_400", new int[]{1, 2, 3});
			presetsPrefs.addNewCustomPreset("preset_400", new int[]{1, 2, 3});
		} catch (PresetNameAlreadyExists e) {
			assertThat(e.getMessage()).isEqualTo("Preset with name preset_400 already exists");
			return;
		}

		fail("Expected PresetNameAlreadyExists exception of to be thrown, but it wasn't ");
	}

	@Test
	public void deleteCustomPreset_removePresetFromPrefs() throws PresetNameAlreadyExists {
		presetsPrefs.addNewCustomPreset("preset_5000", new int[]{1, 2, 3});
		presetsPrefs.addNewCustomPreset("preset_5001", new int[]{1, 2, 3});
		presetsPrefs.addNewCustomPreset("preset_5002", new int[]{1, 2, 3});

		presetsPrefs.deleteCustomPreset(1);

		List<CustomPreset> customPresets = gsonPrefs.getJsonAsList(PREF_CUSTOM, type, new ArrayList<>());
		assertThat(customPresets).hasSize(2);
		assertThat(customPresets.get(0).getName()).isEqualTo("preset_5000");
		assertThat(customPresets.get(1).getName()).isEqualTo("preset_5002");
	}

	@Test
	public void getCustomPresets_returnPresetNamesFromPrefs() throws PresetNameAlreadyExists {
		presetsPrefs.addNewCustomPreset("preset_6000", new int[]{1, 2, 3});
		presetsPrefs.addNewCustomPreset("preset_6001", new int[]{1, 2, 3});
		presetsPrefs.addNewCustomPreset("preset_6002", new int[]{1, 2, 3});

		assertThat(presetsPrefs.getCustomPresets())
				.containsExactly("preset_6000", "preset_6001", "preset_6002");
	}

	@Test
	public void getPresetAtIndex_presetAtIndexExists_returnPresetFromPrefs()
	throws PresetNameAlreadyExists {
		presetsPrefs.addNewCustomPreset("preset_7000", new int[]{1, 2, 3});
		presetsPrefs.addNewCustomPreset("preset_7001", new int[]{2, 3, 4});
		presetsPrefs.addNewCustomPreset("preset_7002", new int[]{3, 4, 5});

		CustomPreset presetAtIndex = presetsPrefs.getCustomPresetAtIndex(2);

		assertThat(presetAtIndex.getName()).isEqualTo("preset_7002");
		assertThat(presetAtIndex.getGains()).containsExactly(3, 4, 5);
	}

	@Test
	public void getCurrentPresetType_getTypeFromPrefs() {
		gsonPrefs.edit().putEnum(PREF_TYPE, Equalizer.PresetType.CUSTOM_NEW).apply();
		assertThat(presetsPrefs.getCurrentPresetType()).isEqualTo(Equalizer.PresetType.CUSTOM_NEW);
	}

	@Test
	public void setCurrentPresetType_saveTypeToPrefs() {
		presetsPrefs.saveCurrentPresetType(Equalizer.PresetType.BUILT_IN);
		assertThat(gsonPrefs.getEnum(PREF_TYPE, Equalizer.PresetType.CUSTOM))
				.isEqualTo(Equalizer.PresetType.BUILT_IN);
	}
}
