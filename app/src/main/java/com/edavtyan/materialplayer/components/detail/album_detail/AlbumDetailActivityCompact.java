package com.edavtyan.materialplayer.components.detail.album_detail;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.detail.lib.ParallaxHeaderListCompactActivity;
import com.edavtyan.materialplayer.components.detail.lib.ParallaxHeaderListCompactModule;

import java.util.concurrent.TimeUnit;

import static com.edavtyan.materialplayer.components.detail.album_detail.AlbumDetailMvp.EXTRA_ALBUM_ID;

public class AlbumDetailActivityCompact
		extends ParallaxHeaderListCompactActivity
		implements AlbumDetailMvp.View {

	private Navigator navigator;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int albumId = getIntent().getIntExtra(EXTRA_ALBUM_ID, -1);
		AlbumDetailFactory factory = ((App) getApplicationContext()).getAlbumDetailDI(this, this, albumId);

		navigator = factory.getNavigator();

		addModule(new ParallaxHeaderListCompactModule(this, factory.getAdapter(), factory.getPresenter()));

		init(factory.getAdapter());
	}

	@Override
	public void setAlbumTitle(String albumTitle) {
		setTitle(albumTitle);
	}

	@Override
	@SuppressWarnings("ConstantConditions")
	public void setAlbumInfo(String artistTitle, int tracksCount, long duration) {
		String tracksCountStr = getResources().getQuantityString(R.plurals.tracks, tracksCount, tracksCount);
		long durationStr = TimeUnit.MILLISECONDS.toMinutes(duration);

		String portraitBottomInfo = getString(R.string.pattern_track_time_count, tracksCountStr, durationStr);
		String landscapeInfo = getString(R.string.pattern_album_info, artistTitle, tracksCountStr);

		setInfo(artistTitle, portraitBottomInfo, landscapeInfo);
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