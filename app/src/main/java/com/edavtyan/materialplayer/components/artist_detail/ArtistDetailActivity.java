package com.edavtyan.materialplayer.components.artist_detail;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.album_all.AlbumListAdapter;
import com.edavtyan.materialplayer.lib.mvp.parallax_list.ParallaxHeaderListActivity;

import static com.edavtyan.materialplayer.components.artist_detail.ArtistDetailMvp.EXTRA_ARTIST_TITLE;

public class ArtistDetailActivity
		extends ParallaxHeaderListActivity
		implements ArtistDetailMvp.View {

	private Navigator navigator;
	private AlbumListAdapter adapter;

	@Override
	public void setArtistTitle(String title) {
		setHeaderTitle(title);
	}

	@Override
	public void setArtistInfo(int albumsCount, int tracksCount) {
		Resources res = getResources();
		String albumsCountStr = res.getQuantityString(R.plurals.albums, albumsCount, albumsCount);
		String tracksCountStr = res.getQuantityString(R.plurals.tracks, tracksCount, tracksCount);
		String info = getString(R.string.pattern_artist_info, albumsCountStr, tracksCountStr);
		setHeaderInfo(info);
	}

	@Override
	public void setArtistImage(Bitmap art) {
		setHeaderImage(art, R.drawable.fallback_artist);
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ArtistDetailFactory factory = getDI();
		adapter = factory.getAdapter();
		init(adapter, factory.getPresenter());
		navigator = factory.getNavigator();
	}

	@Override
	public void goToAlbumDetail(int albumId) {
		navigator.gotoAlbumDetail(albumId);
	}

	@Override
	public void notifyDataSetChanged() {
		adapter.notifyDataSetChangedNonFinal();
	}

	protected ArtistDetailFactory getDI() {
		String artistTitle = getIntent().getStringExtra(EXTRA_ARTIST_TITLE);
		return ((App) getApplication()).getArtistDetailDI(this, this, artistTitle);
	}
}
