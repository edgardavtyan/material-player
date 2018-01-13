package com.edavtyan.materialplayer.lib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityToolbarModule;
import com.edavtyan.materialplayer.utils.ThemeColors;

import butterknife.BindView;

public abstract class BaseToolbarActivity extends BaseActivity {
	@BindView(R.id.toolbar) Toolbar toolbar;

	private ActivityToolbarModule toolbarModule;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		toolbarModule = new ActivityToolbarModule(this);
		toolbarModule.setTitleStringId(getToolbarTitleStringId());
		toolbarModule.setBackIconEnabled(isBackIconEnabled());
		addModule(toolbarModule);
	}

	@Override
	public void onThemeChanged(ThemeColors colors) {
		toolbarModule.setBackgroundColor(colors.getColorPrimary());
		toolbarModule.setStatusBarColor(colors.getColorPrimaryDark());
	}

	protected boolean isBackIconEnabled() {
		return true;
	}

	protected int getToolbarTitleStringId() {
		return R.string.app_name;
	}
}
