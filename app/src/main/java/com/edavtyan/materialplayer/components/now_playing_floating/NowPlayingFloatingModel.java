package com.edavtyan.materialplayer.components.now_playing_floating;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.edavtyan.materialplayer.components.player2.PlayerMvp;
import com.edavtyan.materialplayer.components.player2.PlayerService;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.utils.ArtProvider2;

import lombok.Setter;

public class NowPlayingFloatingModel
		implements NowPlayingFloatingMvp.Model,
				   PlayerMvp.Player.OnNewTrackListener {

	private final Context context;
	private final ArtProvider2 artProvider;
	private PlayerService service;
	private @Setter OnNewTrackListener onNewTrackListener;
	private @Setter OnServiceConnectedListener onServiceConnectedListener;

	public NowPlayingFloatingModel(Context context, ArtProvider2 artProvider) {
		this.context = context;
		this.artProvider = artProvider;
	}

	@Override
	public void bind() {
		Intent intent = new Intent(context, PlayerService.class);
		context.bindService(intent, this, Context.BIND_AUTO_CREATE);
	}

	@Override
	public void unbind() {
		context.unbindService(this);
	}

	@Override
	public Track getNowPlayingTrack() {
		return service.getPlayer().getCurrentTrack();
	}

	@Override public String getNowPlayingTrackArtPath() {
		return artProvider.load(getNowPlayingTrack()).getAbsolutePath();
	}

	@Override
	public boolean isPlaying() {
		return service.getPlayer().isPlaying();
	}

	@Override
	public void togglePlayPause() {
		service.getPlayer().playPause();
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

	@Override public void onNewTrack() {
		if (onNewTrackListener != null) {
			onNewTrackListener.onNewTrack();
		}
	}
}
