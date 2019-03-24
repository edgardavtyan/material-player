package com.edavtyan.materialplayer.ui.detail.artist_detail;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.playlist.models.PlaylistPresenter;
import com.edavtyan.materialplayer.lib.transition.SharedTransitionsManager;
import com.edavtyan.materialplayer.lib.transition.SourceSharedViews;
import com.edavtyan.materialplayer.ui.Navigator;
import com.edavtyan.materialplayer.ui.detail.lib.ParallaxHeaderListActivity;
import com.edavtyan.materialplayer.ui.lists.album_list.AlbumListView;

import java.util.List;

import javax.inject.Inject;

public class ArtistDetailActivity
		extends ParallaxHeaderListActivity
		implements AlbumListView {

	public static final String EXTRA_ARTIST_TITLE = "extra_artistTitle";

	@Inject Navigator navigator;
	@Inject SharedTransitionsManager transitionsManager;
	@Inject PlaylistPresenter playlistPresenter;
	@Inject ArtistDetailPresenter presenter;

	private @Nullable SourceSharedViews sharedViews;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		getComponent().inject(this);
		super.onCreate(savedInstanceState);
		if (sharedViews != null) {
			transitionsManager.updateSourceViews(sharedViews);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.menu_artist_detail, menu);
		return result;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_sort_name:
			presenter.onSortByName();
			return true;
		case R.id.menu_sort_year:
			presenter.onSortByDate();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void showPlaylistSelectionDialog(List<Track> tracks) {
		playlistPresenter.onAddToPlaylistClick(tracks);
	}

	public void setArtistTitle(String title) {
		setTitle(title);
	}

	public void setArtistInfo(int albumsCount, int tracksCount) {
		Resources res = getResources();
		String portraitTopInfo = res.getQuantityString(R.plurals.albums, albumsCount, albumsCount);
		String portraitBottomInfo = res.getQuantityString(R.plurals.tracks, tracksCount, tracksCount);
		String landscapeInfo = res.getString(R.string.pattern_artist_info, portraitTopInfo, portraitBottomInfo);
		setInfo(portraitTopInfo, portraitBottomInfo, landscapeInfo);
	}

	public void setArtistImage(Bitmap image) {
		setImage(image, R.drawable.fallback_artist);
	}

	public void gotoAlbumDetail(int albumId, SourceSharedViews sharedViews) {
		this.sharedViews = sharedViews;
		transitionsManager.pushSourceViews(sharedViews);
		navigator.gotoAlbumDetail(this, albumId, sharedViews.build());
	}

	protected ArtistDetailDIComponent getComponent() {
		String artistTitle = getIntent().getStringExtra(EXTRA_ARTIST_TITLE);
		return DaggerArtistDetailDIComponent
				.builder()
				.appDIComponent(((App) getApplication()).getAppComponent())
				.artistDetailDIModule(new ArtistDetailDIModule(this, artistTitle))
				.build();
	}
}

