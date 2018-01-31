package com.edavtyan.materialplayer.screens.detail.artist_detail;

import android.animation.Animator;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.screens.Navigator;
import com.edavtyan.materialplayer.screens.detail.lib.ParallaxHeaderListCompactActivity;
import com.edavtyan.materialplayer.screens.lists.artist_list.ArtistListFragment;
import com.edavtyan.materialplayer.utils.ViewUtils;

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

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		getComponent().inject(this);
		super.onCreate(savedInstanceState);
		ButterKnife.bind(this);
		sharedArtView.setVisibility(View.VISIBLE);
		artView.setVisibility(View.INVISIBLE);

		sharedArtView.post(() -> {
			int[] artViewLocation = new int[2];
			artView.getLocationOnScreen(artViewLocation);

			int[] sharedArtViewLocation = new int[2];
			sharedArtView.getLocationOnScreen(sharedArtViewLocation);

			mainWrapper.setAlpha(0);
			getWindow().setBackgroundDrawableResource(android.R.color.transparent);

			ArtistDetailIntent intent = new ArtistDetailIntent(getIntent());
			ViewUtils.setSize(sharedArtView, artView.getWidth(), artView.getHeight());
			sharedArtView.setX(intent.getSharedArtX() - sharedArtViewLocation[0]);
			sharedArtView.setY(intent.getSharedArtY() - sharedArtViewLocation[1]);
			sharedArtView.setScaleX((float) intent.getSharedArtWidth() / artView.getWidth());
			sharedArtView.setScaleY((float) intent.getSharedArtHeight() / artView.getHeight());
			sharedArtView.setPivotX(0);
			sharedArtView.setPivotY(0);
			sharedArtView.animate()
						 .x(artView.getX() + artViewWrapper.getPaddingLeft())
						 .y(artView.getY() + appbar.getHeight())
						 .scaleX(1)
						 .scaleY(1)
						 .setDuration(500)
						 .setListener(new Animator.AnimatorListener() {
							 @Override
							 public void onAnimationStart(Animator animation) {
								 ArtistListFragment.onNextActivityCreatedListener.run();
							 }

							 @Override
							 public void onAnimationEnd(Animator animation) {
//								 sharedArtView.setVisibility(View.INVISIBLE);
//								 artView.setVisibility(View.VISIBLE);
							 }

							 @Override
							 public void onAnimationCancel(Animator animation) {}

							 @Override
							 public void onAnimationRepeat(Animator animation) {}
						 })
						 .start();
			mainWrapper.animate().alpha(1);
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
