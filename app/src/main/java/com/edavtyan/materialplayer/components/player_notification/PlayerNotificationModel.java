package com.edavtyan.materialplayer.components.player_notification;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.edavtyan.materialplayer.components.player2.PlayerMvp;
import com.edavtyan.materialplayer.components.player2.PlayerService;
import com.edavtyan.materialplayer.db.Track;

import lombok.Setter;

public class PlayerNotificationModel
		implements ServiceConnection, PlayerNotificationMvp.Model,
				   PlayerMvp.Player.OnNewTrackListener {

	private final Context context;

	private PlayerService service;

	private @Setter OnNewTrackListener onNewTrackListener;

	public PlayerNotificationModel(Context context) {
		this.context = context;
	}

	@Override public void bind() {
		Intent intent = new Intent(context, PlayerService.class);
		context.bindService(intent, this, Context.BIND_AUTO_CREATE);
	}

	@Override public void unbind() {
		context.unbindService(this);
	}

	@Override public boolean isPlaying() {
		return service.getPlayer().isPlaying();
	}

	@Override public Track getTrack() {
		return service.getPlayer().getCurrentTrack();
	}

	@Override public void onServiceConnected(ComponentName name, IBinder binder) {
		service = ((PlayerService.PlayerBinder) binder).getService();
		service.getPlayer().setOnNewTrackListener(this);
	}

	@Override public void onServiceDisconnected(ComponentName name) {
	}

	@Override public void onNewTrack() {
		if (onNewTrackListener != null) onNewTrackListener.onNewTrack();
	}
}
