package com.edavtyan.materialplayer.components.lists.lib;

import com.edavtyan.materialplayer.components.player.PlayerService;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

import lombok.Setter;

public class ListModel
		implements ListMvp.Model,
				   ModelServiceModule.OnServiceConnectedListener,
				   ModelServiceModule.OnServiceDisconnectedListener {

	protected PlayerService service;

	private final CompactListPref compactListPref;
	private final ModelServiceModule serviceModule;

	private @Setter OnServiceConnectedListener onServiceConnectedListener;

	public interface OnServiceConnectedListener {
		void onServiceConnected();
	}

	public ListModel(ModelServiceModule serviceModule, CompactListPref compactListPref) {
		this.compactListPref = compactListPref;
		this.serviceModule = serviceModule;
		this.serviceModule.setOnServiceConnectedListener(this);
		this.serviceModule.setOnServiceDisconnectedListener(this);
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

		if (onServiceConnectedListener != null) {
			onServiceConnectedListener.onServiceConnected();
		}
	}

	@Override
	public void onServiceDisconnected() {
	}
}
