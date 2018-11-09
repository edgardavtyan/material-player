package com.edavtyan.materialplayer.modular.activity.modules;

import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.ed.libsutils.utils.WindowUtils;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.theme.Theme;
import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.modular.activity.ActivityModule;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityToolbarModule extends ActivityModule {

	private static final int DEFAULT_TITLE_STRING_ID = R.string.app_name;
	private static final boolean DEFAULT_BACK_ICON_ENABLED = true;

	@BindView(R.id.toolbar) Toolbar toolbar;
	@BindView(R.id.appbar) AppBarLayout appBarLayout;

	private final AppCompatActivity activity;

	private int titleStringId;
	private boolean isBackIconEnabled;

	public ActivityToolbarModule(AppCompatActivity activity) {
		this.activity = activity;
		titleStringId = DEFAULT_TITLE_STRING_ID;
		isBackIconEnabled = DEFAULT_BACK_ICON_ENABLED;
	}

	public ActivityToolbarModule(AppCompatActivity activity, @StringRes int titleStringId) {
		this(activity);
		this.titleStringId = titleStringId;
		this.isBackIconEnabled = DEFAULT_BACK_ICON_ENABLED;
	}

	public ActivityToolbarModule(
			AppCompatActivity activity,
			@StringRes int titleStringId,
			boolean isBackIconEnabled) {
		this(activity);
		this.titleStringId = titleStringId;
		this.isBackIconEnabled = isBackIconEnabled;
	}

	public void setTitleString(String title) {
		toolbar.setTitle(title);
	}

	public void setBackIconEnabled(boolean enabled) {
		assert activity.getSupportActionBar() != null;
		activity.getSupportActionBar().setDisplayHomeAsUpEnabled(enabled);
	}

	public void setToolbarBackgroundColor(int color) {
		toolbar.setBackgroundColor(color);
	}

	public void setToolbarIconsColor(int color) {
		toolbar.getOverflowIcon().setColorFilter(color, PorterDuff.Mode.SRC_IN);
	}

	public void setStatusBarColor(int color) {
		WindowUtils.setStatusBarColor(activity, color);
	}

	public void setTheme(ThemeColors colors) {
		setStatusBarColor(colors.getColorPrimaryDark());
		setToolbarBackgroundColor(colors.getColorPrimary());
		setToolbarIconsColor(colors.getTextContrastPrimary());

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			if (colors.getTheme() == Theme.WHITE) {
				WindowUtils.addSystemUiFlag(activity, View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
			} else {
				WindowUtils.removeSystemUiFlag(activity, View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
			}
		}
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ButterKnife.bind(this, activity);

		toolbar.setTitle(activity.getResources().getString(titleStringId));
		activity.setSupportActionBar(toolbar);
		setBackIconEnabled(isBackIconEnabled);

		if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
			WindowUtils.makeStatusBarSemiTransparent(activity);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			activity.onBackPressed();
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onThemeChanged(ThemeColors colors) {
		appBarLayout.setBackgroundColor(colors.getColorPrimary());
		WindowUtils.setStatusBarColor(activity, colors.getColorPrimaryDark());
	}
}
