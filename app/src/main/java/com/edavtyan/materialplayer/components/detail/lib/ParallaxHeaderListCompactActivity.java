package com.edavtyan.materialplayer.components.detail.lib;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.lists.lib.ListView;
import com.edavtyan.materialplayer.lib.base.BaseActivity;
import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityToolbarModule;

public abstract class ParallaxHeaderListCompactActivity
		extends BaseActivity
		implements ListView {

	private ParallaxHeaderListCompactModule parallaxHeaderListModule;

	@Override
	public int getLayoutId() {
		return R.layout.activity_detail_compact;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addModule(new ActivityToolbarModule(this));
	}

	protected void init(TestableRecyclerAdapter adapter, ParallaxHeaderListPresenter presenter) {
		parallaxHeaderListModule = new ParallaxHeaderListCompactModule(this, adapter, presenter);
		addModule(parallaxHeaderListModule);
	}

	public void setTitle(String title) {
		parallaxHeaderListModule.setTitle(title);
	}

	public void setInfo(String portraitTopInfo, String portraitBottomInfo, String landscapeInfo) {
		parallaxHeaderListModule.setInfo(portraitTopInfo, portraitBottomInfo, landscapeInfo);
	}

	public void setImage(Bitmap image, @DrawableRes int fallback) {
		parallaxHeaderListModule.setImage(image, fallback);
	}

	public void notifyDataSetChanged() {
		parallaxHeaderListModule.notifyDataSetChanged();
	}
}
