package com.edavtyan.materialplayer.components.artist_detail;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.mvp.parallax_list.ParallaxHeaderListActivity;
import com.edavtyan.materialplayer.components.album_detail.AlbumDetailActivity;

public class ArtistDetailActivity
		extends ParallaxHeaderListActivity
		implements ArtistDetailMvp.View {

	public static final String EXTRA_ARTIST_TITLE = "extra_artistTitle";

	public static void startActivity(Context context, String artistTitle) {
		Intent intent = new Intent(context, ArtistDetailActivity.class);
		intent.putExtra(EXTRA_ARTIST_TITLE, artistTitle);
		context.startActivity(intent);
	}

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
	public void setArtistImage(Bitmap art, int fallbackImage) {
		setHeaderImage(art, fallbackImage);
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ArtistDetailDI di = getDI();
		init(di.provideAdapter(), di.providePresenter());
	}

	@Override
	public void goToAlbumDetail(int albumId) {
		AlbumDetailActivity.startActivity(this, albumId);
	}

	protected ArtistDetailDI getDI() {
		String artistTitle = getIntent().getStringExtra(EXTRA_ARTIST_TITLE);
		return ((App) getApplication()).getArtistDetailDI(this, this, artistTitle);
	}
}
