package com.edavtyan.materialplayer.ui.detail.album_detail;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.playlist.models.PlaylistPresenter;
import com.edavtyan.materialplayer.lib.transition.SharedTransitionsManager;
import com.edavtyan.materialplayer.ui.Navigator;
import com.edavtyan.materialplayer.ui.detail.lib.ParallaxHeaderListActivity;
import com.edavtyan.materialplayer.ui.lists.track_list.TrackListView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumDetailActivity extends ParallaxHeaderListActivity implements TrackListView {

	public static final String EXTRA_ALBUM_ID = "extra_albumId";

	@Inject Navigator navigator;
	@Inject SharedTransitionsManager transitionsManager;
	@Inject AlbumDetailPresenter presenter;
	@Inject PlaylistPresenter playlistPresenter;

	@BindView(R.id.art) ImageView artView;
	@BindView(R.id.list) RecyclerView list;
	@BindView(R.id.list_background) @Nullable View listBackground;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		getComponent().inject(this);
		super.onCreate(savedInstanceState);
		ButterKnife.bind(this);
	}

	@Override
	public void showPlaylistSelectionDialog(Track track) {
		playlistPresenter.onAddToPlaylistClick(track);
	}

	public void setAlbumTitle(String albumTitle) {
		setTitle(albumTitle);
	}

	public void setAlbumInfo(String artistTitle, int tracksCount, long duration) {
		long durationStr = TimeUnit.MILLISECONDS.toMinutes(duration);
		String tracksCountStr = getResources().getQuantityString(R.plurals.tracks, tracksCount, tracksCount);
		String portraitBottomInfo = getString(R.string.pattern_track_time_count, tracksCountStr, durationStr);
		String landscapeInfo = getString(R.string.pattern_album_info, artistTitle, tracksCountStr);
		setInfo(artistTitle, portraitBottomInfo, landscapeInfo);
	}

	public void setAlbumImage(@Nullable Bitmap image) {
		setImage(image, R.drawable.fallback_cover);
	}

	protected AlbumDetailDIComponent getComponent() {
		int albumId = getIntent().getIntExtra(EXTRA_ALBUM_ID, -1);
		return DaggerAlbumDetailDIComponent
				.builder()
				.appDIComponent(((App) getApplication()).getAppComponent())
				.albumDetailDIModule(new AlbumDetailDIModule(this, albumId))
				.build();
	}
}
