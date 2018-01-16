package com.edavtyan.materialplayer.components.detail.album_detail;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.detail.lib.ParallaxHeaderListActivity;
import com.edavtyan.materialplayer.lib.theme.ThemeDaggerModule;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesModule;

import javax.inject.Inject;

public class AlbumDetailActivityNormal
		extends ParallaxHeaderListActivity
		implements AlbumDetailView {

	@Inject Navigator navigator;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		getComponent().inject(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void setAlbumTitle(String title) {
		setTitle(title);
	}

	@Override
	public void setAlbumInfo(String artistTitle, int tracksCount, long duration) {
		Resources res = getResources();
		String tracksCountStr = res.getQuantityString(R.plurals.tracks, tracksCount, tracksCount);
		String info = getString(R.string.pattern_album_info, artistTitle, tracksCountStr);
		setInfo(info);
	}

	@Override
	public void setAlbumImage(Bitmap image) {
		setImage(image, R.drawable.fallback_cover);
	}

	@Override
	public void gotoNowPlaying() {
		navigator.gotoNowPlaying();
	}

	protected AlbumDetailComponent getComponent() {
		int albumId = getIntent().getIntExtra(EXTRA_ALBUM_ID, -1);
		return DaggerAlbumDetailComponent
				.builder()
				.albumDetailModule(new AlbumDetailModule(this, this, albumId))
				.activityModulesModule(new ActivityModulesModule(this))
				.themeDaggerModule(new ThemeDaggerModule(this))
				.build();
	}
}
