package com.edavtyan.materialplayer.screens.detail.lib;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer.modular.activity.ModularActivity;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityToolbarModule;
import com.edavtyan.materialplayer.screens.lists.lib.ListView;

import javax.inject.Inject;

import butterknife.ButterKnife;

public abstract class ParallaxHeaderListActivity extends ModularActivity implements ListView {

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

	public void setTitle(String title) {
		parallaxListModule.setTitle(title);
	}

	public void setInfo(String info) {
		parallaxListModule.setInfo(info);
	}

	public void setImage(Bitmap image, @DrawableRes int fallback) {
		parallaxListModule.setImage(image, fallback);
	}

	@Override
	public void notifyDataSetChanged() {
		parallaxListModule.notifyDataSetChanged();
	}
}
