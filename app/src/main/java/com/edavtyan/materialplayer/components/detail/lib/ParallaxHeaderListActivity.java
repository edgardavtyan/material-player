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

public abstract class ParallaxHeaderListActivity
		extends BaseActivity
		implements ListView {

	private ParallaxHeaderListModule parallaxHeaderListModule;

	@Override
	public int getLayoutId() {
		return R.layout.activity_detail;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addModule(new ActivityToolbarModule(this));
	}

	public void init(TestableRecyclerAdapter adapter, ParallaxHeaderListPresenter presenter) {
		parallaxHeaderListModule = new ParallaxHeaderListModule(this, adapter, presenter);
		addModule(new ParallaxHeaderListModule(this, adapter, presenter));
	}

	public void setTitle(String title) {
		parallaxHeaderListModule.setTitle(title);
	}

	public void setInfo(String info) {
		parallaxHeaderListModule.setInfo(info);
	}

	public void setImage(Bitmap image, @DrawableRes int fallback) {
		parallaxHeaderListModule.setImage(image, fallback);
	}

	@Override
	public void notifyDataSetChanged() {
		parallaxHeaderListModule.notifyDataSetChanged();
	}
}
