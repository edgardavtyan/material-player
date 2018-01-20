package com.edavtyan.materialplayer.components.detail.artist_detail;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.detail.lib.ParallaxHeaderListActivity;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesFactory;

import javax.inject.Inject;

public class ArtistDetailActivityNormal
		extends ParallaxHeaderListActivity
		implements ArtistDetailView {

	@Inject Navigator navigator;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		getComponent().inject(this);
		super.onCreate(savedInstanceState);
	}

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
	public void gotoAlbumDetailNormal(int albumId) {
		navigator.gotoAlbumDetailNormal(albumId);
	}

	@Override
	public void gotoAlbumDetailCompact(int albumId) {
		navigator.gotoAlbumDetailCompact(albumId);
	}

	protected ArtistDetailComponent getComponent() {
		String artistTitle = getIntent().getStringExtra(EXTRA_ARTIST_TITLE);
		return DaggerArtistDetailComponent
				.builder()
				.appComponent(((App)getApplication()).getAppComponent())
				.artistDetailFactory(new ArtistDetailFactory(this, this, artistTitle))
				.activityModulesFactory(new ActivityModulesFactory())
				.build();
	}
}
