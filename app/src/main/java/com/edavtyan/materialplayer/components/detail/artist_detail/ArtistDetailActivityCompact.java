package com.edavtyan.materialplayer.components.detail.artist_detail;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.CompactPrefsModule;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.UtilsModule;
import com.edavtyan.materialplayer.components.detail.lib.ParallaxHeaderListCompactActivity;
import com.edavtyan.materialplayer.db.DaggerDBModule;
import com.edavtyan.materialplayer.lib.lastfm.LastFmModule;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;

import javax.inject.Inject;

public class ArtistDetailActivityCompact
		extends ParallaxHeaderListCompactActivity
		implements ArtistDetailView {

	@Inject Navigator navigator;
	@Inject ArtistDetailPresenter presenter;
	@Inject ArtistDetailAdapter adapter;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getComponent().inject(this);
		init(adapter, presenter);
	}

	@Override
	public void setArtistTitle(String title) {
		setTitle(title);
	}

	@Override
	public void setArtistInfo(int albumsCount, int tracksCount) {
		Resources res = getResources();
		String portraitTopInfo = res.getQuantityString(R.plurals.albums, albumsCount, albumsCount);
		String portraitBottomInfo = res.getQuantityString(R.plurals.tracks, tracksCount, tracksCount);
		String landscapeInfo = res.getString(R.string.pattern_artist_info, portraitTopInfo, portraitBottomInfo);
		setInfo(portraitTopInfo, portraitBottomInfo, landscapeInfo);
	}

	@Override
	public void setArtistImage(Bitmap image) {
		setImage(image, R.drawable.fallback_artist);
	}

	@Override
	public void gotoAlbumDetail(int albumId) {
		navigator.gotoAlbumDetail(albumId);
	}

	protected ArtistDetailComponent getComponent() {
		String artistTitle = getIntent().getStringExtra(EXTRA_ARTIST_TITLE);
		return DaggerArtistDetailComponent
				.builder()
				.artistDetailModule(new ArtistDetailModule(this, this, artistTitle))
				.lastFmModule(new LastFmModule(getString(R.string.lastfm_api_key)))
				.modelModulesModule(new ModelModulesModule(this))
				.utilsModule(new UtilsModule())
				.daggerDBModule(new DaggerDBModule())
				.advancedSharedPrefsModule(new AdvancedSharedPrefsModule())
				.compactPrefsModule(new CompactPrefsModule())
				.build();
	}
}
