package com.edavtyan.materialplayer.ui.prefs;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ed.androidprefs.category.PreferenceCategory;
import com.ed.androidprefs.checkbox.CheckboxPreference;
import com.ed.androidprefs.color.ColorSelectionPreference;
import com.ed.androidprefs.seekbar.SeekbarPreference;
import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesDIModule;
import com.edavtyan.materialplayer.modular.activity.ModularActivity;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityToolbarModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PrefActivity extends ModularActivity {
	@BindView(R.id.pref_category_appearance) PreferenceCategory appearanceCategoryView;
	@BindView(R.id.pref_category_playback) PreferenceCategory playbackCategoryView;
	@BindView(R.id.pref_engine) CheckboxPreference enginePrefView;
	@BindView(R.id.pref_category_rg) PreferenceCategory rgCategoryView;
	@BindView(R.id.pref_resume) CheckboxPreference resumePrefView;
	@BindView(R.id.pref_primary_color) ColorSelectionPreference primaryColorPrefView;
	@BindView(R.id.pref_rg_enabled) CheckboxPreference rgEnabledPrefView;
	@BindView(R.id.pref_rg_album) CheckboxPreference rgAlbumPrefView;
	@BindView(R.id.pref_rg_gain) SeekbarPreference rgGainPrefView;

	@Inject ActivityToolbarModule toolbarModule;
	@Inject ActivityBaseMenuModule baseMenuModule;
	@Inject ScreenThemeModule themeModule;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_pref);
		ButterKnife.bind(this);

		getComponent().inject(this);
		addModule(toolbarModule);
		addModule(baseMenuModule);
		addModule(themeModule);
	}

	@Override
	public void onThemeChanged(ThemeColors colors) {
		super.onThemeChanged(colors);
		appearanceCategoryView.setTextColor(colors.getColorPrimary());
		playbackCategoryView.setTextColor(colors.getColorPrimary());
		enginePrefView.setCheckBoxColor(colors.getColorPrimary());
		rgCategoryView.setTextColor(colors.getColorPrimary());
		resumePrefView.setCheckBoxColor(colors.getColorPrimary());
		primaryColorPrefView.setDialogButtonsColor(colors.getColorPrimary());
		rgEnabledPrefView.setCheckBoxColor(colors.getColorPrimary());
		rgAlbumPrefView.setCheckBoxColor(colors.getColorPrimary());
		rgGainPrefView.setColor(colors.getColorPrimary());
	}

	protected PrefDIComponent getComponent() {
		return DaggerPrefDIComponent
				.builder()
				.appDIComponent(((App) getApplication()).getAppComponent())
				.prefDIModule(new PrefDIModule(this))
				.activityModulesDIModule(new ActivityModulesDIModule(R.string.pref_title))
				.build();
	}
}
