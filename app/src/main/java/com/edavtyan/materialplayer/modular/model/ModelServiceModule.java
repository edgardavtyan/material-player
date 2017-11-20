package com.edavtyan.materialplayer.modular.model;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.edavtyan.materialplayer.components.player.PlayerService;

import lombok.Getter;
import lombok.Setter;

public class ModelServiceModule implements ServiceConnection {
	private final Context context;

	private @Getter PlayerService service;

	private @Setter OnServiceConnectedListener onServiceConnectedListener;

	public interface OnServiceConnectedListener {
		void onServiceConnected(PlayerService service);
	}

	public ModelServiceModule(Context context) {
		this.context = context;
	}

	public void bind() {
		Intent intent = new Intent(context, PlayerService.class);
		context.bindService(intent, this, Context.BIND_AUTO_CREATE);
	}

	public void unbind() {
		context.unbindService(this);
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder binder) {
		service = ((PlayerService.PlayerBinder) binder).getService();

		if (onServiceConnectedListener != null) {
			onServiceConnectedListener.onServiceConnected(service);
		}
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
	}
}
