package com.edavtyan.materialplayer.screens.detail.lib;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;

import com.ed.libsutils.utils.WindowUtils;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.screens.lists.lib.ListView;
import com.edavtyan.materialplayer.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer.modular.activity.ModularActivity;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityToolbarModule;

import javax.inject.Inject;

import butterknife.ButterKnife;

public abstract class ParallaxHeaderListCompactActivity
		extends ModularActivity
		implements ListView {

	@Inject ActivityToolbarModule toolbarModule;
	@Inject ActivityBaseMenuModule baseMenuModule;
	@Inject ScreenThemeModule themeModule;
	@Inject ParallaxHeaderListCompactModule parallaxListModule;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_detail_compact);
		ButterKnife.bind(this);

		addModule(baseMenuModule);
		addModule(toolbarModule);
		addModule(themeModule);
		addModule(parallaxListModule);
	}

	@Override
	public void onBackPressed() {
		if (WindowUtils.isPortrait(this)) {
			callModulesOnBackPressed();
		} else {
			super.onBackPressed();
		}
	}

	public void setTitle(String title) {
		parallaxListModule.setTitle(title);
	}

	public void setInfo(String portraitTopInfo, String portraitBottomInfo, String landscapeInfo) {
		parallaxListModule.setInfo(portraitTopInfo, portraitBottomInfo, landscapeInfo);
	}

	public void setImage(Bitmap image, @DrawableRes int fallback) {
		parallaxListModule.setImage(image, fallback);
	}

	public void notifyDataSetChanged() {
		parallaxListModule.notifyDataSetChanged();
	}
}
