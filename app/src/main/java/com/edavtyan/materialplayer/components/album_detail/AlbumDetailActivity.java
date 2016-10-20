package com.edavtyan.materialplayer.components.album_detail;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.lib.mvp.parallax_list.ParallaxHeaderListActivity;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;

public class AlbumDetailActivity extends ParallaxHeaderListActivity implements AlbumDetailMvp.View {

	public static final String EXTRA_ALBUM_ID = "extra_albumId";

	private Navigator navigator;
	private TestableBitmapFactory bitmapFactory;

	public void setAlbumTitle(String title) {
		setHeaderTitle(title);
	}

	public void setAlbumInfo(String artistTitle, int tracksCount) {
		Resources res = getResources();
		String tracksCountStr = res.getQuantityString(R.plurals.tracks, tracksCount, tracksCount);
		String info = getString(R.string.pattern_album_info, artistTitle, tracksCountStr);
		setHeaderInfo(info);
	}

	public void setAlbumImage(String artPath) {
		Bitmap art = bitmapFactory.fromPath(artPath);
		setHeaderImage(art, R.drawable.fallback_cover);
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AlbumDetailFactory factory = getDI();
		init(factory.provideAdapter(), factory.providePresenter());
		navigator = factory.provideNavigator();
		bitmapFactory = factory.provideBitmapFactory();
	}

	@Override
	public void goToNowPlaying() {
		navigator.gotoNowPlaying();
	}

	protected AlbumDetailFactory getDI() {
		String albumId = getIntent().getStringExtra(EXTRA_ALBUM_ID);
		return ((App) getApplicationContext()).getAlbumDetailDI(this, Integer.parseInt(albumId));
	}
}
