package com.edavtyan.materialplayer.app.models.player;

import android.content.Context;

import com.h6ah4i.android.media.IBasicMediaPlayer;
import com.h6ah4i.android.media.opensl.OpenSLMediaPlayer;

import java.io.IOException;

import lombok.Setter;

public class MusicPlayer
		implements OpenSLMediaPlayer.OnCompletionListener, OpenSLMediaPlayer.OnPreparedListener {
	private final IBasicMediaPlayer player;
	private final NowPlayingQueue queue;
	private @Setter OnPreparedListener onPreparedListener;
	private @Setter OnPlaybackStateChangedListener onPlaybackStateChangedListener;

	/* Event listeners */

	public interface OnPreparedListener {
		void onPrepared();
	}

	public interface OnPlaybackStateChangedListener {
		void onPlaybackStateChanged(PlaybackState state);
	}

	/* Constructors */

	public MusicPlayer(Context context, IBasicMediaPlayer player, NowPlayingQueue queue) {
		this.player = player;
		this.queue = queue;
		player.setOnCompletionListener(this);
		player.setOnPreparedListener(this);
	}

	/* MusicPlayer methods */

	public void prepare() {
		try {
			player.reset();
			player.setDataSource(queue.getCurrentTrack().getPath());
			player.prepare();
			player.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void resume() {
		player.start();
		raiseOnPlaybackStateChanged(PlaybackState.RESUMED);
	}

	public void pause() {
		player.pause();
		raiseOnPlaybackStateChanged(PlaybackState.PAUSED);
	}

	public void moveNext() {
		queue.moveToNext();
	}

	public void movePrev() {
		if (player.getCurrentPosition() > 5000) {
			player.seekTo(0);
		} else {
			queue.moveToPrev();
		}
	}

	public boolean isPlaying() {
		return player.isPlaying();
	}

	public int getDuration() {
		return player.getDuration();
	}

	public int getPosition() {
		return player.getCurrentPosition();
	}

	public void setPosition(int position) {
		player.seekTo(position);
	}

	/* OpenSLMediaPlayer event listeners */

	@Override
	public void onCompletion(IBasicMediaPlayer mediaPlayer) {
		if (player.getCurrentPosition() < 500) return;

		switch (queue.getRepeatMode()) {
		case NO_REPEAT:
			if (queue.isAtLastTrack()) player.pause();
			break;

		case REPEAT:
			moveNext();
			prepare();
			break;

		case REPEAT_ONE:
			player.seekTo(0);
			prepare();
			break;
		}
	}

	@Override
	public void onPrepared(IBasicMediaPlayer mediaPlayer) {
		player.start();
		if (onPreparedListener != null) {
			onPreparedListener.onPrepared();
		}
	}

	/* Private methods */

	private void raiseOnPlaybackStateChanged(PlaybackState state) {
		if (onPlaybackStateChangedListener != null) {
			onPlaybackStateChangedListener.onPlaybackStateChanged(state);
		}
	}
}
