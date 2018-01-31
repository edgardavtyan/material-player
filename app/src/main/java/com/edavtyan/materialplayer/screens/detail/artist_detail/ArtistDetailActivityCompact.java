package com.edavtyan.materialplayer.screens.detail.artist_detail;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.ContentFrameLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ed.libsutils.utils.ViewUtils;
import com.ed.libsutils.utils.WindowUtils;
import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.screens.Navigator;
import com.edavtyan.materialplayer.screens.detail.lib.ParallaxHeaderListCompactActivity;
import com.edavtyan.materialplayer.screens.lists.artist_list.ArtistListFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArtistDetailActivityCompact
		extends ParallaxHeaderListCompactActivity
		implements ArtistDetailView {

	@Inject Navigator navigator;

	@BindView(R.id.shared_art) ImageView sharedArtView;
	@BindView(R.id.art) ImageView artView;
	@BindView(R.id.art_wrapper) FrameLayout artViewWrapper;
	@BindView(R.id.main_wrapper) LinearLayout mainWrapper;
	@BindView(R.id.appbar) AppBarLayout appbar;
	@BindView(android.R.id.content) ContentFrameLayout contentView;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		getComponent().inject(this);
		super.onCreate(savedInstanceState);
		ButterKnife.bind(this);
		sharedArtView.setVisibility(View.VISIBLE);
		artView.setVisibility(View.INVISIBLE);

		sharedArtView.post(() -> {
			mainWrapper.setAlpha(0);
			mainWrapper.animate().alpha(1);

			getWindow().setBackgroundDrawableResource(android.R.color.transparent);

			int statusBarHeight = WindowUtils.getStatusBarHeight(this);
			int[] artViewLocation = ViewUtils.getLocationOnScreen(artView);
			int[] sharedArtViewLocation = ViewUtils.getLocationOnScreen(sharedArtView);

			ArtistDetailIntent intent = new ArtistDetailIntent(getIntent());
			ViewUtils.setSize(sharedArtView, artView.getWidth(), artView.getHeight());
			sharedArtView.setX(intent.getSharedArtX() - sharedArtViewLocation[0]);
			sharedArtView.setY(intent.getSharedArtY() - sharedArtViewLocation[1]);
			sharedArtView.setScaleX((float) intent.getSharedArtWidth() / artView.getWidth());
			sharedArtView.setScaleY((float) intent.getSharedArtHeight() / artView.getHeight());
			sharedArtView.setPivotX(0);
			sharedArtView.setPivotY(0);
			sharedArtView.animate()
						 .x(artViewLocation[0])
						 .y(artViewLocation[1] - statusBarHeight)
						 .scaleX(1)
						 .scaleY(1)
						 .setDuration(500)
						 .withStartAction(() -> new Handler().postDelayed(ArtistListFragment.sharedViews::hide, 50))
						 .withEndAction(ArtistListFragment.sharedViews::show)
						 .start();
		});
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
		sharedArtView.setImageBitmap(image);
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
				.appComponent(((App) getApplication()).getAppComponent())
				.artistDetailFactory(new ArtistDetailFactory(this, this, artistTitle))
				.build();
	}
}
