package com.edavtyan.materialplayer.components.audioeffects;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.edavtyan.materialplayer.components.audioeffects.models.BassBoost;
import com.edavtyan.materialplayer.components.audioeffects.models.Equalizer;
import com.edavtyan.materialplayer.components.audioeffects.models.Surround;
import com.edavtyan.materialplayer.components.player.PlayerService;

import lombok.Setter;

public class AudioEffectsModel implements AudioEffectsMvp.Model {
	private final Context context;

	private PlayerService service;

	private @Setter ServiceConnectionListener onServiceConnectedListener;

	public AudioEffectsModel(Context context) {
		this.context = context;
	}

	@Override
	public void init() {
		Intent intent = new Intent(context, PlayerService.class);
		context.bindService(intent, this, Context.BIND_AUTO_CREATE);
	}

	@Override
	public void close() {
		context.unbindService(this);
	}

	@Override
	public Equalizer getEqualizer() {
		return service.getEqualizer();
	}

	@Override
	public BassBoost getBassBoost() {
		return service.getBassBoost();
	}

	@Override
	public Surround getSurround() {
		return service.getSurround();
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder binder) {
		service = ((PlayerService.PlayerBinder) binder).getService();

		if (onServiceConnectedListener != null) {
			onServiceConnectedListener.onServiceConnected();
		}
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
	}
}
