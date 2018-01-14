package com.edavtyan.materialplayer.components.prefs;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.base.BaseToolbarActivity;
import com.edavtyan.materialplayer.utils.ThemeColors;
import com.edavtyan.prefs.category.PreferenceCategory;
import com.edavtyan.prefs.checkbox.CheckboxPreference;

import butterknife.BindView;

public class PrefActivity extends BaseToolbarActivity {
	@BindView(R.id.pref_category_appearance) PreferenceCategory appearanceCategoryView;
	@BindView(R.id.pref_category_playback) PreferenceCategory playbackCategoryView;
	@BindView(R.id.pref_compact_lists) CheckboxPreference compactListsPrefView;
	@BindView(R.id.pref_compact_detail) CheckboxPreference compactDetailPrefView;
	@BindView(R.id.pref_compact_main) CheckboxPreference compactMainPrefView;
	@BindView(R.id.pref_resume) CheckboxPreference resumePrefView;

	@Override
	public int getLayoutId() {
		return R.layout.activity_pref;
	}

	@Override
	protected int getToolbarTitleStringId() {
		return R.string.pref_title;
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
	}
}
