package com.edavtyan.materialplayer.components.detail.lib;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.lists.lib.ListView;
import com.edavtyan.materialplayer.lib.base.BaseToolbarActivity;
import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;

public abstract class ParallaxHeaderListCompactActivity
		extends BaseToolbarActivity
		implements ListView {

	private ParallaxHeaderListCompactModule parallaxHeaderListModule;

	@Override
	public int getLayoutId() {
		return R.layout.activity_detail_compact;
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
