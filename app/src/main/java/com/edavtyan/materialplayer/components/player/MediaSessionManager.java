package com.edavtyan.materialplayer.components.player;

import android.content.Context;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;

import static android.support.v4.media.session.PlaybackStateCompat.STATE_NONE;

public class MediaSessionManager {

	private final MediaSessionCompat mediaSession;
	private final Player player;
	private final PlaybackStateCompat.Builder playbackState;

	private final Player.OnPlayPauseListener playerOnPlayPauseListener
			= new Player.OnPlayPauseListener() {

		@Override
		public void onPlayPause() {
			int state = player.isPlaying()
					? PlaybackStateCompat.STATE_PLAYING
					: PlaybackStateCompat.STATE_PAUSED;

			playbackState.setState(state, player.getPosition(), 1.0f);
			mediaSession.setPlaybackState(playbackState.build());
		}
	};

	private final MediaSessionCompat.Callback mediaSessionCallback
			= new MediaSessionCompat.Callback() {

		@Override
		public void onPlay() {
			player.play();
		}

		@Override
		public void onPause() {
			player.pause();
		}

		@Override
		public void onSkipToNext() {
			player.skipToNext();
		}

		@Override
		public void onSkipToPrevious() {
			player.skipToPrevious();
		}
	};

	public MediaSessionManager(Context context, Player player) {
		this.player = player;
		this.playbackState = new PlaybackStateCompat.Builder();
		this.mediaSession = new MediaSessionCompat(context, context.getPackageName());
	}

	public void init() {
		player.setOnPlayPauseListener(playerOnPlayPauseListener);

		mediaSession.setFlags(
				MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
				MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
		mediaSession.setCallback(mediaSessionCallback);
		mediaSession.setActive(true);

		playbackState.setActions(
				PlaybackStateCompat.ACTION_PLAY |
				PlaybackStateCompat.ACTION_PAUSE |
				PlaybackStateCompat.ACTION_PLAY_PAUSE |
				PlaybackStateCompat.ACTION_SEEK_TO |
				PlaybackStateCompat.ACTION_SKIP_TO_NEXT |
				PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS |
				PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM);
		playbackState.setState(STATE_NONE, 0l, 1.0f);

		mediaSession.setPlaybackState(playbackState.build());
	}

	public void close() {
		player.removeOnPlayPauseListener(playerOnPlayPauseListener);
		mediaSession.setActive(false);
		mediaSession.release();
	}
}
