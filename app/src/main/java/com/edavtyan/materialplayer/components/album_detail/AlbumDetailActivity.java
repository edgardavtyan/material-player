package com.edavtyan.materialplayer.components.album_detail;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.lib.base.BaseToolbarActivity;
import com.edavtyan.materialplayer.modular.activity.ParallaxHeaderListModule;
import com.edavtyan.materialplayer.views.DetailActivityViews;

public class AlbumDetailActivity
		extends BaseToolbarActivity
		implements AlbumDetailMvp.View {

	private Navigator navigator;
	private AlbumDetailAdapter adapter;
	private DetailActivityViews views;

	public void setAlbumTitle(String title) {
		views.setTitle(title);
	}

	public void setAlbumInfo(String artistTitle, int tracksCount, long duration) {
		Resources res = getResources();
		String tracksCountStr = res.getQuantityString(R.plurals.tracks, tracksCount, tracksCount);
		String info = getString(R.string.pattern_album_info, artistTitle, tracksCountStr);
		views.setInfo(info);
	}

	public void setAlbumImage(Bitmap art) {
		views.setArt(art, R.drawable.fallback_cover);
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_detail;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AlbumDetailFactory factory = getDI();

		navigator = factory.getNavigator();
		adapter = factory.getAdapter();
		views = new DetailActivityViews(this);

		addModule(new ParallaxHeaderListModule(this, factory.getAdapter(), factory.getPresenter()));
	}

	@Override
	public void gotoNowPlaying() {
		navigator.gotoNowPlaying();
	}

	@Override
	public void notifyDataSetChanged() {
		adapter.notifyDataSetChanged();
	}

	protected AlbumDetailFactory getDI() {
		int albumId = getIntent().getIntExtra(AlbumDetailMvp.EXTRA_ALBUM_ID, -1);
		return ((App) getApplicationContext()).getAlbumDetailDI(this, this, albumId);
	}
}
