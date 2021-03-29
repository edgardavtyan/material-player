package com.edavtyan.materialplayer.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.edavtyan.materialplayer.ui.audio_effects.AudioEffectsActivity;
import com.edavtyan.materialplayer.ui.detail.album_detail.AlbumDetailActivity;
import com.edavtyan.materialplayer.ui.detail.artist_detail.ArtistDetailActivity;
import com.edavtyan.materialplayer.ui.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.ui.prefs.PrefActivity;
import com.edavtyan.materialplayer.ui.search.SearchActivity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class Navigator {
	private final Context context;

	public Navigator(Context context) {
		this.context = context;
	}

	public void gotoArtistDetailCompact(Activity activity, String artistTitle, Bundle bundle) {
		Intent intent = new Intent(activity, ArtistDetailActivity.class);
		intent.putExtra(ArtistDetailActivity.EXTRA_ARTIST_TITLE, artistTitle);
		intent.putExtras(bundle);
		activity.startActivity(intent);
		activity.overridePendingTransition(0, android.R.anim.fade_out);
	}

	public void gotoAlbumDetail(Activity activity, int albumId, Bundle bundle) {
		Intent intent = new Intent(activity, AlbumDetailActivity.class);
		intent.putExtra(AlbumDetailActivity.EXTRA_ALBUM_ID, albumId);
		intent.putExtras(bundle);
		activity.startActivity(intent);
		activity.overridePendingTransition(0, android.R.anim.fade_out);
	}

	public void gotoNowPlaying(Activity activity, Bundle bundle) {
		Intent intent = new Intent(activity, NowPlayingActivity.class);
		intent.putExtras(bundle);
		activity.startActivity(intent);
		activity.overridePendingTransition(0, android.R.anim.fade_out);
	}

	public void gotoAudioEffects() {
		Intent intent = new Intent(context, AudioEffectsActivity.class);
		intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	public void gotoSettings() {
		Intent intent = new Intent(context, PrefActivity.class);
		intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	public void gotoSearch() {
		Intent intent = new Intent(context, SearchActivity.class);
		context.startActivity(intent);
	}
}
