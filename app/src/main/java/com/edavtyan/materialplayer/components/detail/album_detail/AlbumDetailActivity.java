package com.edavtyan.materialplayer.components.detail.album_detail;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.detail.lib.ParallaxHeaderListActivity;

import javax.inject.Inject;

public class AlbumDetailActivity extends ParallaxHeaderListActivity implements AlbumDetailView {
	@Inject Navigator navigator;
	@Inject AlbumDetailPresenter presenter;
	@Inject AlbumDetailAdapter adapter;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int albumId = getIntent().getIntExtra(EXTRA_ALBUM_ID, -1);
		getApp().getAlbumDetailComponent(this, this, albumId).inject(this);
		init(adapter, presenter);
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
}
