package com.edavtyan.materialplayer.components;

import android.content.Context;
import android.content.Intent;

import com.edavtyan.materialplayer.components.album_detail.AlbumDetailActivity;
import com.edavtyan.materialplayer.components.artist_detail.ArtistDetailActivity;
import com.edavtyan.materialplayer.components.nowplaying.NowPlayingActivity;

public class Navigator {
	private final Context context;

	public Navigator(Context context) {
		this.context = context;
	}

	public void gotoArtistDetail(String artistTitle) {
		Intent intent = new Intent(context, ArtistDetailActivity.class);
		intent.putExtra(ArtistDetailActivity.EXTRA_ARTIST_TITLE, artistTitle);
		context.startActivity(intent);
	}

	public void gotoAlbumDetail(int albumId) {
		Intent intent = new Intent(context, AlbumDetailActivity.class);
		intent.putExtra(AlbumDetailActivity.EXTRA_ALBUM_ID, Integer.toString(albumId));
		context.startActivity(intent);
	}

	public void gotoNowPlaying() {
		Intent intent = new Intent(context, NowPlayingActivity.class);
		context.startActivity(intent);
	}
}
