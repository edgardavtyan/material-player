package com.edavtyan.materialplayer.screens.detail.lib;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.edavtyan.materialplayer.SharedViews;

public class DetailSharedViews extends SharedViews {
	private final ImageView artView;

	public DetailSharedViews(ImageView artView) {
		this.artView = artView;
	}

	public Bundle build() {
		int[] artViewLocation = new int[2];
		artView.getLocationOnScreen(artViewLocation);
		Bundle bundle = new Bundle();
		bundle.putFloat(ParallaxHeaderListCompactModule.SHARED_ART_X, artViewLocation[0]);
		bundle.putFloat(ParallaxHeaderListCompactModule.SHARED_ART_Y, artViewLocation[1]);
		bundle.putInt(ParallaxHeaderListCompactModule.SHARED_ART_WIDTH, artView.getWidth());
		bundle.putInt(ParallaxHeaderListCompactModule.SHARED_ART_HEIGHT, artView.getHeight());
		return bundle;
	}

	@Override
	public void hide() {
		artView.setVisibility(View.INVISIBLE);
	}

	@Override
	public void show() {
		artView.setVisibility(View.VISIBLE);
	}
}
