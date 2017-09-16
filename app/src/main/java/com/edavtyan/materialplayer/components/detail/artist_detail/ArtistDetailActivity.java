package com.edavtyan.materialplayer.components.detail.artist_detail;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.detail.lib.ParallaxHeaderListActivity;
import com.edavtyan.materialplayer.components.detail.lib.ParallaxHeaderListModule;
import com.edavtyan.materialplayer.modular.activity.BaseToolbarModule;

import static com.edavtyan.materialplayer.components.detail.artist_detail.ArtistDetailMvp.EXTRA_ARTIST_TITLE;

public class ArtistDetailActivity
		extends ParallaxHeaderListActivity
		implements ArtistDetailMvp.View {

	private Navigator navigator;

	@Override
	public void setArtistTitle(String title) {
		setTitle(title);
	}

	@Override
	public void setArtistInfo(int albumsCount, int tracksCount) {
		Resources res = getResources();
		String albumsCountStr = res.getQuantityString(R.plurals.albums, albumsCount, albumsCount);
		String tracksCountStr = res.getQuantityString(R.plurals.tracks, tracksCount, tracksCount);
		String info = getString(R.string.pattern_artist_info, albumsCountStr, tracksCountStr);
		setInfo(info);
	}

	@Override
	public void setArtistImage(Bitmap image) {
		setImage(image, R.drawable.fallback_artist);
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ArtistDetailFactory factory = getDI();

		navigator = factory.getNavigator();

		addModule(new ParallaxHeaderListModule(this, factory.getAdapter(), factory.getPresenter()));
		addModule(new BaseToolbarModule(this));

		init(factory.getAdapter());
	}

	@Override
	public void gotoAlbumDetail(int albumId) {
		navigator.gotoAlbumDetail(albumId);
	}

	protected ArtistDetailFactory getDI() {
		String artistTitle = getIntent().getStringExtra(EXTRA_ARTIST_TITLE);
		return ((App) getApplication()).getArtistDetailDI(this, this, artistTitle);
	}
}
