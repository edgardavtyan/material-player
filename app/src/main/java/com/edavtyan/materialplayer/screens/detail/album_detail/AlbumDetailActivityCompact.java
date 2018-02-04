package com.edavtyan.materialplayer.screens.detail.album_detail;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.screens.Navigator;
import com.edavtyan.materialplayer.screens.detail.lib.ParallaxHeaderListCompactActivity;
import com.edavtyan.materialplayer.screens.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.transition.CurrentSharedViews;
import com.edavtyan.materialplayer.transition.SourceSharedViews;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumDetailActivityCompact
		extends ParallaxHeaderListCompactActivity
		implements AlbumDetailView {

	@Inject Navigator navigator;
	@Inject CurrentSharedViews currentSharedViews;

	@BindView(R.id.art) ImageView artView;
	@BindView(R.id.list) RecyclerView list;
	@BindView(R.id.list_background) View listBackground;
	@BindView(R.id.list_header) View header;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		getComponent().inject(this);
		super.onCreate(savedInstanceState);
		ButterKnife.bind(this);
	}

	@Override
	public void setAlbumTitle(String albumTitle) {
		setTitle(albumTitle);
	}

	@Override
	public void setAlbumInfo(String artistTitle, int tracksCount, long duration) {
		String tracksCountStr = getResources().getQuantityString(
				R.plurals.tracks, tracksCount, tracksCount);
		long durationStr = TimeUnit.MILLISECONDS.toMinutes(duration);

		String portraitBottomInfo = getString(
				R.string.pattern_track_time_count, tracksCountStr, durationStr);
		String landscapeInfo = getString(
				R.string.pattern_album_info, artistTitle, tracksCountStr);

		setInfo(artistTitle, portraitBottomInfo, landscapeInfo);
	}

	@Override
	public void setAlbumImage(Bitmap image) {
		setImage(image, R.drawable.fallback_cover);
	}

	@Override
	public void gotoNowPlaying() {
		Fragment nowPlayingBarFragment = getSupportFragmentManager().findFragmentById(R.id.floating_nowplaying);
		View nowPlayingBarRoot = nowPlayingBarFragment.getView();

		NowPlayingActivity.listBitmap = viewToBitmap(list);
		NowPlayingActivity.nowPlayingBarBitmap = viewToBitmap(nowPlayingBarRoot);
		SourceSharedViews sharedViews = new SourceSharedViews(
				Pair.create(artView, "art"),
				Pair.create(list, "list"),
				Pair.create(listBackground, "listBackground"),
				Pair.create(nowPlayingBarRoot, "bar"));
		currentSharedViews.push(sharedViews);
		navigator.gotoNowPlaying(this, sharedViews.build());
	}

	protected AlbumDetailComponent getComponent() {
		int albumId = getIntent().getIntExtra(EXTRA_ALBUM_ID, -1);
		return DaggerAlbumDetailComponent
				.builder()
				.appComponent(((App) getApplication()).getAppComponent())
				.albumDetailFactory(new AlbumDetailFactory(this, this, albumId))
				.build();
	}

	private Bitmap viewToBitmap(View view) {
		Bitmap viewBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_4444);
		Canvas canvas = new Canvas(viewBitmap);
		view.draw(canvas);
		return viewBitmap;
	}
}
