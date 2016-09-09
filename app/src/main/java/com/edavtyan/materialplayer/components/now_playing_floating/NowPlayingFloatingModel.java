package com.edavtyan.materialplayer.components.now_playing_floating;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.edavtyan.materialplayer.MusicPlayerService;
import com.edavtyan.materialplayer.components.player.MusicPlayer;
import com.edavtyan.materialplayer.db.Track;

import lombok.Setter;

public class NowPlayingFloatingModel
		implements NowPlayingFloatingMvp.Model,
				   MusicPlayer.OnPreparedListener {

	private final Context context;
	private MusicPlayerService service;
	private @Setter OnNewTrackListener onNewTrackListener;
	private @Setter OnServiceConnectedListener onServiceConnectedListener;

	public NowPlayingFloatingModel(Context context) {
		this.context = context;
	}

	@Override
	public void bind() {
		Intent intent = new Intent(context, MusicPlayerService.class);
		context.bindService(intent, this, Context.BIND_AUTO_CREATE);
	}

	@Override
	public void unbind() {
		context.unbindService(this);
	}

	@Override
	public Track getNowPlayingTrack() {
		return service.getQueue().getCurrentTrack();
	}

	@Override
	public boolean isPlaying() {
		return service.getPlayer().isPlaying();
	}

	@Override
	public void togglePlayPause() {
		if (isPlaying()) {
			service.getPlayer().pause();
		} else {
			service.getPlayer().resume();
		}
	}

	@Override
	public boolean hasData() {
		return service.getQueue().hasData();
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder binder) {
		service = ((MusicPlayerService.MusicPlayerBinder) binder).getService();
		service.getPlayer().setOnPreparedListener(this);

		if (onServiceConnectedListener != null) {
			onServiceConnectedListener.onServiceConnected();
		}
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {

	}

	@Override
	public void onPrepared() {
		if (onNewTrackListener != null) {
			onNewTrackListener.onNewTrack();
		}
	}
}
