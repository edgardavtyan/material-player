package com.edavtyan.materialplayer.ui.now_playing;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer.player.Player;
import com.edavtyan.materialplayer.player.RepeatMode;
import com.edavtyan.materialplayer.player.ShuffleMode;
import com.edavtyan.materialplayer.service.PlayerService;

import lombok.Setter;

public class NowPlayingModel
		implements ServiceConnection,
				   Player.OnNewTrackListener,
				   Player.OnPlayPauseListener {

	private final Context context;
	private final AlbumArtProvider albumArtProvider;

	private PlayerService service;

	private @Setter @Nullable OnModelBoundListener onModelBoundListener;
	private @Setter @Nullable OnNewTrackListener onNewTrackListener;
	private @Setter @Nullable OnPlayPauseListener onPlayPauseListener;

	public interface OnModelBoundListener {
		void onModelBound();
	}

	public interface OnNewTrackListener {
		void onNewTrack();
	}

	public interface OnPlayPauseListener {
		void onPlayPause();
	}

	public NowPlayingModel(Context context, AlbumArtProvider albumArtProvider) {
		this.context = context;
		this.albumArtProvider = albumArtProvider;
	}

	public void bind() {
		Intent intent = new Intent(context, PlayerService.class);
		context.bindService(intent, this, Context.BIND_AUTO_CREATE);
	}

	public void unbind() {
		service.getPlayer().removeOnNewTrackListener(this);
		service.getPlayer().removeOnPlayPauseListener(this);
		context.unbindService(this);
	}

	public RepeatMode getRepeatMode() {
		return service.getPlayer().getRepeatMode();
	}

	public ShuffleMode getShuffleMode() {
		return service.getPlayer().getShuffleMode();
	}

	public void toggleRepeatMode() {
		service.getPlayer().toggleRepeatMode();
	}

	public void toggleShuffleMode() {
		service.getPlayer().toggleShuffleMode();
	}

	public boolean isPlaying() {
		return service.getPlayer().isPlaying();
	}

	public void playPause() {
		service.getPlayer().playPause();
	}

	public void rewind() {
		service.getPlayer().skipToPrevious();
	}

	public void fastForward() {
		service.getPlayer().skipToNext();
	}

	public void seek(int positionMS) {
		service.getPlayer().setSeek(positionMS);
	}

	public int getDuration() {
		return (int) service.getPlayer().getCurrentTrack().getDuration();
	}

	public int getPosition() {
		return (int) service.getPlayer().getSeek();
	}

	public String getTitle() {
		return service.getPlayer().getCurrentTrack().getTitle();
	}

	public String getArtist() {
		return service.getPlayer().getCurrentTrack().getArtistTitle();
	}

	public String getAlbum() {
		return service.getPlayer().getCurrentTrack().getAlbumTitle();
	}

	public Bitmap getArt() {
		return albumArtProvider.load(service.getPlayer().getCurrentTrack());
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder binder) {
		service = ((PlayerService.PlayerBinder) binder).getService();
		service.getPlayer().setOnNewTrackListener(this);
		service.getPlayer().setOnPlayPauseListener(this);
		if (onModelBoundListener != null) {
			onModelBoundListener.onModelBound();
		}
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
	}

	@Override
	public void onNewTrack() {
		if (onNewTrackListener != null) onNewTrackListener.onNewTrack();
	}

	@Override
	public void onPlayPause() {
		if (onPlayPauseListener != null) onPlayPauseListener.onPlayPause();
	}
}
