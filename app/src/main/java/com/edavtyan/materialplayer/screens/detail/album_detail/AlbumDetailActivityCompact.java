package com.edavtyan.materialplayer.screens.detail.album_detail;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.screens.Navigator;
import com.edavtyan.materialplayer.screens.detail.lib.ParallaxHeaderListCompactActivity;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

public class AlbumDetailActivityCompact
		extends ParallaxHeaderListCompactActivity
		implements AlbumDetailView {

	@Inject Navigator navigator;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		getComponent().inject(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void setAlbumTitle(String albumTitle) {
		setTitle(albumTitle);
	}

	@Override
	public void setAlbumInfo(String artistTitle, int tracksCount, long duration) {
		String tracksCountStr = getResources().getQuantityString(
				R.plurals.tracks, tracksCount, tracksCount);
		long durationStr = TimeUnit.MILLISECONDS.toMinutes(duration);

		String portraitBottomInfo = getString(
				R.string.pattern_track_time_count, tracksCountStr, durationStr);
		String landscapeInfo = getString(
				R.string.pattern_album_info, artistTitle, tracksCountStr);

		setInfo(artistTitle, portraitBottomInfo, landscapeInfo);
	}

	@Override
	public void setAlbumImage(Bitmap image) {
		setImage(image, R.drawable.fallback_cover);
	}

	@Override
	public void gotoNowPlaying() {
		navigator.gotoNowPlaying(this, new Bundle());
	}

	protected AlbumDetailComponent getComponent() {
		int albumId = getIntent().getIntExtra(EXTRA_ALBUM_ID, -1);
		return DaggerAlbumDetailComponent
				.builder()
				.appComponent(((App)getApplication()).getAppComponent())
				.albumDetailFactory(new AlbumDetailFactory(this, this, albumId))
				.build();
	}
}
