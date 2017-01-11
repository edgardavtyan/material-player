package com.edavtyan.materialplayer.lib.mvp.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.edavtyan.materialplayer.components.player.PlayerService;

public abstract class ServiceModel implements ServiceConnection {
	private final Context context;
	protected PlayerService service;

	public ServiceModel(Context context) {
		this.context = context;
	}

	public void bindService() {
		Intent intent = new Intent(context, PlayerService.class);
		context.bindService(intent, this, Context.BIND_AUTO_CREATE);
	}

	public void unbindService() {
		context.unbindService(this);
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder binder) {
		service = ((PlayerService.PlayerBinder) binder).getService();
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
	}
}
