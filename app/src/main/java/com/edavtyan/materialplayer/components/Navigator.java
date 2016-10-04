package com.edavtyan.materialplayer.components;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.album_detail.AlbumDetailActivity;
import com.edavtyan.materialplayer.components.artist_detail.ArtistDetailActivity;
import com.edavtyan.materialplayer.components.now_playing_queue.PlaylistActivity;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;

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

	public void gotoNowPlayingQueue() {
		Intent intent = new Intent(context, PlaylistActivity.class);
		context.startActivity(intent);
		((AppCompatActivity) context).overridePendingTransition(R.anim.fade_in, android.R.anim.fade_out);
	}
}
