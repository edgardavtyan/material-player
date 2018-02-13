package com.edavtyan.materialplayer.screens.prefs;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesFactory;
import com.edavtyan.materialplayer.modular.activity.ModularActivity;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityToolbarModule;
import com.edavtyan.prefs.category.PreferenceCategory;
import com.edavtyan.prefs.checkbox.CheckboxPreference;
import com.edavtyan.prefs.color.ColorSelectionPreference;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PrefActivity extends ModularActivity {
	@BindView(R.id.pref_category_appearance) PreferenceCategory appearanceCategoryView;
	@BindView(R.id.pref_category_playback) PreferenceCategory playbackCategoryView;
	@BindView(R.id.pref_compact_lists) CheckboxPreference compactListsPrefView;
	@BindView(R.id.pref_resume) CheckboxPreference resumePrefView;
	@BindView(R.id.pref_primary_color) ColorSelectionPreference primaryColorPrefView;

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
		compactListsPrefView.setCheckBoxColor(colors.getColorPrimary());
		resumePrefView.setCheckBoxColor(colors.getColorPrimary());
		primaryColorPrefView.setDialogButtonsColor(colors.getColorPrimary());
	}

	protected PrefComponent getComponent() {
		return DaggerPrefComponent
				.builder()
				.appComponent(((App)getApplication()).getAppComponent())
				.prefModule(new PrefModule(this))
				.activityModulesFactory(new ActivityModulesFactory(R.string.pref_title))
				.build();
	}
}
