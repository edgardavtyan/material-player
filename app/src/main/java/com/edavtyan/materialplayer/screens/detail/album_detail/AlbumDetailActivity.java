package com.edavtyan.materialplayer.screens.detail.album_detail;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.ed.libsutils.utils.WindowUtils;
import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.screens.Navigator;
import com.edavtyan.materialplayer.screens.detail.lib.ParallaxHeaderListActivity;
import com.edavtyan.materialplayer.screens.lists.track_list.TrackListView;
import com.edavtyan.materialplayer.screens.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.transition.SharedTransitionsManager;
import com.edavtyan.materialplayer.transition.SourceSharedViews;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumDetailActivity
		extends ParallaxHeaderListActivity
		implements TrackListView {

	public static final String EXTRA_ALBUM_ID = "extra_albumId";

	@Inject Navigator navigator;
	@Inject SharedTransitionsManager transitionsManager;

	@BindView(R.id.art) ImageView artView;
	@BindView(R.id.list) RecyclerView list;
	@BindView(R.id.list_background) @Nullable View listBackground;

	private SourceSharedViews sharedViews;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		getComponent().inject(this);
		super.onCreate(savedInstanceState);
		ButterKnife.bind(this);
		if (sharedViews != null) {
			transitionsManager.updateSourceViews(sharedViews);
		}
	}

	public void setAlbumTitle(String albumTitle) {
		setTitle(albumTitle);
	}

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

	public void setAlbumImage(Bitmap image) {
		setImage(image, R.drawable.fallback_cover);
	}

	public void gotoNowPlaying() {
		sharedViews = new SourceSharedViews(
				Pair.create(artView, "art"),
				Pair.create(list, "list"));

		if (WindowUtils.isPortrait(this)) {
			NowPlayingActivity.listBitmap = viewToBitmap(list);
			sharedViews.add(Pair.create(listBackground, "listBackground"));
		}

		transitionsManager.pushSourceViews(sharedViews);
		navigator.gotoNowPlaying(this, sharedViews.build());
	}

	protected AlbumDetailComponent getComponent() {
		int albumId = getIntent().getIntExtra(EXTRA_ALBUM_ID, -1);
		return DaggerAlbumDetailComponent
				.builder()
				.appComponent(((App) getApplication()).getAppComponent())
				.albumDetailFactory(new AlbumDetailFactory(this, albumId))
				.build();
	}

	private Bitmap viewToBitmap(View view) {
		view.requestLayout();
		Bitmap viewBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_4444);
		Canvas canvas = new Canvas(viewBitmap);
		view.draw(canvas);
		return viewBitmap;
	}
}
