package com.edavtyan.materialplayer.notification;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.IBinder;

import com.edavtyan.materialplayer.player.Player;
import com.edavtyan.materialplayer.service.PlayerService;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;

import lombok.Setter;

public class PlayerNotificationModel
		implements ServiceConnection,
				   Player.OnNewTrackListener,
				   Player.OnPlayPauseListener {

	private final Context context;
	private final AlbumArtProvider albumArtProvider;

	private PlayerService service;

	private @Setter OnNewTrackListener onNewTrackListener;
	private @Setter OnPlayPauseListener onPlayPauseListener;

	public interface OnNewTrackListener {
		void onNewTrack();
	}

	public interface OnPlayPauseListener {
		void onPlayPause();
	}

	public PlayerNotificationModel(Context context, AlbumArtProvider albumArtProvider) {
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

	public boolean isPlaying() {
		return service.getPlayer().isPlaying();
	}

	public Track getTrack() {
		return service.getPlayer().getCurrentTrack();
	}

	public Bitmap getArt() {
		return albumArtProvider.load(getTrack());
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder binder) {
		service = ((PlayerService.PlayerBinder) binder).getService();
		service.getPlayer().setOnNewTrackListener(this);
		service.getPlayer().setOnPlayPauseListener(this);
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
