package com.edavtyan.materialplayer.notification;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer.player.Player;
import com.edavtyan.materialplayer.service.PlayerService;

import lombok.Setter;

public class PlayerNotificationModel
		implements ServiceConnection,
				   Player.OnNewTrackListener,
				   Player.OnPlayPauseListener {

	private final Context context;
	private final AlbumArtProvider albumArtProvider;

	private PlayerService service;

	private @Setter @Nullable OnNewTrackListener onNewTrackListener;
	private @Setter @Nullable OnPlayPauseListener onPlayPauseListener;
	private @Setter @Nullable OnServiceConnectedListener onServiceConnectedListener;

	public interface OnNewTrackListener {
		void onNewTrack();
	}

	public interface OnPlayPauseListener {
		void onPlayPause();
	}

	public interface OnServiceConnectedListener {
		void onServiceConnected();
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

	public boolean hasData() {
		return service.getPlayer().hasData();
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

		if (onServiceConnectedListener != null) {
			onServiceConnectedListener.onServiceConnected();
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
