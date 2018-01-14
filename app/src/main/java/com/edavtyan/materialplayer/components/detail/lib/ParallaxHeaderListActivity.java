package com.edavtyan.materialplayer.components.detail.lib;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.lists.lib.ListView;
import com.edavtyan.materialplayer.lib.base.BaseToolbarActivity;
import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;

public abstract class ParallaxHeaderListActivity
		extends BaseToolbarActivity
		implements ListView {

	private ParallaxHeaderListModule parallaxHeaderListModule;

	@Override
	public int getLayoutId() {
		return R.layout.activity_detail;
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
