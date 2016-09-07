package com.edavtyan.materialplayer.components.album_detail;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.mvp.parallax_list.ParallaxHeaderListActivity;
import com.edavtyan.materialplayer.components.nowplaying.NowPlayingActivity;

import java.io.File;

public class AlbumDetailActivity extends ParallaxHeaderListActivity implements AlbumDetailMvp.View {

	private static final String EXTRA_ALBUM_ID = "extra_albumId";

	public static void startActivity(Context context, int albumId) {
		Intent intent = new Intent(context, AlbumDetailActivity.class);
		intent.putExtra(EXTRA_ALBUM_ID, albumId);
		context.startActivity(intent);
	}

	public void setAlbumTitle(String title) {
		setHeaderTitle(title);
	}

	public void setAlbumInfo(String artistTitle, int tracksCount) {
		Resources res = getResources();
		String tracksCountStr = res.getQuantityString(R.plurals.tracks, tracksCount, tracksCount);
		String info = getString(R.string.pattern_album_info, artistTitle, tracksCountStr);
		setHeaderInfo(info);
	}

	public void setAlbumImage(File artFile, int fallbackImage) {
		Bitmap art = BitmapFactory.decodeFile(artFile.getAbsolutePath());
		setHeaderImage(art, fallbackImage);
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AlbumDetailDI di = getDI();
		init(di.provideAdapter(), di.providePresenter());
	}

	protected AlbumDetailDI getDI() {
		int albumId = getIntent().getIntExtra(EXTRA_ALBUM_ID, 0);
		return ((App) getApplicationContext()).getAlbumDetailDI(this, albumId);
	}

	@Override
	public void goToNowPlaying() {
		NowPlayingActivity.startActivity(this);
	}
}
