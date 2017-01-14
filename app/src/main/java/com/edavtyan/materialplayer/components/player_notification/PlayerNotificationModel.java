package com.edavtyan.materialplayer.components.player_notification;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.IBinder;

import com.edavtyan.materialplayer.components.player.PlayerMvp;
import com.edavtyan.materialplayer.components.player.PlayerService;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;

import lombok.Setter;

public class PlayerNotificationModel
		implements ServiceConnection, PlayerNotificationMvp.Model,
				   PlayerMvp.Player.OnNewTrackListener, PlayerMvp.Player.OnPlayPauseListener {

	private final Context context;
	private final AlbumArtProvider albumArtProvider;

	private PlayerService service;

	private @Setter OnNewTrackListener onNewTrackListener;
	private @Setter OnPlayPauseListener onPlayPauseListener;

	public PlayerNotificationModel(Context context, AlbumArtProvider albumArtProvider) {
		this.context = context;
		this.albumArtProvider = albumArtProvider;
	}

	@Override
	public void bind() {
		Intent intent = new Intent(context, PlayerService.class);
		context.bindService(intent, this, Context.BIND_AUTO_CREATE);
	}

	@Override
	public void unbind() {
		service.getPlayer().removeOnNewTrackListener(this);
		service.getPlayer().removeOnPlayPauseListener(this);
		context.unbindService(this);
	}

	@Override
	public boolean isPlaying() {
		return service.getPlayer().isPlaying();
	}

	@Override
	public Track getTrack() {
		return service.getPlayer().getCurrentTrack();
	}

	@Override
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
