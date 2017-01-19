package com.edavtyan.materialplayer.components;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.album_detail.AlbumDetailActivityCompact;
import com.edavtyan.materialplayer.components.album_detail.AlbumDetailMvp;
import com.edavtyan.materialplayer.components.artist_detail.ArtistDetailActivity;
import com.edavtyan.materialplayer.components.audioeffects.AudioEffectsActivity;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.components.now_playing_queue.NowPlayingQueueActivity;
import com.edavtyan.materialplayer.components.prefs.PrefActivity;

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
		Intent intent = new Intent(context, AlbumDetailActivityCompact.class);
		intent.putExtra(AlbumDetailMvp.EXTRA_ALBUM_ID, albumId);
		context.startActivity(intent);
	}

	public void gotoNowPlaying() {
		Intent intent = new Intent(context, NowPlayingActivity.class);
		context.startActivity(intent);
	}

	public void gotoNowPlayingQueue() {
		Intent intent = new Intent(context, NowPlayingQueueActivity.class);
		context.startActivity(intent);
		((AppCompatActivity) context).overridePendingTransition(R.anim.fade_in, android.R.anim.fade_out);
	}

	public void gotoAudioEffects() {
		Intent intent = new Intent(context, AudioEffectsActivity.class);
		context.startActivity(intent);
	}

	public void gotoSettings() {
		Intent intent = new Intent(context, PrefActivity.class);
		context.startActivity(intent);
	}
}
