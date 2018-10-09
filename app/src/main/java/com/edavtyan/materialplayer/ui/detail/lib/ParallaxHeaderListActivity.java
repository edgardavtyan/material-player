package com.edavtyan.materialplayer.ui.detail.lib;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.base.BaseActivity;
import com.edavtyan.materialplayer.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityToolbarModule;
import com.edavtyan.materialplayer.ui.lists.lib.ListView;

import javax.inject.Inject;

import butterknife.ButterKnife;

public abstract class ParallaxHeaderListActivity
		extends BaseActivity
		implements ListView {

	@Inject ActivityToolbarModule toolbarModule;
	@Inject ActivityBaseMenuModule baseMenuModule;
	@Inject ScreenThemeModule themeModule;
	@Inject ParallaxHeaderListModule parallaxListModule;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_detail);
		ButterKnife.bind(this);

		addModule(baseMenuModule);
		addModule(toolbarModule);
		addModule(themeModule);
		addModule(parallaxListModule);
	}

	@Override
	public void onBackPressed() {
		callModulesOnBackPressed();
	}

	public void setTitle(String title) {
		parallaxListModule.setTitle(title);
	}

	public void setInfo(String portraitTopInfo, String portraitBottomInfo, String landscapeInfo) {
		parallaxListModule.setInfo(portraitTopInfo, portraitBottomInfo, landscapeInfo);
	}

	public void setImage(@Nullable Bitmap image, @DrawableRes int fallback) {
		parallaxListModule.setArt(image, fallback);
	}
}
