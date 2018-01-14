package com.edavtyan.materialplayer.components.prefs;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.base.BaseActivity;
import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityToolbarModule;
import com.edavtyan.prefs.category.PreferenceCategory;
import com.edavtyan.prefs.checkbox.CheckboxPreference;
import com.edavtyan.prefs.color.ColorSelectionPreference;

import butterknife.BindView;

public class PrefActivity extends BaseActivity {
	@BindView(R.id.pref_category_appearance) PreferenceCategory appearanceCategoryView;
	@BindView(R.id.pref_category_playback) PreferenceCategory playbackCategoryView;
	@BindView(R.id.pref_compact_lists) CheckboxPreference compactListsPrefView;
	@BindView(R.id.pref_compact_detail) CheckboxPreference compactDetailPrefView;
	@BindView(R.id.pref_compact_main) CheckboxPreference compactMainPrefView;
	@BindView(R.id.pref_resume) CheckboxPreference resumePrefView;
	@BindView(R.id.pref_primary_color) ColorSelectionPreference primaryColorPrefView;

	@Override
	public int getLayoutId() {
		return R.layout.activity_pref;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ActivityToolbarModule toolbarModule = new ActivityToolbarModule(this);
		toolbarModule.setTitleStringId(R.string.pref_title);
		addModule(toolbarModule);
	}

	@Override
	public void onThemeChanged(ThemeColors colors) {
		super.onThemeChanged(colors);
		appearanceCategoryView.setTextColor(colors.getColorPrimary());
		playbackCategoryView.setTextColor(colors.getColorPrimary());
		compactListsPrefView.setCheckBoxColor(colors.getColorPrimary());
		compactDetailPrefView.setCheckBoxColor(colors.getColorPrimary());
		compactMainPrefView.setCheckBoxColor(colors.getColorPrimary());
		resumePrefView.setCheckBoxColor(colors.getColorPrimary());
		primaryColorPrefView.setDialogButtonsColor(colors.getColorPrimary());
	}
}
