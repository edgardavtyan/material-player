package com.edavtyan.materialplayer.components.nowplaying;

import android.content.Intent;

import com.edavtyan.materialplayer.MusicPlayerService;
import com.edavtyan.materialplayer.components.player.MusicPlayer;
import com.edavtyan.materialplayer.components.player.NowPlayingQueue;
import com.edavtyan.materialplayer.components.player.RepeatMode;
import com.edavtyan.materialplayer.utils.ArtProvider;

import java.io.File;

import lombok.Setter;

public class NowPlayingModel implements MusicPlayer.OnPreparedListener {
	private MusicPlayer player;
	private NowPlayingQueue queue;
	private MusicPlayerService service;
	private @Setter OnNewTrackListener onNewTrackListener;

	interface OnNewTrackListener {
		void onNewTrack();
	}

	public NowPlayingModel(MusicPlayerService service) {
		this.service = service;
		this.player = service.getPlayer();
		this.player.setOnPreparedListener(this);
		this.queue = service.getQueue();
	}

	/*
	 * Track info
	 */

	public CharSequence getTrackTitle() {
		return queue.getCurrentTrack().getTitle();
	}

	public CharSequence getArtistTitle() {
		return queue.getCurrentTrack().getArtistTitle();
	}

	public CharSequence getAlbumTitle() {
		return queue.getCurrentTrack().getAlbumTitle();
	}

	public File getArt() {
		return ArtProvider.fromTrack(queue.getCurrentTrack());
	}

	public int getDuration() {
		return player.getDuration();
	}

	public int getPosition() {
		return player.getPosition();
	}

	/*
	 * Player controls
	 */

	public void toggleShuffle() {
		queue.toggleShuffling();
	}

	public boolean isShuffling() {
		return queue.isShuffling();
	}

	public void toggleRepeat() {
		queue.toggleRepeatMode();
	}

	public RepeatMode getRepeatMode() {
		return queue.getRepeatMode();
	}

	public void rewind() {
		player.movePrev();
		player.prepare();
	}

	public void pause() {
		player.pause();
	}

	public void resume() {
		player.resume();
	}

	public void fastForward() {
		player.moveNext();
		player.prepare();
	}

	public boolean isPlaying() {
		return player.isPlaying();
	}

	public void seekTo(int progress) {
		player.setPosition(progress);
	}

	/*
	 * Events
	 */

	@Override
	public void onPrepared() {
		if (onNewTrackListener != null) {
			onNewTrackListener.onNewTrack();
		}

		service.sendBroadcast(new Intent(MusicPlayerService.SEND_NEW_TRACK));
	}
}
