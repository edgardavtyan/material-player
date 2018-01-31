package com.edavtyan.materialplayer.screens.detail.artist_detail;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ArtistDetailSharedViews {
	private final ImageView artView;

	public ArtistDetailSharedViews(ImageView artView) {
		this.artView = artView;
	}

	public Bundle build() {
		int[] artViewLocation = new int[2];
		artView.getLocationOnScreen(artViewLocation);
		Bundle bundle = new Bundle();
		bundle.putFloat(ArtistDetailView.SHARED_ART_X, artViewLocation[0]);
		bundle.putFloat(ArtistDetailView.SHARED_ART_Y, artViewLocation[1]);
		bundle.putInt(ArtistDetailView.SHARED_ART_WIDTH, artView.getWidth());
		bundle.putInt(ArtistDetailView.SHARED_ART_HEGIHT, artView.getHeight());
		return bundle;
	}

	public void hide() {
		artView.setVisibility(View.INVISIBLE);
	}

	public void show() {
		artView.setVisibility(View.VISIBLE);
	}
}
