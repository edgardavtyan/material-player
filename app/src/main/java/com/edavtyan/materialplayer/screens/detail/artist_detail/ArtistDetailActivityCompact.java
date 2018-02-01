package com.edavtyan.materialplayer.screens.detail.artist_detail;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.CurrentSharedViews;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.screens.Navigator;
import com.edavtyan.materialplayer.screens.detail.lib.DetailSharedViews;
import com.edavtyan.materialplayer.screens.detail.lib.ParallaxHeaderListCompactActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArtistDetailActivityCompact
		extends ParallaxHeaderListCompactActivity
		implements ArtistDetailView {

	@Inject Navigator navigator;
	@Inject CurrentSharedViews currentSharedViews;

	@BindView(R.id.shared_art) ImageView sharedArtView;
	@BindView(R.id.art) ImageView artView;
	@BindView(R.id.main_wrapper) LinearLayout mainWrapper;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		getComponent().inject(this);
		super.onCreate(savedInstanceState);
		ButterKnife.bind(this);
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
	public void gotoAlbumDetailNormal(int albumId) {
		navigator.gotoAlbumDetailNormal(albumId);
	}

	@Override
	public void gotoAlbumDetailCompact(int albumId, DetailSharedViews sharedViews) {
		currentSharedViews.push(sharedViews);
		navigator.gotoAlbumDetailCompact(this, albumId, sharedViews.build());
	}

	protected ArtistDetailComponent getComponent() {
		String artistTitle = getIntent().getStringExtra(EXTRA_ARTIST_TITLE);
		return DaggerArtistDetailComponent
				.builder()
				.appComponent(((App) getApplication()).getAppComponent())
				.artistDetailFactory(new ArtistDetailFactory(this, this, artistTitle))
				.build();
	}
}
