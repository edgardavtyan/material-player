package com.edavtyan.materialplayer.ui.lists.lib;

import com.edavtyan.materialplayer.service.PlayerService;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

import lombok.Setter;

public class ListModel implements ModelServiceModule.OnServiceConnectedListener {

	protected PlayerService service;

	private final ModelServiceModule serviceModule;

	private @Setter OnServiceConnectedListener onServiceConnectedListener;

	public interface OnServiceConnectedListener {
		void onServiceConnected();
	}

	public ListModel(ModelServiceModule serviceModule) {
		this.serviceModule = serviceModule;
		this.serviceModule.setOnServiceConnectedListener(this);
	}

	public void bindService() {
		serviceModule.bind();
	}

	public void unbindService() {
		serviceModule.unbind();
	}

	@Override
	public void onServiceConnected(PlayerService service) {
		this.service = service;

		if (onServiceConnectedListener != null) {
			onServiceConnectedListener.onServiceConnected();
		}
	}
}
