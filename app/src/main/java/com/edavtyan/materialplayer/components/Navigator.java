package com.edavtyan.materialplayer.components;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.audio_effects.AudioEffectsActivity;
import com.edavtyan.materialplayer.components.detail.album_detail.AlbumDetailActivityCompact;
import com.edavtyan.materialplayer.components.detail.album_detail.AlbumDetailActivityNormal;
import com.edavtyan.materialplayer.components.detail.album_detail.AlbumDetailView;
import com.edavtyan.materialplayer.components.detail.artist_detail.ArtistDetailActivityCompact;
import com.edavtyan.materialplayer.components.detail.artist_detail.ArtistDetailActivityNormal;
import com.edavtyan.materialplayer.components.detail.artist_detail.ArtistDetailView;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.components.now_playing_queue.NowPlayingQueueActivity;
import com.edavtyan.materialplayer.components.prefs.PrefActivity;
import com.edavtyan.materialplayer.components.search.SearchActivity;

public class Navigator {
	private final Context context;

	public Navigator(Context context) {
		this.context = context;
	}

	public void gotoArtistDetailNormal(String artistTitle) {
		Intent intent = new Intent(context, ArtistDetailActivityNormal.class);
		intent.putExtra(ArtistDetailView.EXTRA_ARTIST_TITLE, artistTitle);
		context.startActivity(intent);
	}

	public void gotoArtistDetailCompact(String artistTitle) {
		Intent intent = new Intent(context, ArtistDetailActivityCompact.class);
		intent.putExtra(ArtistDetailView.EXTRA_ARTIST_TITLE, artistTitle);
		context.startActivity(intent);
	}

	public void gotoAlbumDetailNormal(int albumId) {
		Intent intent = new Intent(context, AlbumDetailActivityNormal.class);
		intent.putExtra(AlbumDetailView.EXTRA_ALBUM_ID, albumId);
		context.startActivity(intent);
	}

	public void gotoAlbumDetailCompact(int albumId) {
		Intent intent = new Intent(context, AlbumDetailActivityCompact.class );
		intent.putExtra(AlbumDetailView.EXTRA_ALBUM_ID, albumId);
		context.startActivity(intent);
	}

	public void gotoNowPlaying() {
		Intent intent = new Intent(context, NowPlayingActivity.class);
		context.startActivity(intent);
	}

	public void gotoNowPlayingQueue(Activity activity) {
		Intent intent = new Intent(context, NowPlayingQueueActivity.class);
		context.startActivity(intent);
		activity.overridePendingTransition(R.anim.fade_in, android.R.anim.fade_out);
	}

	public void gotoAudioEffects() {
		Intent intent = new Intent(context, AudioEffectsActivity.class);
		context.startActivity(intent);
	}

	public void gotoSettings() {
		Intent intent = new Intent(context, PrefActivity.class);
		context.startActivity(intent);
	}

	public void gotoSearch() {
		Intent intent = new Intent(context, SearchActivity.class);
		context.startActivity(intent);
	}
}
