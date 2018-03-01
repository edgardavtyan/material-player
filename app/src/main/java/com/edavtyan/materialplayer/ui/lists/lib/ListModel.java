package com.edavtyan.materialplayer.ui.lists.lib;

import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.service.PlayerService;

import lombok.Setter;

public class ListModel implements ModelServiceModule.OnServiceConnectedListener {

	private final ModelServiceModule serviceModule;

	@Setter
	@Nullable
	private OnServiceConnectedListener onServiceConnectedListener;

	@Nullable
	private PlayerService service;

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

	protected PlayerService getService() {
		if (service != null) {
			return service;
		} else {
			throw new ServiceNotConnectedException();
		}
	}

	@Override
	public void onServiceConnected(PlayerService service) {
		this.service = service;

		if (onServiceConnectedListener != null) {
			onServiceConnectedListener.onServiceConnected();
		}
	}
}
