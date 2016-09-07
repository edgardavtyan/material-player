package com.edavtyan.materialplayer.components.album_detail;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.lib.mvp.parallax_list.ParallaxHeaderListActivity;

import java.io.File;

public class AlbumDetailActivity extends ParallaxHeaderListActivity implements AlbumDetailMvp.View {

	public static final String EXTRA_ALBUM_ID = "extra_albumId";

	private Navigator navigator;

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

		AlbumDetailFactory factory = getDI();
		init(factory.provideAdapter(), factory.providePresenter());
		navigator = factory.provideNavigator();
	}

	@Override
	public void goToNowPlaying() {
		navigator.gotoNowPlaying();
	}

	protected AlbumDetailFactory getDI() {
		int albumId = getIntent().getIntExtra(EXTRA_ALBUM_ID, 0);
		return ((App) getApplicationContext()).getAlbumDetailDI(this, albumId);
	}
}
