package com.edavtyan.materialplayer.components.artist_detail;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.lib.base.BaseActivity;
import com.edavtyan.materialplayer.modular.activity.BaseToolbarModule;
import com.edavtyan.materialplayer.modular.activity.ParallaxHeaderListModule;
import com.edavtyan.materialplayer.views.DetailActivityViews;

import static com.edavtyan.materialplayer.components.artist_detail.ArtistDetailMvp.EXTRA_ARTIST_TITLE;

public class ArtistDetailActivity
		extends BaseActivity
		implements ArtistDetailMvp.View {

	private Navigator navigator;
	private ArtistDetailAdapter adapter;
	private DetailActivityViews views;

	@Override
	public void setArtistTitle(String title) {
		views.setTitle(title);
	}

	@Override
	public void setArtistInfo(int albumsCount, int tracksCount) {
		Resources res = getResources();
		String albumsCountStr = res.getQuantityString(R.plurals.albums, albumsCount, albumsCount);
		String tracksCountStr = res.getQuantityString(R.plurals.tracks, tracksCount, tracksCount);
		String info = getString(R.string.pattern_artist_info, albumsCountStr, tracksCountStr);
		views.setInfo(info);
	}

	@Override
	public void setArtistImage(Bitmap art) {
		views.setArt(art, R.drawable.fallback_artist);
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_detail;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ArtistDetailFactory factory = getDI();

		adapter = factory.getAdapter();
		navigator = factory.getNavigator();
		views = new DetailActivityViews(this);

		addModule(new ParallaxHeaderListModule(this, factory.getAdapter(), factory.getPresenter()));
		addModule(new BaseToolbarModule(this));
	}

	@Override
	public void gotoAlbumDetail(int albumId) {
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
