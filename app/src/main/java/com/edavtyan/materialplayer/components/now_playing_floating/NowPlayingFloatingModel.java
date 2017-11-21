package com.edavtyan.materialplayer.components.now_playing_floating;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.IBinder;

import com.edavtyan.materialplayer.components.player.Player;
import com.edavtyan.materialplayer.components.player.PlayerService;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;

import lombok.Setter;

public class NowPlayingFloatingModel
		implements NowPlayingFloatingMvp.Model,
				   Player.OnNewTrackListener {

	private final Context context;
	private final AlbumArtProvider albumArtProvider;

	private PlayerService service;
	
	private @Setter OnNewTrackListener onNewTrackListener;
	private @Setter OnServiceConnectedListener onServiceConnectedListener;

	public NowPlayingFloatingModel(Context context, AlbumArtProvider albumArtProvider) {
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
		context.unbindService(this);
	}

	@Override
	public void togglePlayPause() {
		service.getPlayer().playPause();
	}

	@Override
	public Track getNowPlayingTrack() {
		return service.getPlayer().getCurrentTrack();
	}

	@Override
	public Bitmap getNowPlayingTrackArt() {
		return albumArtProvider.load(getNowPlayingTrack());
	}

	@Override
	public boolean isPlaying() {
		return service.getPlayer().isPlaying();
	}

	@Override
	public boolean hasData() {
		return service.getPlayer().hasData();
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder binder) {
		service = ((PlayerService.PlayerBinder) binder).getService();
		service.getPlayer().setOnNewTrackListener(this);

		if (onServiceConnectedListener != null) {
			onServiceConnectedListener.onServiceConnected();
		}
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
	}

	@Override
	public void onNewTrack() {
		if (onNewTrackListener != null) {
			onNewTrackListener.onNewTrack();
		}
	}
}
