package com.edavtyan.materialplayer.components.lists.lib;

import android.content.Context;

import com.edavtyan.materialplayer.components.player.PlayerService;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

public class ListModel
		implements ListMvp.Model,
				   ModelServiceModule.OnServiceConnectedListener,
				   ModelServiceModule.OnServiceDisconnectedListener {

	protected PlayerService service;

	private final CompactListPref compactListPref;
	private final ModelServiceModule serviceModule;

	public ListModel(Context context, CompactListPref compactListPref) {
		this.compactListPref = compactListPref;
		serviceModule = new ModelServiceModule(context);
		serviceModule.setOnServiceConnectedListener(this);
		serviceModule.setOnServiceDisconnectedListener(this);
	}

	public void bindService() {
		serviceModule.bind();
	}

	public void unbindService() {
		serviceModule.unbind();
	}

	@Override
	public boolean isCompactModeEnabled() {
		return compactListPref.isEnabled();
	}

	@Override
	public void onServiceConnected(PlayerService service) {
		this.service = service;
	}

	@Override
	public void onServiceDisconnected() {
	}
}
